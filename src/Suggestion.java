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

    //Constructor A
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

    //Constructor B
    public Suggestion(WeaponCard aWeapon, RoomCard aRoom, CharacterCard aCharacter) {
        suggestingPlayer = null;
        isAccusation = false;
        weapon = aWeapon;
        room = aRoom;
        character = aCharacter;
    }

    //INTERFACE

    /**
     * Setting the selected accusation as the accusation
     * @param isAccusation, true or false
     * @return wasSet, whether it was true or false
     * */
    public boolean setIsAccusation(boolean isAccusation) {
        boolean wasSet = false;
        this.isAccusation = isAccusation;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setting the selected weapon as the weapon
     * @param weapon, true or false
     * @return wasSet, whether it was true or false
     * */
    public boolean setWeapon(WeaponCard weapon) {
        boolean wasSet = false;
        this.weapon = weapon;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setting the selected room as the room
     * @param room, true or false
     * @return wasSet, whether it was true or false
     * */
    public boolean setRoom(RoomCard room) {
        boolean wasSet = false;
        this.room = room;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setting the selected character as the character
     * @param character, true or false
     * @return wasSet, whether it was true or false
     * */
    public boolean setCharacter(CharacterCard character) {
        boolean wasSet = false;
        this.character = character;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setting the selected player as the player
     * @param suggestingPlayer, true or false
     * @return wasSet, whether it was true or false
     * */
    public boolean setSuggestingPlayer(Player suggestingPlayer) {
        boolean wasSet = false;
        this.suggestingPlayer = suggestingPlayer;
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
     * @param newWeaponCard, assigning card
     * @return wasSet
     */
    public boolean setWeaponCard(WeaponCard newWeaponCard) {
        boolean wasSet = false;
        if (newWeaponCard != null) {
            weaponCard = newWeaponCard;
            wasSet = true;
        }
        return wasSet;
    }

    /**
     * @param newCharacterCard, assigning card
     * @return wasSet
     */
    public boolean setCharacterCard(CharacterCard newCharacterCard) {
        boolean wasSet = false;
        if (newCharacterCard != null) {
            characterCard = newCharacterCard;
            wasSet = true;
        }
        return wasSet;
    }

    /**
     * @param newRoomCard, assigning card
     * @return wasSet
     */
    public boolean setRoomCard(RoomCard newRoomCard) {
        boolean wasSet = false;
        if (newRoomCard != null) {
            roomCard = newRoomCard;
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