import java.util.*;

/**
 * Represents the players in Cluedo.
 */
public class Player {
    //MEMBER VARIABLES
    private String playerName;
    private Tile playerPosition;
    private Board board;
    private ArrayList<Card> playersHand;
    private CharacterCard charCard;
    private String print = "P";
    private boolean lost = false;
    private boolean moved = false;

    //CONSTRUCTOR
    public Player(Board board, CharacterCard charCard) {
        this.playerPosition = board.get(charCard.getXPos(), charCard.getYPos()); //creates the starting location of the player.
        this.playerPosition.setContains(this);
        this.board = board;
        this.playerName = charCard.getName();
        this.playersHand = new ArrayList<Card>();
        this.charCard = charCard;
    }

    //INTERFACE

    /**
     * Determines where the player moves based on the input.
     *
     * @param input - This is a string which represents the character inputted.
     * @return
     */
    public boolean parser(String input) {


        switch (input.toUpperCase()) {
            case "W":
                return move(-1, 0);
            case "S":
                return move(1, 0);
            case "A":
                return move(0, -1);
            case "D":
                return move(0, 1);
            default:
                System.out.println("Not a valid direction");
                return false;

        }
    }

    /**
     * Moves the player along the x and y axis.
     *
     * @param x - Left or Right movement
     * @param y - Up or Down movement
     * @return
     */
    public boolean move(int x, int y) {

        boolean cost;

        Tile nextMove = board.get(playerPosition.getRow() + x, playerPosition.getCol() + y);

        if (nextMove != null && nextMove.isAccessible()) {
            cost = true;
            if (nextMove.getRoom() != null && playerPosition.getRoom() != null) {
                cost = false;

            }
            playerPosition.setContains(null);
            playerPosition = nextMove;
            playerPosition.setContains(this);
        } else {
            System.out.println("The player cannot access this tile.");
            cost = false;

        }
        return cost;
    }

    /**
     * Moves the player to the specified tile.
     *
     * @param tile - Where the player will move to
     */
    public void movePlayer(Tile tile) {
        playerPosition.setContains(null);
        playerPosition = tile;
        playerPosition.setContains(this);
    }

    /**
     * @param b - Sets whether the player has been moved by a suggestion.
     */
    public void setJustMoved(boolean b) {
        moved = b;
    }

    /**
     * @return Returns whether the player has been moved by a suggestion.
     */
    public boolean getJustMoved() {
        return moved;
    }

    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the position of the player
     */
    public Tile getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Adds a card to a player's hand.
     *
     * @param card
     */
    public void addHand(Card card) {
        playersHand.add(card);
    }

    /**
     * Checks if the player's hand contains a specific card.
     *
     * @param card
     * @return - if the card is found
     */
    public boolean handContains(Card card) {
        return playersHand.contains(card);
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
     * @return whether a player has lost.
     */
    public boolean lost() {
        return lost;
    }

    /**
     * Calls for player to lose.
     */
    public void lose() {
        lost = true;
    }

    /**
     * Finds if the character card matches the player's character.
     *
     * @param card - The card provided.
     * @return if player is the character.
     */
    public boolean equals(Card card) {
        return charCard.equals(card);
    }

    /**
     * Finds if the player matches the player's character.
     *
     * @param player - The player being compared.
     * @return if player is the character.
     */
    public boolean equals(Player player) {
        return player.getPlayerName().equals(playerName);
    }

    /**
     * @return the cards in the player's hand.
     */
    public ArrayList<Card> getHand() {
        return playersHand;
    }

    /**
     * Returns player's board symbol
     *
     * @return - The symbol of the player
     */
    public String getPrint() {
        return print;
    }

    /**
     * Sets the player's symbol.
     *
     * @param p - The symbol of the player.
     */
    public void setPrint(String p) {
        this.print = p;
    }
}







