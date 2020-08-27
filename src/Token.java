import java.util.ArrayList;

public class Token {
    int xPos, yPos;
    String name;
    String token;
    ArrayList<Card> hand = new ArrayList<>();
    boolean lostGame = false;

    public Token(String name) {this.name = name;}
    public Token(String name, String token, int xPos, int yPos){
        this.name = name;
        this.token = token;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public ArrayList<Card> getHand() { return hand; }
    public String printHand(){
        StringBuilder sb = new StringBuilder();
        for(Card card: hand){
            sb.append(card.getName());
            sb.append(" ");
        }
        return sb.toString();
    }
}
