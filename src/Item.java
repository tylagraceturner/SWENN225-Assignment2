import java.util.ArrayList;
import java.util.List;

public class Item {

    int x;
    int y;

    ArrayList<Card> playersHand = new ArrayList<>();
    String name;
    String item;
    boolean lost = false;





    //Contructor A
    public Item(String name){
        this.name = name;
    }

    //Constructor B
    public Item(String name, String item, int x, int y){
        this.name = name;
        this.item = item;
        this.x = x;
        this.y = y;
    }

    public List<Card> getPlayersHand(){
        return playersHand;
    }

    /**
     * Prints the player's hand.
     */
    public void printHand() {
        int i = 0;
        for (Card card : playersHand) {
            System.out.println(i + "= " + card.getName() + " [" + card.getType() + "] ");
            i++;
        }
    }

    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return name;
    }





}
