/**
 * Represents the tiles on the board in Cluedo.
 */
public class Tile {
    //Tile Associations
    private int r, c;
    private Room room;
    private String printable = " ";
    private boolean accessible = true;
    private Player contains = null;

    //CONSTRUCTOR
    Tile(int r, int c) {
        this.r = r;
        this.c = c;
    }

    //INTERFACE
    /**
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @return column position
     */
    public int getCol() {
        return c;
    }

    /**
     * @return row position
     */
    public int getRow() {
        return r;
    }

    /**
     * @param contains
     */
    public void setContains(Player contains) {
        this.contains = contains;
    }

    /**
     * @return contains
     */
    public Player getContains() {
        return contains;
    }

    /**
     * @param room, setting room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @return if accessible or not
     */
    public boolean isAccessible() {
        return accessible;
    }

    /**
     * @return setting accessible to false
     */
    public void setAccessible() {
        accessible = !accessible;
    }

    /**
     * @return string for better visual representation
     */
    public String getPrint() {
        if (contains != null) {
            return contains.getPrint();
        }
        return printable;
    }

    /**
     * setting the display details
     */
    public void setPrint(String print) {
        this.printable = print;
    }
}