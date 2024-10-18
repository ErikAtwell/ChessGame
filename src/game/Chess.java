package game;

import java.util.Scanner;
import util.Color;

public class Chess {

    public static void main(String[] args) {
        Board.initialize();
        Board.print();

        // Our game loop.
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Decide which player's turn it is based on the current turn number.
                Color currentPlayer = Board.currentTurn % 2 == 0 ? Color.WHITE : Color.BLACK;

                System.out.println("Current Turn: " + Board.currentTurn);

                if (currentPlayer == Color.WHITE) {
                    System.out.println("Player One's Turn (White)");
                } else {
                    System.out.println("Player Two's Turn (Black)");
                }

                System.out.println("Enter your move (e.g. g1 f1)");
                String move = scanner.nextLine();
                String[] moveParts = move.split(" ");

                // If there's less than two parts, continue
                if (moveParts.length < 2) {
                    System.out.println("Invalid input. Please enter the move in the format 'g1 f1'.");
                    Board.print();
                    continue;
                }

                int startRow = moveParts[0].charAt(0) - 'a' + 1;
                int startCol = Integer.parseInt(moveParts[0].substring(1));
                int destRow = moveParts[1].charAt(0) - 'a' + 1;
                int destCol = Integer.parseInt(moveParts[1].substring(1));

                Board.movePiece(currentPlayer, startRow, startCol, destRow, destCol);
                Board.print();
            }
        }
    }
}
