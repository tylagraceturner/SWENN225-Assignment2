import java.util.ArrayList;
import java.util.List;

/**
 * Represents the item cards in Cluedo.
 */
public class Item {
    int x;
    int y;

    List<Card> playersHand = new ArrayList<>();
    String name;
    String item;
    boolean lost = false;

    public Item(String name, String item, int x, int y){
        this.name = name;
        this.item = item;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the player's hand.
     */
    public List<Card> getPlayersHand(){
        return playersHand;
    }


    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * @return the x position
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y position of the player
     */
    public int getY() {
        return y;
    }
}
