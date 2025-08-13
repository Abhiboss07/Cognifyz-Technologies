import java.util.Scanner;
public class TicTacToe {

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            TicTacToe game = new TicTacToe();
            game.playGame(); 

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameIsOver = false;

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X starts. Enter row and column (0-2) to make a move.");

        while (!gameIsOver) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");

            int row = -1;
            int col = -1;

            boolean validInput = false;
            while (!validInput) {
                try {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                        board[row][col] = currentPlayer;
                        validInput = true;
                    } else {
                        System.out.println("This move is not valid. The cell might be taken or out of bounds. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter numbers for row and column.");
                    scanner.next();
                }
            }

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameIsOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                gameIsOver = true;
            } else {
                switchPlayer();
            }
        }
    }
}
