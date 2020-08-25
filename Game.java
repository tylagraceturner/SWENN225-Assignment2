import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The main class for running the Cluedo Game. Responsible for the key functions involved in the game.
 */
public class Game {
    //MEMBER VARIABLES
    private final int boardWidth = 24;
    private Board board = new Board();
    private int numberOfPlayers;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Card> tempDeck = new ArrayList<>();
    private ArrayList<Suggestion> accusations = new ArrayList<>();
    private Suggestion murder;

    static JFrame f;
    private static final Insets insetsData = new Insets(2, 2, 2, 2);


    //CONSTRUCTOR
    public Game() {
        initialiseBoard();
    }

    //INTERFACE

    /**
     * Creates suggestion for player
     *
     * @param p     the player
     * @param input the scanner to take input
     * @return the created suggestion
     */
    private Suggestion getAccusation(Player p, Scanner input) {
        Card roomC = null;
        Card weaponC = null;
        Card characterC = null;

        int i = 0;
        System.out.println("Pick ONE card of each type to make a Suggestion:");
        for (Card c : deck) {
            System.out.println(i + ": " + c.getName() + " (" + c.getType() + ")");
            i++;
        }

        //checks if the room card is a instance of the roomCard class
        System.out.println("Pick a Room Card: ");
        while (!(roomC instanceof RoomCard)) {
            try {
                roomC = deck.get(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid");
                return null;
            }
        }

        //checks if the weapon card is a instance of the weaponCard class
        System.out.println("Pick a Weapon Card: ");
        while (!(weaponC instanceof RoomCard)) {
            try {
                weaponC = deck.get(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid");
                return null;
            }
        }

        //checks if the character card is a instance of the characterCard class
        System.out.println("Pick a Character Card: ");
        while (!(characterC instanceof RoomCard)) {
            try {
                characterC = deck.get(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid");
                return null;
            }
        }
        return new Suggestion(p, true, (WeaponCard) weaponC, (RoomCard) roomC, (CharacterCard) characterC); // Get the accusation
    }

    /**
     * Refutes suggestion for player
     *
     * @param s     the suggestion to be refuted
     * @param input scanner to parse inputs
     * @return true if the suggestion has been refuted
     */
    public boolean refuteSuggestion(Suggestion s, Scanner input) {
        for (Player p : players) {
            if (p.equals(s.getSuggestingPlayer())) {
                continue;
            }
            boolean canRefute = false;
            ArrayList<Card> refuteList = new ArrayList<>();

            //checking if the card in hand of player contains the card apposed,
            //if player has card, refuse the accusation, else accuse
            for (Card c : p.getHand()) {
                if (s.contains(c)) {
                    canRefute = true;
                    refuteList.add(c);
                }
            }

            //displays the refute card.
            if (refuteList.size() > 0) {
                System.out.println("It is " + p.getPlayerName() + "'s (Player " + (p.getPrint()) + ") turn to refute");
                printCards(refuteList);
                System.out.println("The card you wish to refute with: ");

                String in = input.nextLine();
                Card c = refuteList.get(Integer.parseInt(in));
                if (s.contains(c)) {
                    return true;
                }
            } else {
                System.out.println(p.getPlayerName() + " cannot refute");

            }
        }
        return false;
    }

    public void redraw() {
        System.out.flush(); //write any data stored in the output buffer. Prevents data loss.
        board.draw();
    }


    /**
     * Generate new board
     */
    public void initialiseBoard() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[1].length; j++) {
                board.getBoard()[i][j] = new Tile(i, j);
            }
        }

        initialiseRooms();
        initialiseCards();
    }

    /**
     * Generates Rooms
     */
    public void initialiseRooms() {

        rooms.add(new Room("Kitchen", board, "K"));
        rooms.add(new Room("Ball Room", board, "B"));
        rooms.add(new Room("Conservatory", board, "C"));
        rooms.add(new Room("Billiard Room", board, "I"));
        rooms.add(new Room("Library", board, "L"));
        rooms.add(new Room("Study", board, "S"));
        rooms.add(new Room("Hall", board, "H"));
        rooms.add(new Room("Lounge", board, "O"));
        rooms.add(new Room("Dining Room", board, "D"));

        parseLayout();

    }

    /**
     * Adds the appropriate tiles to their respective rooms based on the layout string
     * The layout string represents the board with each character representing a different room
     * 'X' represents inaccessible areas of the map, such as walls
     * '_' represents accessible tiles that are not part of a room
     * 'E' represents the end of the string
     */
    public void parseLayout() {
        String layout =
                "XXXXXXXXX_XXXX_XXXXXXXXX" +
                        "XXXXXXX___XXXX___XXXXXXX" +
                        "XKKKKX__XXXBBXXX__XCCCCX" +
                        "XKKKKX__XBBBBBBX__XCCCCX" +
                        "XKKKKX__XBBBBBBX__CCCCCX" +
                        "XXKKKX__BBBBBBBB___XXXXX" +
                        "XXXXKX__XBBBBBBX________" +
                        "________XBXXXXBX_______X" +
                        "X_________________XXXXXX" +
                        "XXXXX_____________IIIIIX" +
                        "XDDDXXXX__XXXXX___XIIIIX" +
                        "XDDDDDDX__XXXXX___XIIIIX" +
                        "XDDDDDDD__XXXXX___XXXXIX" +
                        "XDDDDDDX__XXXXX________X" +
                        "XDDDDDDX__XXXXX___XXLXXX" +
                        "XXXXXXDX__XXXXX__XXLLLLX" +
                        "X_________XXXXX__LLLLLLX" +
                        "_________________XXLLLLX" +
                        "X________XXHHXX___XXXXXX" +
                        "XXXXXOX__XHHHHX_________" +
                        "XOOOOOX__XHHHHH________X" +
                        "XOOOOOX__XHHHHX__XSXXXXX" +
                        "XOOOOOX__XHHHHX__XSSSSSX" +
                        "XOOOOOX__XHHHHX__XSSSSSX" +
                        "XXXXXXX_XXXXXXXX_XXXXXXXE";

        for (int i = 0; layout.charAt(i) != 'E'; i++) {
            if (layout.charAt(i) == 'X') {
                board.getBoard()[i / boardWidth][i % boardWidth].setAccessible();
                board.getBoard()[i / boardWidth][i % boardWidth].setPrint("+");
            } else {
                for (Room r : rooms) {
                    if (layout.charAt(i) == r.getPrint().charAt(0)) {
                        r.addTile(board.getBoard()[i / boardWidth][i % boardWidth]);
                    }
                }
            }
        }

        for (Room r : rooms) {
            r.setPrint();
        }
    }

    /**
     * Generates and adds cards to the deck
     */
    private void initialiseCards() {

        //Character cards
        deck.add(new CharacterCard("Col Mustard", 17, 0));
        deck.add(new CharacterCard("Mrs White", 0, 9));
        deck.add(new CharacterCard("Prof Plum", 19, 23));
        deck.add(new CharacterCard("Miss Scarlett", 24, 7));
        deck.add(new CharacterCard("Mr Green", 0, 14));
        deck.add(new CharacterCard("Mrs Peacock", 6, 23));

        //Weapon Cards
        deck.add(new WeaponCard("Rope"));
        deck.add(new WeaponCard("Dagger"));
        deck.add(new WeaponCard("Lead Pipe"));
        deck.add(new WeaponCard("Spanner"));
        deck.add(new WeaponCard("Revolver"));
        deck.add(new WeaponCard("Candlestick"));

        //Room Cards
        deck.add(new RoomCard("Hall"));
        deck.add(new RoomCard("Billiard Room"));
        deck.add(new RoomCard("Conservatory"));
        deck.add(new RoomCard("Library"));
        deck.add(new RoomCard("Ball Room"));
        deck.add(new RoomCard("Study"));
        deck.add(new RoomCard("Dining Room"));
        deck.add(new RoomCard("Lounge"));
        deck.add(new RoomCard("Kitchen"));

    }

    /**
     * Generates the murder scene
     *
     * @return a suggestion containing the murder weapon, the locations of the murder and the identity of the murderer
     */
    private Suggestion createMurderer() {
        WeaponCard mWeapon;
        RoomCard mRoom;
        CharacterCard the_murderer;

        int i = (int) (Math.random() * tempDeck.size());
        while (!(tempDeck.get(i) instanceof WeaponCard)) {
            i = (int) (Math.random() * tempDeck.size());

        }

        mWeapon = (WeaponCard) tempDeck.get(i);
        tempDeck.remove(mWeapon);

        i = (int) (Math.random() * tempDeck.size());
        while (!(tempDeck.get(i) instanceof RoomCard)) {
            i = (int) (Math.random() * tempDeck.size());

        }

        mRoom = (RoomCard) tempDeck.get(i);
        tempDeck.remove(mRoom);

        i = (int) (Math.random() * tempDeck.size());
        while (!(tempDeck.get(i) instanceof CharacterCard)) {
            i = (int) (Math.random() * tempDeck.size());
        }

        the_murderer = (CharacterCard) tempDeck.get(i);
        tempDeck.remove(the_murderer);

        return new Suggestion(mWeapon, mRoom, the_murderer);
    }

    /**
     * Deals cards to players
     */
    private void dealCards(int numberOfPlayers) {
        int i = 0;
        while (!tempDeck.isEmpty()) {
            Card c = tempDeck.get((int) (Math.random() * tempDeck.size()));
            players.get(i).addHand(c);
            tempDeck.remove(c);
            i++;
            if (i == numberOfPlayers) {
                i = 0;
            }
        }
    }


    /**
     * Allows player to create suggestion
     *
     * @param p     the player making the suggestion
     * @param input the scanner to take input
     * @return the created suggestion
     */

    private Suggestion suggest(Player p, Scanner input) {
        Card roomC = null;
        Card weaponC = null;
        Card characterC = null;

        int i = 0;
        System.out.println("Pick 1 card of each type to make a suggestion");
        for (Card c : deck) {
            System.out.println(i + ": " + c.getName() + " (" + c.getType() + ")");
            i++;

            if (c instanceof RoomCard && c.getName().equals(p.getPlayerPosition().getRoom().getRoomCard())) {
                roomC = c;
            }
        }

        System.out.println("The room is: " + p.getPlayerPosition().getRoom().getRoomCard());

        System.out.println("Pick 1 weapon card: ");
        while (!(weaponC instanceof WeaponCard)) {
            try {
                weaponC = deck.get(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
                return null;
            }
        }

        System.out.println("Pick 1 character card: ");
        while (!(characterC instanceof CharacterCard)) {
            try {
                characterC = deck.get(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
                return null;
            }
        }
        return new Suggestion(p, false, (WeaponCard) weaponC, (RoomCard) roomC, (CharacterCard) characterC);
    }

    /**
     * represents rolling two 6-sided dice
     *
     * @return the sum of the two dice rolls
     */
    private int rollDice() {
        return (int) ((Math.random() * 5 + 1) + (Math.random() * 5 + 1));
    }


    /**
     * Prints cards from the list
     *
     * @param cards - the list
     */
    public void printCards(List<Card> cards) {
        int i = 0;
        for (Card c : cards) {
            System.out.println(i + ": " + c.getName() + " (" + c.getType() + ")");
            i++;
        }
    }


    /**
     * @param p     - The player
     * @param input - The scanner
     * @return true if the game is over
     */
    private boolean doTurn(Player p, Scanner input) {

        System.out.println("It is " + p.getPlayerName() + "'s (Player " + (p.getPrint()) + ") turn");
        boolean moving = true;
        Room startRoom = p.getPlayerPosition().getRoom();
        boolean suggest = false;

        if (p.getJustMoved()) {//if player has moved
            p.setJustMoved(false);
            System.out.println("You can choose to move or make a suggestion.");
            System.out.println("Do you want to move? (Y/N)");

            String response = input.nextLine().toUpperCase();
            if (response.equals("N")) {
                moving = false;
                suggest = true;
            }
        }

        if (moving) {//if character is moving
            redraw();

            int numberOfMoves = rollDice();
            System.out.println("You rolled: " + numberOfMoves);

            while (numberOfMoves != 0) {//while player still has turns left
                System.out.println("Enter direction to move: (WASD)");
                System.out.println("or press 'H' to view your hand");
                System.out.println("or press 'G' to make a guess (suggestion) at the murder circumstances");

                String direction = input.nextLine();

                if (direction.toUpperCase().equals("H")) {//if player wants to see whats in hand
                    p.printHand();

                } else if (direction.toUpperCase().equals("G")) {//if the player wants to make a suggestion
                    Room currentRoom = p.getPlayerPosition().getRoom();
                    if (currentRoom != null && !currentRoom.equals(startRoom)) {
                        suggest = true;
                        numberOfMoves = 0;

                    } else {
                        System.out.println("Cannot make a suggestion from this room.");
                    }
                } else if (p.parser(direction)) {//after each move player makes on board
                    numberOfMoves--;
                    System.out.println("You have " + numberOfMoves + " moves left");
                }
                redraw();

            }
        }

        if (!suggest && p.getPlayerPosition().getRoom() != null && !p.getPlayerPosition().getRoom().equals(startRoom)) {
            //if player is in a room to make a suggestion
            System.out.println("Do you want to make a Suggestion? (Y/N)");
            String response = input.nextLine().toUpperCase();
            if (response.equals("Y")) {
                suggest = true;
            }
        }

        if (suggest) {//if player wants to suggest
            System.out.println("Your Hand: ");
            p.printHand();
            System.out.println(" ");
            Suggestion s = suggest(p, input);

            if (s == null) {
                return false;
            }

            boolean refute = refuteSuggestion(s, input);

            for (Player p1 : players) {
                if (s != null && p1.getPlayerName().equals(s.getCharacter().getName())) {
                    for (Room r : rooms) {
                        if (r.getRoomCard().equals(s.getRoom().getName())) {
                            Tile movingTo = r.getTile().stream().filter(tile -> tile.getContains() == null).findFirst().get();
                            p1.movePlayer(movingTo);
                            p1.setJustMoved(true);
                        }
                    }
                }
            }

            //if the player makes an accusation
            if (!refute) {
                assert s != null;
                s.setIsAccusation(true);
                accusations.add(s);
            }
        }

        //if the accusation is made
        if (!accusations.isEmpty()) {
            System.out.println("Do you want to make an accusation? (Y/N");
            String response = input.nextLine().toUpperCase();
            if (response.equals("Y")) {
                Suggestion accuse = getAccusation(p, input);
                if (accuse.equals(murder)) {//if the murder is found
                    System.out.println("Congratulations!");
                    return true;
                } else {//if wrongly accused
                    System.out.println("Sorry! You've Lost.");
                    p.lose();
                }
            }
        }
        return false;
    }

    /**
     * Main game
     */
    private void run() {
        //getting the user input and boundary testing it
      /*  Scanner input = new Scanner(System.in);
        do {
            System.out.println("Please enter the number of players between 3 and 6: ");
            try {
                numberOfPlayers = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid. Please enter a number between 3 and 6.");
            }
            System.out.println("You entered: " + numberOfPlayers);


       // }
      //  while (numberOfPlayers > 6 || numberOfPlayers < 3);

        //adding the players
        players.add(new Player(board, new CharacterCard("Mr Green", 0, 14)));
        players.add(new Player(board, new CharacterCard("Mrs White", 0, 9)));
        players.add(new Player(board, new CharacterCard("Mrs Peacock", 6, 23)));
        if (numberOfPlayers > 3) {
            players.add(new Player(board, new CharacterCard("Col Mustard", 17, 0)));
        }
        if (numberOfPlayers > 4) {
            players.add(new Player(board, new CharacterCard("Prof Plum", 19, 23)));
        }
        if (numberOfPlayers > 5) {
            players.add(new Player(board, new CharacterCard("Miss Scarlett", 24, 7)));
        }

        for (Player p : players) {
            p.setPrint(String.valueOf(players.indexOf(p) + 1));
        }

        tempDeck.addAll(deck);
        murder = createMurderer();
        dealCards(numberOfPlayers);

        //checking if the player has won or not
        int count = 0;
        boolean won = false;
        while (!won) {
            for (Player p : players) {
                won = doTurn(p, input);
                if (won) {
                    System.out.println("Congratulations " + p.getPlayerName() + ". You Won.");
                    System.out.println("The Murder Circumstances:\n It was " + murder.getCharacter().getName() + " in the " + murder.getRoom().getName() + " with the " + murder.getWeapon().getName() + ".");
                }
            }

            if (count == 20) {
                break;
            }
            count++;
        }

        System.out.println("Please enter the number of players between 3 and 6: ");

        numberOfPlayers = Integer.parseInt(input.nextLine());
*/

    }

    public void initialisePlayerTokens() {


    }




    public void getPlayers(){

        //COULD BE FORMATTED NICER

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("How many players would like to play?");
        JRadioButton threePlayers = new JRadioButton("3");
        JRadioButton fourPlayers = new JRadioButton("4");
        JRadioButton fivePlayers = new JRadioButton("5");
        JRadioButton sixPlayers = new JRadioButton("6");

        ButtonGroup playerOptions = new ButtonGroup();
        playerOptions.add(threePlayers);
        playerOptions.add(fourPlayers);
        playerOptions.add(fivePlayers);
        playerOptions.add(sixPlayers);

        f.add(label);
        f.add(threePlayers);
        f.add(fourPlayers);
        f.add(fivePlayers);
        f.add(sixPlayers);

        f.setSize(700,800);
        f.setLocationRelativeTo(null);
        f.setLayout(new FlowLayout());
        f.setVisible(true);

        threePlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
                f.getContentPane().removeAll();
                f.repaint();
                initialisePlayerTokens();
            }
        });

        fourPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 4;
                f.getContentPane().removeAll();
                f.repaint();
                initialisePlayerTokens();
            }
        });

        fivePlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 5;
                f.getContentPane().removeAll();
                f.repaint();
                initialisePlayerTokens();
            }
        });

        sixPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 6;
                f.getContentPane().removeAll();
                f.repaint();
                initialisePlayerTokens();
            }
        });

    }

    public void setup(){
        initialiseCards();
        initialiseRooms();
        getPlayers();
    }

    public void start(){

        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");

        f.add(startButton);
        f.add(quitButton);
        f.setSize(700,800);
        f.setLocationRelativeTo(null);
        f.setLayout(new FlowLayout());

        //-------------------                add in a welcome image here                  -------------------------


        /*

        File file = new File("PATHNAME");
        BufferedImage img;
        try {
            img = ImageIO.read(file);
            ImageIcon i = new ImageIcon(img);
            JLabel l = new JLabel();
           l.setIcon(i);
            f.add(l, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        */


        //-----------------------------------------------------------------------------------------------------------


        f.setVisible(true);

        //START BUTTON

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.getContentPane().removeAll();
                f.repaint();
                setup(); // SETUP METHOD
            }
        });


        //QUIT BUTTON

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog x = new JDialog(f, "Quit Game");
                JLabel l = new JLabel("<html>All progress will be lost.<br/>Are you sure you want to quit?</html>", SwingConstants.CENTER);
                JButton t = new JButton("Yes");
                JButton n = new JButton("No");

                t.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0); //exit the window completely, return exit status of 0
                    }
                });

                n.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.dispose();
                    }
                });

                JPanel panel = new JPanel();
                panel.add(l);
                panel.add(t);
                panel.add(n);
                x.add(panel);
                x.setSize(250, 125);
                x.setLocationRelativeTo(null);
                x.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        f = new JFrame("Cluedo");

        JMenu menu;
        JMenuItem newGame, quit;

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Game Options");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog x = new JDialog(f, "New Game");
                JLabel l = new JLabel("<html>All progress will be lost.<br/>Are you sure you want to start a new game?</html>", SwingConstants.CENTER);
                JButton t = new JButton("Yes");
                JButton n = new JButton("No");

                t.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.getContentPane().removeAll();
                        f.repaint();
                        x.dispose();
                        Game game = new Game();
                        game.run();

                    }
                });

                n.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.dispose();
                    }
                });

                JPanel panel = new JPanel();
                panel.add(l);
                panel.add(t);
                panel.add(n);
                x.add(panel);
                x.setSize(300, 125);
                x.setLocationRelativeTo(null);
                x.setVisible(true);

            }
        });

        quit = new JMenuItem("Quit Game");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog x = new JDialog(f, "Quit Game");
                JLabel l = new JLabel("<html>All progress will be lost.<br/>Are you sure you want to quit?</html>", SwingConstants.CENTER);
                JButton t = new JButton("Yes");
                JButton n = new JButton("No");

                t.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0); //exit the window completely, return exit status of 0
                    }
                });

                n.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.dispose();
                    }
                });

                JPanel panel = new JPanel();
                panel.add(l);
                panel.add(t);
                panel.add(n);
                x.add(panel);
                x.setSize(250, 125);
                x.setLocationRelativeTo(null);
                x.setVisible(true);

            }
        });

        menu.add(newGame);
        menu.addSeparator();
        menu.add(quit);

        menuBar.add(menu);
        f.setJMenuBar(menuBar);
        f.setSize(700, 800);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        Game cluedo = new Game();
        cluedo.start();

    }
}