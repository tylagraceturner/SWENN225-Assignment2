import java.util.ArrayList;

/**
 * Represents the different rooms on the board in Cluedo.
 */
public class Room  {

    public String name;
    //Room Associations
    private String roomCard;
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
     * @return character name as print
     */
    public String getPrint() {
        return print;
    }


}