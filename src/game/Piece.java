package game;

import util.Color;

public abstract class Piece {
    // We'll give each piece a color, so we can easily determine which player it belongs to.
    protected Color color;
    protected Tile tile;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Tile getTile() {
        return this.tile;
    }

    public abstract boolean canMoveTo(int destRow, int destCol);

    // Force each piece to have a toString method, so we can print it to the board.
    public abstract String toString();
}
