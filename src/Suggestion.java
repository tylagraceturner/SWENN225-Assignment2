/**
 * Represents the ability to make a suggestion/accusation in Cluedo.
 */
public class Suggestion {
    //Suggestion Attributes
    private boolean isAccusation;
    private WeaponCard weapon;
    private RoomCard room;
    private CharacterCard character;
    private Player suggestingPlayer;

    //Suggestion Associations
    private WeaponCard weaponCard;
    private CharacterCard characterCard;
    private RoomCard roomCard;

    //CONSTRUCTORS
    public Suggestion(Player aSuggestingPlayer, boolean aIsAccusation, WeaponCard aWeapon, RoomCard aRoom, CharacterCard aCharacter) {
        isAccusation = aIsAccusation;
        weapon = aWeapon;
        room = aRoom;
        character = aCharacter;
        suggestingPlayer = aSuggestingPlayer;
        if (!setWeaponCard(aWeapon)) {
            throw new RuntimeException("Unable to create Suggestion due to aWeaponCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        if (!setCharacterCard(aCharacter)) {
            throw new RuntimeException("Unable to create Suggestion due to aCharacterCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        if (!setRoomCard(aRoom)) {
            throw new RuntimeException("Unable to create Suggestion due to aRoomCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    public Suggestion(WeaponCard aWeapon, RoomCard aRoom, CharacterCard aCharacter) {
        suggestingPlayer = null;
        isAccusation = false;
        weapon = aWeapon;
        room = aRoom;
        character = aCharacter;
    }

    //INTERFACE

    //setting the selected accusation as the accusation
    public boolean setIsAccusation(boolean aIsAccusation) {
        boolean wasSet = false;
        isAccusation = aIsAccusation;
        wasSet = true;
        return wasSet;
    }

    //setting the selected weapon as the weapon
    public boolean setWeapon(WeaponCard aWeapon) {
        boolean wasSet = false;
        weapon = aWeapon;
        wasSet = true;
        return wasSet;
    }

    //setting the selected room as the room
    public boolean setRoom(RoomCard aRoom) {
        boolean wasSet = false;
        room = aRoom;
        wasSet = true;
        return wasSet;
    }

    //setting the selected character as the character
    public boolean setCharacter(CharacterCard aCharacter) {
        boolean wasSet = false;
        character = aCharacter;
        wasSet = true;
        return wasSet;
    }

    //setting the player as the suggested player
    public boolean setSuggestingPlayer(Player aSuggestingPlayer) {
        boolean wasSet = false;
        suggestingPlayer = aSuggestingPlayer;
        wasSet = true;
        return wasSet;
    }

    /**
     * @return if it is a accusation or not
     */
    public boolean getIsAccusation() {
        return isAccusation;
    }

    /**
     * @return weapon
     */
    public WeaponCard getWeapon() {
        return weapon;
    }

    /**
     * @return room
     */
    public RoomCard getRoom() {
        return room;
    }

    /**
     * @return character
     */
    public CharacterCard getCharacter() {
        return character;
    }

    /**
     * @return suggested player
     */
    public Player getSuggestingPlayer() {
        return suggestingPlayer;
    }

    /**
     * @return weapon card
     */
    public WeaponCard getWeaponCard() {
        return weaponCard;
    }

    /**
     * @return character card
     */
    public CharacterCard getCharacterCard() {
        return characterCard;
    }

    /**
     * @return room card
     */
    public RoomCard getRoomCard() {
        return roomCard;
    }

    /**
     * @param aNewWeaponCard, assigning card
     * @return wasSet
     */
    public boolean setWeaponCard(WeaponCard aNewWeaponCard) {
        boolean wasSet = false;
        if (aNewWeaponCard != null) {
            weaponCard = aNewWeaponCard;
            wasSet = true;
        }
        return wasSet;
    }

    /**
     * @param aNewCharacterCard, assigning card
     * @return wasSet
     */
    public boolean setCharacterCard(CharacterCard aNewCharacterCard) {
        boolean wasSet = false;
        if (aNewCharacterCard != null) {
            characterCard = aNewCharacterCard;
            wasSet = true;
        }
        return wasSet;
    }

    /**
     * @param aNewRoomCard, assigning card
     * @return wasSet
     */
    public boolean setRoomCard(RoomCard aNewRoomCard) {
        boolean wasSet = false;
        if (aNewRoomCard != null) {
            roomCard = aNewRoomCard;
            wasSet = true;
        }
        return wasSet;
    }

    /**
     * removing all cards assigned
     */
    public void delete() {
        weaponCard = null;
        characterCard = null;
        roomCard = null;
    }

    /**
     * @param s, suggestion card
     * @return if the cards are same as the ones required
     */
    public boolean equals(Suggestion s) {
        return this.getWeapon().equals(s.getWeapon()) &&
                this.getRoom().equals(s.getRoom()) &&
                this.getCharacter().equals(s.getCharacter());
    }

    /**
     * @param c, selected card
     * @return if the cards are the same in each state
     */
    public boolean contains(Card c) {
        return weapon.equals(c) || room.equals(c) || character.equals(c);
    }

    /**
     * @return super string, easier to read
     */
    public String toString() {
        return super.toString() + "[" +
                "isAccusation" + ":" + getIsAccusation() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "weapon" + "=" + (getWeapon() != null ? !getWeapon().equals(this) ? getWeapon().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "room" + "=" + (getRoom() != null ? !getRoom().equals(this) ? getRoom().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "character" + "=" + (getCharacter() != null ? !getCharacter().equals(this) ? getCharacter().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "suggestingPlayer" + "=" + (getSuggestingPlayer() != null ? !getSuggestingPlayer().equals(this) ? getSuggestingPlayer().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "weaponCard = " + (getWeaponCard() != null ? Integer.toHexString(System.identityHashCode(getWeaponCard())) : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "characterCard = " + (getCharacterCard() != null ? Integer.toHexString(System.identityHashCode(getCharacterCard())) : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "roomCard = " + (getRoomCard() != null ? Integer.toHexString(System.identityHashCode(getRoomCard())) : "null");
    }
}