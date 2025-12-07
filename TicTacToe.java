
import java.util.*;

public class TicTacToe {

    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    static Scanner sc = new Scanner(System.in);

    // Print the board
    public static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if someone won
    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) return true;
        }
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
               (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    // Check draw
    static boolean isDraw() {
        for (char[] row : board)
            for (char c : row)
                if (c == ' ') return false;
        return true;
    }

    // Human move
    static void playerMove(char p) {
        int r, c;
        while (true) {
            System.out.print("Enter row and column (1-3 1-3): ");
            r = sc.nextInt() - 1;
            c = sc.nextInt() - 1;
            if (r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == ' ') {
                board[r][c] = p;
                break;
            }
            System.out.println("Invalid move, try again.");
        }
    }

    // Simple AI move
    static void aiMove() {
        // 1. Take center
        if (board[1][1] == ' ') {
            board[1][1] = 'O';
            return;
        }
        // 2. Take corners
        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        for (int[] c : corners) {
            if (board[c[0]][c[1]] == ' ') {
                board[c[0]][c[1]] = 'O';
                return;
            }
        }
        // 3. Take edges
        int[][] edges = {{0,1},{1,0},{1,2},{2,1}};
        for (int[] e : edges) {
            if (board[e[0]][e[1]] == ' ') {
                board[e[0]][e[1]] = 'O';
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Choose Mode:\n1. Human vs Human\n2. Human vs AI");
        int mode = sc.nextInt();

        char current = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();

            if (mode == 2 && current == 'O') {
                System.out.println("AI's Turn:");
                aiMove();
            } else {
                System.out.println("Player " + current + "'s Turn:");
                playerMove(current);
            }

            if (checkWin(current)) {
                printBoard();
                System.out.println("Player " + current + " Wins!");
                gameOver = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("Game is a Draw!");
                gameOver = true;
            }

            current = (current == 'X') ? 'O' : 'X';
        }
    }
}
    
