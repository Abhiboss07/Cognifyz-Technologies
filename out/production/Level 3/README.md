# 💬 Realtime Chat (Task 1)

A **simple TCP-based multi-user chat application** in Java with a server and a console client.
Perfect for learning socket programming, threading, and command-based communication. 🚀

---

## ✨ Features

* 👥 **Auto-assigned nickname** on join (`User1`, `User2`, …)
* 📝 **Chat commands**: `/list`, `/name <new>`, `/w <user> <msg>`, `/quit`
* 🔒 **Private messaging** via `/w`
* 📢 **System notices** for join, leave, and rename events
* ⚡ **Low-latency messaging** with TCP\_NODELAY enabled

---

## ⚙️ Requirements

* Java **8+ (JDK)** installed and available on your `PATH`

---

## 🏗️ Compile

From the project directory, run:

```bash
javac ChatServer.java ChatClient.java
```

---

## 🚀 Run the Server

* Default port → **5555**

```bash
java ChatServer           # starts on port 5555
java ChatServer 6000      # starts on custom port (6000)
```

---

## 💻 Run the Client

**Usage**:

```bash
java ChatClient <host> <port> [nickname]
```

**Examples**:

```bash
java ChatClient 127.0.0.1 5555          # connect with auto nickname
java ChatClient 127.0.0.1 5555 Alice    # request nickname "Alice"
```

---

## ⌨️ In-Chat Commands

* `/list` → Show online users
* `/name <new>` → Change nickname (e.g., `/name Alice_2`)
* `/w <user> <msg>` → Private message (e.g., `/w Bob hello!`)
* `/quit` → Disconnect from chat

---

## 🔮 Example Session (Local)

```bash
# 1. Start the server
java ChatServer

# 2. Connect client A
java ChatClient 127.0.0.1 5555 Alice

# 3. Connect client B
java ChatClient 127.0.0.1 5555 Bob

# 4. Bob types:
Hi everyone!

# 5. Alice types:
/w Bob Hey Bob!
```

---

## 📝 Notes

* ✅ Nickname rules: 1–20 characters (`letters/numbers/underscore`)
* 🚫 If requested nickname is **taken** or **invalid**, the server keeps your current name
* 🧵 Each client runs on its own handler thread
