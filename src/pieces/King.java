package pieces;

import game.Board;
import game.Piece;
import util.Color;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wKg" : "bKg";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // The king moves one square in any direction.
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        // Calculate the differences
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        // Check if the move is within one square in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if the destination is empty or contains an opponent's piece
            return Board.getTile(destRow, destCol).isEmpty() || !Board.getTile(destRow, destCol).isEmpty()
                    && Board.getTile(destRow, destCol).getPiece().getColor() != this.color;
        }

        return false;
    }
}
