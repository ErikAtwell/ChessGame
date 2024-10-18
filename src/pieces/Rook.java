package pieces;

import game.Board;
import game.Piece;
import util.Color;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wRk" : "bRk";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // The rook can move horizontally or vertically
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        boolean isHorizontal = startRow == destRow;
        boolean isVertical = startCol == destCol;

        // If horizontal
        if (isHorizontal) {
            int direction = Integer.signum(destCol - startCol);
            int col = startCol + direction;

            while (col != destCol) {
                if (!Board.getTile(startRow, col).isEmpty()) {
                    return false;
                }

                col += direction;
            }

            if (Board.getTile(destRow, destCol).isEmpty() || !Board.getTile(destRow, destCol).isEmpty() && Board.getTile(destRow, destCol).getPiece().getColor() != this.color) {
                return true;
            }
        }

        // If vertical
        if (isVertical) {
            int direction = Integer.signum(destRow - startRow);
            int row = startRow + direction;

            while (row != destRow) {
                if (!Board.getTile(row, startCol).isEmpty()) {
                    return false;
                }

                row += direction;
            }

            return Board.getTile(destRow, destCol).isEmpty() || !Board.getTile(destRow, destCol).isEmpty() && Board.getTile(destRow, destCol).getPiece().getColor() != this.color;
        }
        return false;
    }
}
