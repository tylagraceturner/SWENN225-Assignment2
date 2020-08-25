/**
 * This class represents the grid-like game board for CLuedo.
 */
public class Board {
    //MEMBER VARIABLES
    private Tile[][] board;

    //CONSTRUCTOR
    public Board() {
        board = new Tile[25][24];
    } //25x24

    //INTERFACE

    /**
     * @returns the board
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Draws the board in text format using System.out.
     */
    public void draw() {
        String out = "";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[1].length; y++) {
                out = out.concat(" " + board[x][y].getPrint());
            }
            out = out.concat("\n");
        }
        System.out.print(out);
    }

    /**
     * Gets the tile requested.
     *
     * @param x
     * @param y
     * @returns the tile, null if it doesn't exist.
     */
    public Tile get(int x, int y) {
        if (x < board.length && x >= 0 && y < board[0].length && y >= 0) {
            return board[x][y];
        }
        return null;
    }
}