# 🔐 File Encryption & Decryption in Java

This project is a **simple file encryption and decryption tool** built in Java. It allows you to encrypt the contents of any text file using a basic Caesar cipher algorithm and later decrypt it back. Perfect for beginners who want to learn how file handling and simple cryptography work in Java.

---

## ✨ Features

* Encrypt any `.txt` file with a numeric key.
* Decrypt the encrypted file back to original text.
* Drag & Drop file support for easier path input.
* Saves the encrypted/decrypted result as a new file.
* Beginner-friendly, clean and simple code.

---

## 🛠 Technologies Used

* **Java** (Core Java, File Handling, String manipulation)
* **Caesar Cipher Algorithm** (basic shift cipher)

---

## 📂 Project Structure

```
FileEncryptDecrypt.java   # Main program
README.md                 # Project documentation
hello.txt                 # Example input file
encrypted.txt             # Encrypted output file (auto-generated)
decrypted.txt             # Decrypted output file (auto-generated)
```

---

## 🚀 How to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/Abhiboss07/Cognifyz-Technologies/
   cd File-Encrypt-Decrypt-Java
   ```

2. Compile the Java program:

   ```bash
   javac FileEncryptDecrypt.java
   ```

3. Run the program:

   ```bash
   java FileEncryptDecrypt
   ```

4. Follow the instructions in the console:

   * Choose `1` for encryption or `2` for decryption.
   * Enter the file path (Tip: drag & drop the file into the console).
   * Enter a numeric key (e.g., `3`).
   * The result will be saved as `encrypted.txt` or `decrypted.txt`.

---

## 📖 Example

Input file: `hello.txt`

```
Hello, this is a secret message!
```

Run → Encrypt with key = 3

Output file: `encrypted.txt`

```
Khoor/#wklv#lv#d#vhfuhw#phvvdjh$
```

Run → Decrypt with key = 3

Output file: `decrypted.txt`

```
Hello, this is a secret message!
```

---

## ⚠️ Notes

* This uses a **very basic Caesar Cipher**, not secure for real applications.
* Designed for learning **file handling and string manipulation in Java**.
* Works best with `.txt` files.

---

## 🤝 Contributing

Feel free to fork this repo, improve the algorithm (e.g., AES, RSA), or add features like GUI. Pull requests are welcome!

---

## 📜 License

This project is open-source under the **MIT License**.

---
