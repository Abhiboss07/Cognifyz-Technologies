import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java ChatClient <host> <port> [nickname]");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String nickname = (args.length >= 3) ? args[2] : null;

        try (Socket socket = new Socket(host, port)) {
            socket.setTcpNoDelay(true);

            BufferedReader serverIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );
            BufferedWriter serverOut = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
            );

            AtomicBoolean running = new AtomicBoolean(true);

            // Reader thread: prints messages from server
            Thread reader = new Thread(() -> {
                try {
                    String line;
                    while (running.get() && (line = serverIn.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException ignored) {
                } finally {
                    running.set(false);
                }
            }, "server-listener");
            reader.setDaemon(true);
            reader.start();

            // If nickname specified, try to set it immediately
            if (nickname != null && !nickname.isBlank()) {
                serverOut.write("/name " + nickname + "\n");
                serverOut.flush();
            }

            // Main loop: read from stdin and send to server
            BufferedReader console = new BufferedReader(
                    new InputStreamReader(System.in, StandardCharsets.UTF_8)
            );
            String input;
            while (running.get() && (input = console.readLine()) != null) {
                if (input.equalsIgnoreCase("/quit")) {
                    serverOut.write("/quit\n");
                    serverOut.flush();
                    break;
                }
                serverOut.write(input + "\n");
                serverOut.flush();
            }

            running.set(false);
        }
    }
}
