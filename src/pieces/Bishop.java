package pieces;

import game.Piece;
import util.Color;
import game.Board;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wBp" : "bBp";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // The bishop moves diagonally any number of squares.
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        // Check if the move is diagonal
        if (Math.abs(destRow - startRow) == Math.abs(destCol - startCol)) {
            int rowDirection = Integer.signum(destRow - startRow);
            int colDirection = Integer.signum(destCol - startCol);
            int row = startRow + rowDirection;
            int col = startCol + colDirection;

            // Check if there are any pieces in the way
            while (row != destRow && col != destCol) {
                if (!Board.getTile(row, col).isEmpty()) {
                    return false;
                }
                row += rowDirection;
                col += colDirection;
            }

            // Check if the destination is empty or contains an opponent's piece
            return Board.getTile(destRow, destCol).isEmpty() || Board.getTile(destRow, destCol).getPiece().getColor() != this.color;
        }

        return false;
    }
}
