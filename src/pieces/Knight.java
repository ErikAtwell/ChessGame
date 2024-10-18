package pieces;

import game.Board;
import game.Piece;
import util.Color;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wKn" : "bKn";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // The knight moves in an L-shape. Two moves in one direction and one move in a
        // perpendicular direction. 
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        // Calculate the differences
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        // Check if the move is an "L" shape, we don't have to keep track of
        // every move since the knight can jump over other pieces.
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check if the destination is empty or contains an opponent's piece
            return Board.getTile(destRow, destCol).isEmpty()
                    || Board.getTile(destRow, destCol).getPiece().getColor() != this.color;
        }

        return false;
    }
}
