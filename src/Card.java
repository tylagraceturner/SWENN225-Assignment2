/**
 * Abstract class for the three kinds of cards in the game of Cluedo - Weapon, Character and Room cards.
 */

public abstract class Card {
    //Card Attributes
    private String name;

    //CONSTRUCTOR
    public Card(String aName) {
        name = aName;
    }

    //INTERFACE

    /**
     * @param aName, setting the name of the card,
     * @return wasSet, whether it was set or not
     */
    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    /**
     * @return card name
     */
    public String getName() {
        return name;
    }

    /**
     * @return name as print
     */
    public String getPrint() {
        return this.getName();
    }

    /**
     * @param c, checking if the object is a card
     */
    public abstract boolean equals(Card c);

    /**
     * getting the card type
     */
    public abstract String getType();

    /**
     * @return a super string for better visual representation
     */
    public String toString() {
        return super.toString() + "[" +
                "name" + ":" + getName() + "]";
    }
}