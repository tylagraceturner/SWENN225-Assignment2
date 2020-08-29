/**
 * Represents the room cards in Cluedo.
 */
public class RoomCard extends Card {
    //CONSTRUCTOR
    public RoomCard(String aName) {
        super(aName);
    }

    //INTERFACE
    /**
     * @return character name as print
     */
    public String getPrint() {
        return "\n     Room: " + this.getName();
    }

    /**
     * @param c, cheking if card in hand is the same as in room
     */
    public boolean equals(Card c) {
        if (c instanceof RoomCard && c.getName().equals(this.getName())) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * @return the card type
     */
    public String getType() {
        return "Room";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}