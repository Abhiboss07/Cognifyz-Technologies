# Tic-Tac-Toe Game

A simple console-based Tic-Tac-Toe game implemented in Java. This project demonstrates object-oriented programming concepts with a classic two-player game.

## 🎮 Features

- **Two-player gameplay**: Play against another person on the same computer
- **Interactive console interface**: Easy-to-use text-based interface
- **Input validation**: Robust error handling for invalid moves
- **Game state tracking**: Automatic win detection and draw detection
- **Play again functionality**: Option to restart the game after completion
- **Clean board display**: Visual representation of the game board

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any text editor or IDE

### Compilation and Execution

1. **Compile the Java file:**
   ```bash
   javac TicTacToe.java
   ```

2. **Run the game:**
   ```bash
   java TicTacToe
   ```

## 🎯 How to Play

1. **Game Setup**: The game starts with Player X
2. **Making Moves**: 
   - Enter row number (0-2) followed by column number (0-2)
   - Row 0 is the top, Row 2 is the bottom
   - Column 0 is the left, Column 2 is the right
3. **Valid Moves**: You can only place your mark on empty cells
4. **Winning**: Get three of your marks in a row (horizontally, vertically, or diagonally)
5. **Draw**: If all cells are filled without a winner

### Board Layout
```
| 0,0 | 0,1 | 0,2 |
| 1,0 | 1,1 | 1,2 |
| 2,0 | 2,1 | 2,2 |
```

## 📁 Project Structure

```
Task 1/
├── TicTacToe.java      # Main game implementation
├── TicTacToe.class     # Compiled bytecode
└── README.md           # This file
```

## 🔧 Code Structure

### Main Classes and Methods

- **`TicTacToe`**: Main game class
  - `main()`: Entry point with play-again loop
  - `initializeBoard()`: Sets up empty game board
  - `printBoard()`: Displays current game state
  - `playGame()`: Main game loop
  - `checkWin()`: Checks for winning conditions
  - `isBoardFull()`: Checks for draw condition
  - `switchPlayer()`: Alternates between X and O

### Key Features Implemented

1. **Game Board**: 3x3 character array representation
2. **Player Management**: Automatic switching between X and O
3. **Win Detection**: Checks all possible winning combinations
4. **Input Validation**: Handles invalid inputs gracefully
5. **Error Handling**: Try-catch blocks for robust user input

## 🎲 Game Rules

- Players take turns placing their mark (X or O) on the board
- First player to get three marks in a row wins
- If all cells are filled without a winner, the game is a draw
- Invalid moves (out of bounds or occupied cells) are rejected

## 🛠️ Technical Details

- **Language**: Java
- **Paradigm**: Object-Oriented Programming
- **Data Structures**: 2D arrays for board representation
- **Input/Output**: Console-based using Scanner class
- **Error Handling**: Exception handling for user input validation

## 🎯 Future Enhancements

Potential improvements for this project:
- AI opponent implementation
- Score tracking across multiple games
- GUI interface using Swing or JavaFX
- Network multiplayer functionality
- Different board sizes
- Undo/redo functionality

## 👨‍💻 Author

This project was created as part of the Cognifyz Technologies internship program.

## 📄 License

This project is open source and available under the MIT License.

---

**Enjoy playing Tic-Tac-Toe!** 🎮
