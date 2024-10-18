package game;

public class Border extends Tile {
    private final String name;
    public Border(String name, int row, int col) {
        super(row, col);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
