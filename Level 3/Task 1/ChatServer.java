import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ChatServer {
    private final int port;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    // nickname -> ClientHandler
    private final ConcurrentMap<String, ClientHandler> clients = new ConcurrentHashMap<>();

    // Generate default unique names: User1, User2, ...
    private final AtomicInteger userSeq = new AtomicInteger(1);

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                socket.setTcpNoDelay(true);
                String tempName = nextDefaultName();
                ClientHandler handler = new ClientHandler(socket, tempName);
                pool.submit(handler);
            }
        } finally {
            pool.shutdownNow();
        }
    }

    private String nextDefaultName() {
        return "User" + userSeq.getAndIncrement();
    }

    private void log(String msg) {
        System.out.println("[SERVER] " + msg);
    }

    private void broadcast(String from, String message) {
        String payload = String.format("%s: %s", from, message);
        for (ClientHandler ch : clients.values()) {
            ch.send(payload);
        }
    }

    private void systemBroadcast(String message) {
        for (ClientHandler ch : clients.values()) {
            ch.send("[system] " + message);
        }
    }

    private boolean isNameAvailable(String name) {
        return name != null && !name.isBlank() && !clients.containsKey(name);
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private String nickname;
        private BufferedReader in;
        private BufferedWriter out;
        private volatile boolean running = true;

        ClientHandler(Socket socket, String initialName) {
            this.socket = socket;
            this.nickname = initialName;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

                // Finish login: try to register default nickname
                registerClient();

                send("Welcome to the chat! Your name is '" + nickname + "'.");
                send("Commands: /list, /name <new>, /w <user> <msg>, /quit");

                String line;
                while (running && (line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    if (line.startsWith("/")) {
                        handleCommand(line);
                    } else {
                        broadcast(nickname, line);
                    }
                }
            } catch (IOException e) {
                log("I/O error with " + nickname + ": " + e.getMessage());
            } finally {
                close();
            }
        }

        private void registerClient() throws IOException {
            // Ensure unique nickname; if default taken (rare), generate new.
            synchronized (clients) {
                while (!isNameAvailable(nickname)) {
                    nickname = nextDefaultName();
                }
                clients.put(nickname, this);
            }
            systemBroadcast(nickname + " joined the chat.");
            log(nickname + " connected from " + socket.getRemoteSocketAddress());
        }

        private void handleCommand(String raw) {
            if (raw.equalsIgnoreCase("/list")) {
                send("Online: " + String.join(", ", clients.keySet()));
                return;
            }

            if (raw.equalsIgnoreCase("/quit")) {
                send("Goodbye!");
                running = false;
                return;
            }

            if (raw.toLowerCase().startsWith("/name ")) {
                String[] parts = raw.split("\\s+", 2);
                if (parts.length < 2 || parts[1].isBlank()) {
                    send("Usage: /name <newName>");
                    return;
                }
                String newName = parts[1].trim();
                if (!newName.matches("[A-Za-z0-9_]{1,20}")) {
                    send("Name must be 1-20 chars: letters, numbers, underscore.");
                    return;
                }
                if (!isNameAvailable(newName)) {
                    send("Name '" + newName + "' is taken.");
                    return;
                }
                String old = this.nickname;
                synchronized (clients) {
                    clients.remove(old);
                    this.nickname = newName;
                    clients.put(newName, this);
                }
                systemBroadcast(old + " is now known as " + newName + ".");
                return;
            }

            if (raw.toLowerCase().startsWith("/w ")) {
                String[] parts = raw.split("\\s+", 3);
                if (parts.length < 3) {
                    send("Usage: /w <user> <message>");
                    return;
                }
                String target = parts[1];
                String msg = parts[2];
                ClientHandler dest = clients.get(target);
                if (dest == null) {
                    send("User not found: " + target);
                    return;
                }
                dest.send("(whisper) " + nickname + ": " + msg);
                send("(to " + target + ") " + msg);
                return;
            }

            send("Unknown command. Try /list, /name, /w, /quit");
        }

        void send(String msg) {
            try {
                out.write(msg);
                out.write("\n");
                out.flush();
            } catch (IOException e) {
                running = false;
            }
        }

        private void broadcast(String from, String message) {
            ChatServer.this.broadcast(from, message);
        }

        private void close() {
            try { if (out != null) out.flush(); } catch (IOException ignored) {}
            try { if (socket != null && !socket.isClosed()) socket.close(); } catch (IOException ignored) {}
            if (nickname != null) {
                boolean removed;
                synchronized (clients) {
                    removed = clients.remove(nickname, this);
                }
                if (removed) {
                    systemBroadcast(nickname + " left the chat.");
                    log(nickname + " disconnected");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 5555;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new ChatServer(port).start();
    }
}