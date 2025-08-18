import java.io.*;
import java.util.Scanner;

public class FileEncryptDecrypt {

    // Encrypt text using Caesar cipher
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) (c + shift));
        }
        return result.toString();
    }

    // Decrypt text
    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append((char) (c - shift));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask user choice
            System.out.println("Choose an option: ");
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Ask for file path
            System.out.print("Enter file name or path (Tip: Drag & Drop file here): ");
            String filePath = scanner.nextLine().trim();

            // Remove quotes if drag-drop added them
            if (filePath.startsWith("\"") && filePath.endsWith("\"")) {
                filePath = filePath.substring(1, filePath.length() - 1);
            }

            // Replace backslashes with forward slashes (Windows safe)
            filePath = filePath.replace("\\", "/");

            // Check if file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found! Please check the path: " + filePath);
                return;
            }

            // Read file content
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // Ask shift key
            System.out.print("Enter a number key (e.g., 3): ");
            int shift = scanner.nextInt();

            String resultText;
            String outputFile;

            // Fix: handle null parent safely
            String parentFolder = file.getParent();
            if (parentFolder == null) {
                parentFolder = "."; // current working directory
            }

            if (choice == 1) { // Encrypt
                resultText = encrypt(content.toString(), shift);
                outputFile = parentFolder + "/encrypted.txt";
                System.out.println("File successfully encrypted → " + outputFile);
            } else { // Decrypt
                resultText = decrypt(content.toString(), shift);
                outputFile = parentFolder + "/decrypted.txt";
                System.out.println("File successfully decrypted → " + outputFile);
            }

            // Write output
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(resultText);
            writer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
