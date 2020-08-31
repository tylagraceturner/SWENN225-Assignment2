/**
 * Represents the character cards in Cluedo.
 */
public class CharacterCard extends Card {
    //CharacterCard Attributes
    private int xPos;
    private int yPos;

    //CONSTRUCTOR
    public CharacterCard(String aName, int aXPos, int aYPos) {
        super(aName);
        xPos = aXPos;
        yPos = aYPos;
    }

    //INTERFACE


    /**
     * @return the x position of the character card
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return the y position of the character card
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * @return character name as print
     */
    public String getPrint() {
        return "\nCharacter: " + this.getName();
    }

    /**
     * @param c, checks if right card is found
     */
    public boolean equals(Card c) {
        if (c instanceof CharacterCard && c.getName().equals(this.getName())) {
            return true;
        }
        return false;
    }

    /**
     * @return the card type
     */
    public String getType() {
        return "Character";
    }

    /**
     * @return super string for better visual representation
     */
    public String toString() {
        return super.toString() + "[" +
                "xPos" + ":" + getXPos() + "," +
                "yPos" + ":" + getYPos() + "]";
    }
}