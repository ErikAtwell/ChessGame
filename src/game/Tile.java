package game;

import util.Color;

public class Tile {
    private final Color color;
    private Piece piece;
    private final int row;
    private final int column;

    public Tile(int row, int col) {
        this.row = row;
        this.column = col;
        this.color = (row + col) % 2 == 0 ? Color.WHITE : Color.BLACK;
        this.piece = null;
    }

    public Color getColor() {
        return this.color;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        // Without this we'd get a crash when assigning piece to null.
        if (piece != null)
            piece.tile = this;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    // Going to use b## and w## instead of "##" to differentiate between black and white empty tiles.
    // Because of this, we're going to have three letter abbreviations for pieces.
    public String toString() {
        return this.piece == null ? (this.color == Color.WHITE ? "w##" : "b##") : this.piece.toString();
    }
}
