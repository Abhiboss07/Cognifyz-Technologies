# Password Strength Checker

A Java application that analyzes password strength based on multiple security criteria. This tool helps users create stronger passwords by providing detailed feedback on what makes a password secure.

## 🚀 Features

- **Comprehensive Analysis**: Checks 5 key password security criteria
- **Visual Feedback**: Clear checkmarks (✓) and crosses (✗) for each criterion
- **Strength Rating**: Categorizes passwords as Weak, Medium, or Strong
- **User-Friendly Interface**: Simple console-based interaction
- **Detailed Explanations**: Provides specific feedback for each security requirement

## 📋 Password Criteria

The application evaluates passwords based on the following criteria:

1. **Length**: At least 8 characters long
2. **Uppercase Letters**: Contains at least one uppercase letter (A-Z)
3. **Lowercase Letters**: Contains at least one lowercase letter (a-z)
4. **Numbers**: Contains at least one digit (0-9)
5. **Special Characters**: Contains at least one special character (!@#$%^&*(), etc.)

## 🎯 Strength Rating System

- **Weak (0-2 criteria met)**: Password needs significant improvement
- **Medium (3-4 criteria met)**: Password is moderately secure
- **Strong (5 criteria met)**: Password meets all security requirements

## 🛠️ Requirements

- Java Development Kit (JDK) 8 or higher
- Any Java IDE or command line

## 📦 Installation & Setup

1. **Clone or download** the project files
2. **Navigate** to the Task 2 directory
3. **Compile** the Java file:
   ```bash
   javac PasswordStrengthChecker.java
   ```
4. **Run** the application:
   ```bash
   java PasswordStrengthChecker
   ```

## 🎮 How to Use

1. **Launch** the application
2. **Enter** a password when prompted
3. **Review** the detailed analysis
4. **Check** the final strength rating
5. **Repeat** with different passwords as needed

## 📊 Example Output

```
Enter a password to check its strength: MyPassword123!

--- Password Strength Analysis ---
[✓] At least 8 characters long.
[✓] Contains at least one uppercase letter.
[✓] Contains at least one lowercase letter.
[✓] Contains at least one number.
[✓] Contains at least one special character (e.g., !@#$%).

--- Result ---
Password Strength: Strong
------------------------------------
```

## 🔍 Sample Test Cases

| Password | Length | Uppercase | Lowercase | Numbers | Special | Strength |
|----------|--------|-----------|-----------|---------|---------|----------|
| `password` | ✓ | ✗ | ✓ | ✗ | ✗ | Weak |
| `Password123` | ✓ | ✓ | ✓ | ✓ | ✗ | Medium |
| `MyPass@123!` | ✓ | ✓ | ✓ | ✓ | ✓ | Strong |
| `abc` | ✗ | ✗ | ✓ | ✗ | ✗ | Weak |
| `PASSWORD123` | ✓ | ✓ | ✗ | ✓ | ✗ | Medium |

## 🏗️ Project Structure

```
Task 2/
├── PasswordStrengthChecker.java    # Main application file
├── PasswordStrengthChecker.class   # Compiled bytecode
├── README.md                       # This documentation file
└── Task 2.iml                      # IntelliJ IDEA project file
```

## 🔧 Code Overview

The application consists of two main methods:

- **`main(String[] args)`**: Handles user input and program flow
- **`analyzePassword(String password)`**: Performs the password analysis and displays results

### Key Features of the Code:

- **Regex Pattern Matching**: Uses regular expressions to detect numbers and special characters
- **String Manipulation**: Leverages Java's string methods for case detection
- **Boolean Logic**: Efficiently tracks which criteria are met
- **Scoring System**: Simple integer-based scoring for strength classification

## 🚀 Future Enhancements

Potential improvements for the application:

- [ ] Add password entropy calculation
- [ ] Include common password dictionary checking
- [ ] Add password generation suggestions
- [ ] Implement GUI interface
- [ ] Add password history tracking
- [ ] Include strength visualization (progress bars)

## 🤝 Contributing

Feel free to contribute to this project by:
- Reporting bugs
- Suggesting new features
- Submitting pull requests
- Improving documentation

## 📄 License

This project is part of the Cognifyz Technologies internship program.

## 👨‍💻 Author

**Abhiboss07**
- GitHub: [@Abhiboss07](https://github.com/Abhiboss07)
- Repository: [Cognifyz-Technologies](https://github.com/Abhiboss07/Cognifyz-Technologies)

---

**Note**: This tool is designed for educational purposes and password strength assessment. For production environments, consider using established security libraries and following industry best practices for password validation.
