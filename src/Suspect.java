/**
 * Represents the suspected character in Cluedo.
 */
public class Suspect extends Card {
    //Constructor
    public Suspect(String name) {
        super(name);
    }

    @Override
    /**
     * @param c
     * @return false
     */
    public boolean equals(Card c) {
        return false;
    }

    @Override
    /**
     * @return type of object
     */
    public String getType() {
        return null;
    }

}