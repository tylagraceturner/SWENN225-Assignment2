/**
 * Represents the weapon cards in Cluedo.
 */
public class Weapon extends Card{
    //Constructor
    public Weapon(String name) {
        // TODO Auto-generated constructor stub
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
     * @return card type
     */
    public String getType() {
        return null;
    }
}
