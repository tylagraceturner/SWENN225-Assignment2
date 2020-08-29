/**
 * Represents the weapon cards in Cluedo.
 */
public class WeaponCard extends Card {
    //CONSTRUCTOR
    public WeaponCard(String aName) {
        super(aName);
    }

    //INTERFACE
    /**
     * @return weapon name as print
     */
    public String getPrint() {
        return "\n   Weapon: " + this.getName();
    }

    /**
     * @param c, checking if the weapon card is equal to the card required
     * @return true or false if card is present
     */
    public boolean equals(Card c) {
        if (c instanceof WeaponCard && c.getName().equals(this.getName())) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * @return type of object
     */
    public String getType() {
        return "Weapon";
    }

}