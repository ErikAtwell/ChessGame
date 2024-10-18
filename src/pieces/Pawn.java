package pieces;

import game.Piece;
import game.Board;
import util.Color;

public class Pawn extends Piece {
    private boolean firstMove = true;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "wPn" : "bPn";
    }

    @Override
    public boolean canMoveTo(int destRow, int destCol) {
        // Pawn can only move forward one space, unless it's the first move, then in
        // that
        // case it can move forward two spaces.
        int startRow = this.tile.getRow();
        int startCol = this.tile.getColumn();

        // Ensure that the destination isn't same position.
        if (startRow == destRow && startCol == destCol) {
            return false;
        }

        int forward = this.color == Color.WHITE ? -1 : 1;

        boolean isFirstMove = this.firstMove && this.tile.getColumn() == destCol
                && this.tile.getRow() + 2 * forward == destRow
                && Board.getTile(destRow, destCol).isEmpty()
                && Board.getTile(this.tile.getRow() + forward, destCol).isEmpty();

        boolean isStandardMove = this.tile.getColumn() == destCol && this.tile.getRow() + forward == destRow
                && Board.getTile(destRow, destCol).isEmpty();

        // First move has the ability to move two spaces forward.
        boolean isCapture = this.tile.getColumn() + 1 == destCol || this.tile.getColumn() - 1 == destCol
                && this.tile.getRow() + forward == destRow
                && !Board.getTile(destRow, destCol).isEmpty()
                && Board.getTile(destRow, destCol).getPiece().getColor() != this.color;

        // It's designed like this so we can manage isFirstMove properly.
        if (isFirstMove || isStandardMove || isCapture) {
            if (this.firstMove) {
                this.firstMove = false;
                System.out.println("First move used");
            }
            return true;
        }

        return false;
    }
}
