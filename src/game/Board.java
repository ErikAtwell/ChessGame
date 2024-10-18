package game;

import pieces.*;
import util.Color;

public abstract class Board {
    private static final Tile[][] tiles = new Tile[9][9];

    public static int currentTurn = 0;

    public static void initialize() {
        // Empty tiles and borders
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (i == 0) {
                    tiles[i][j] = new Border(" " + (j == 0 ? " " : Integer.toString(j)) + " ", i, j);
                    continue;
                }

                if (j == 0) {
                    tiles[i][j] = new Border(" " + (char) ('a' + (i - 1)) + " ", i, j);
                    continue;
                }

                tiles[i][j] = new Tile(i, j);
            }
        }

        // Pawns
        for (int i = 1; i < tiles.length; i++) {
            tiles[2][i].setPiece(new Pawn(Color.BLACK));
            tiles[7][i].setPiece(new Pawn(Color.WHITE));
        }

        // Rooks
        tiles[1][1].setPiece(new Rook(Color.BLACK));
        tiles[1][8].setPiece(new Rook(Color.BLACK));
        tiles[8][1].setPiece(new Rook(Color.WHITE));
        tiles[8][8].setPiece(new Rook(Color.WHITE));

        // Knights

        tiles[1][2].setPiece(new Knight(Color.BLACK));
        tiles[1][7].setPiece(new Knight(Color.BLACK));
        tiles[8][2].setPiece(new Knight(Color.WHITE));
        tiles[8][7].setPiece(new Knight(Color.WHITE));

        // Bishops
        tiles[1][3].setPiece(new Bishop(Color.BLACK));
        tiles[1][6].setPiece(new Bishop(Color.BLACK));
        tiles[8][3].setPiece(new Bishop(Color.WHITE));
        tiles[8][6].setPiece(new Bishop(Color.WHITE));

        // Queens
        tiles[1][4].setPiece(new Queen(Color.BLACK));
        tiles[8][4].setPiece(new Queen(Color.WHITE));

        // Kings
        tiles[1][5].setPiece(new King(Color.BLACK));
        tiles[8][5].setPiece(new King(Color.WHITE));
    }

    // This will check if moves are valid and if they are, handle movement.
    public static void movePiece(Color current, int startRow, int startCol, int destRow, int destColumn) {
        // If the source tile or destination tile is out of bounds, we'll return.
        if (startRow < 1 || startRow > 8 || startCol < 1 || startCol > 8 || destRow < 1 || destRow > 8 || destColumn < 1
                || destColumn > 8) {
            System.out.println("Out of bounds! Try again.");
            return;
        }

        Tile sourceTile = tiles[startRow][startCol];
        Tile destTile = tiles[destRow][destColumn];

        if (sourceTile.isEmpty()) {
            System.out.println("No piece to move! Try again.");
            return;
        }

        // If a tile that is not assigned to a player is attempted to be moved, we'll
        // return.
        if (sourceTile.getPiece().getColor() != current) {
            System.out.println("You don't own that piece! Try again.");
            return;
        }

        boolean canMove = sourceTile.getPiece().canMoveTo(destRow, destColumn);

        // Debug info, can be removed if needed.
        System.out.println("Source Row: " + startRow);
        System.out.println("Source Column: " + startCol);
        System.out.println("Dest Row: " + destRow);
        System.out.println("Dest Column: " + destColumn);
        System.out.println("Piece: " + sourceTile.getPiece().toString());
        System.out.println("Success: " + canMove);

        if (canMove) {
            // Check for checkmate.
            if (destTile.getPiece() instanceof King) {
                Board.print();
                System.out.println("Checkmate! " + current + " wins!");
                System.exit(0);
            }

            destTile.setPiece(sourceTile.getPiece());
            sourceTile.setPiece(null); // Remove the piece from the source tile, leaving "##" by default.
            currentTurn++;
        } else {
            System.out.println("Invalid move! Try again.");
        }
    }

    public static Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public static void print() {
        for (Tile[] tile : tiles) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.print(tile[j].toString() + " ");
            }
            System.out.println();
        }
    }
}
