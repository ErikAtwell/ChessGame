package pieces;

import game.Board;
import game.Piece;
import util.Color;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wQn" : "bQn";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // The queen can move any number of squares along a row, column, or diagonal.
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        // Check if the move is in a straight line or diagonal
        if (startRow == destRow || startCol == destCol || Math.abs(startRow - destRow) == Math.abs(startCol - destCol)) {
            // Check if the path is clear
            int xDirection = Integer.signum(destRow - startRow);
            int yDirection = Integer.signum(destCol - startCol);

            int x = startRow + xDirection;
            int y = startCol + yDirection;

            while (x != destRow || y != destCol) {
                if (!Board.getTile(x, y).isEmpty()) {
                    return false;
                }

                x += xDirection;
                y += yDirection;
            }

            // After we go through all the x and y values, we can check for captures.
            return Board.getTile(destRow, destCol).isEmpty() || !Board.getTile(destRow, destCol).isEmpty()
                    && Board.getTile(destRow, destCol).getPiece().getColor() != this.color;
        }

        return false;
    }
}
