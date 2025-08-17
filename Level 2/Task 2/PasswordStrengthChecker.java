import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a password to check its strength: ");
        String password = scanner.nextLine();

        analyzePassword(password);

        scanner.close();
    }

    public static void analyzePassword(String password) {
        int score = 0;

        boolean hasLength = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9]*");

        System.out.println("\n--- Password Strength Analysis ---");

        if (hasLength) {
            System.out.println("[✓] At least 8 characters long.");
            score++;
        } else {
            System.out.println("[✗] Too short. Must be at least 8 characters.");
        }

        if (hasUppercase) {
            System.out.println("[✓] Contains at least one uppercase letter.");
            score++;
        } else {
            System.out.println("[✗] Missing an uppercase letter (A-Z).");
        }

        if (hasLowercase) {
            System.out.println("[✓] Contains at least one lowercase letter.");
            score++;
        } else {
            System.out.println("[✗] Missing a lowercase letter (a-z).");
        }

        if (hasDigit) {
            System.out.println("[✓] Contains at least one number.");
            score++;
        } else {
            System.out.println("[✗] Missing a number (0-9).");
        }

        if (hasSpecialChar) {
            System.out.println("[✓] Contains at least one special character (e.g., !@#$%).");
            score++;
        } else {
            System.out.println("[✗] Missing a special character.");
        }

        System.out.println("\n--- Result ---");
        System.out.print("Password Strength: ");

        if (score <= 2) {
            System.out.println("Weak");
        } else if (score <= 4) {
            System.out.println("Medium");
        } else {
            System.out.println("Strong");
        }
        System.out.println("------------------------------------");
    }
}
