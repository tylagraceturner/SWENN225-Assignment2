import java.util.ArrayList;

/**
 * Represents the different rooms on the board in Cluedo.
 */
public class Room  {

    //Room Associations
    private String roomCard;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Board board;
    private String print;

    //CONSTRUCTOR



    public Room(String aRoomCard, Board board, String print) {
        this.roomCard = aRoomCard;
        this.board = board;
        this.print = print;
    }

    //INTERFACE

    /**
     * @return room card
     */
    public String getRoomCard() {
        return roomCard;
    }

    /**
     * @return tile
     */
    public ArrayList<Tile> getTile() {
        return tiles;
    }

    /**
     * @param tile, adding a tile
     */
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * setting the display details
     */
    public void setPrint() {
        for (Tile tile : tiles) {
            tile.setRoom(this);
            tile.setPrint(print);
        }
    }

    /**
     * @return character name as print
     */
    public String getPrint() {
        return print;
    }


}