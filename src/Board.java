import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Cluedo GUI Form.
 */
public class Board {
    //MEMBER VARIABLES
    String[][] board =new String[26][25];

    /**
     * Sets up the board
     * Explains how it works
     * @param characters, List of the characters
     */
    public void board(List<Item> characters){
        System.out.println();
        System.out.println();
        System.out.println("How Our Version Of Cluedo works:");
        System.out.println("The room walls are shown by '+'");//MAY CHANGE IDK
        System.out.println("The doors are labeled T,B,R,L depending on their position");
        System.out.println("T - Door at the TOP of square");
        System.out.println("B - Door at the BOTTOM of square)");
        System.out.println("R - Door at the RIGHT of square");
        System.out.println("L - Door at the LEFT of square");
        System.out.println("Lowercase letters are used to help identify which room you are in");
        System.out.println("lou - Lounge,   hall - Hall,   stud - Study,   lib - Library,   bill - Billard Room");
        System.out.println("con - Conservatory,   ball - Ball Room,   kit - Kitchen,   din - Dining Room");
        System.out.println("'X' on a square means it is out of bounds");
        System.out.println();
        System.out.println();

        //set starting position of characters
        for(Item character : characters) {
            //Starting Scarlet
            if (character.name.equals("Miss Scarlett")) {
                board[1][8] = "S";
                character.x = 1;
                character.y = 8;
                character.item = "S";
            }
            //Starting Plum
            if (character.name.equals("Professor Plum")) {

                board[6][24]= "P";
                character.x = 6;
                character.y = 24;
                character.item = "P";
            }
            //Starting Peacock
            if (character.name.equals("Mrs. Peacock")) {
                board[19][24] = "C";
                character.x = 19;
                character.y = 24;
                character.item = "C";
            }
            //Starting Green
            if (character.name.equals("Mr. Green")) {
                board[25][15] = "G";
                character.x = 25;
                character.y = 15;
                character.item = "G";
            }
            //Starting White
            if (character.name.equals("Mrs. White")) {
                board[25][10] = "W";
                character.x = 25;
                character.y = 10;
                character.item = "W";
            }
            //Starting Mustard
            if (character.name.equals("Colonel Mustard")) {
                board[8][1]="M";
                character.x = 8;
                character.y = 1;
                character.item = "M";
            }
        }

        //Showing Lounge
        for(int i=0; i<7; i++){
            for(int j=0; j<8; j++){
                if(i==6||i==1||j==1||j==7)
                    board[i][j]="+";
            }
        }
        //Door at top
        board[6][7]="T";
        board[2][6]="+";

        //labelling lounge
        board[4][3]="l";
        board[4][4]="o";
        board[4][5]="u";

        board[1][7]="X";
        board[1][9]="X";


        //Showing Hall
        for(int i=0; i<8; i++){
            for(int j=10; j<16; j++){
                if(j==10||j==15 || i==1||i==7)
                    board[i][j]="+";
            }
        }
        //Labelling doors
        board[7][12]="T";
        board[7][13]="T";
        board[5][15]="R";

        //labelling hall
        board[4][12]="h";
        board[4][13]="a";
        /***/
        board[4][14]="l";
        board[4][15]="l";


        board[1][16]="X";

        //Showing Study
        for(int i=0; i<5; i++){
            for(int j=18; j<25; j++){
                if(j==18||j==24 || i==1||i==4)
                    board[i][j]="+";
            }
        }
        //Labelling doors
        board[4][18]="T";

        board[2][19]="+";
        board[1][18]="X";
        board[5][24]="X";

        //labelling study
        board[3][20]="s";
        board[3][21]="t";
        board[3][22]="u";
        /***/
        board[3][23]="d";

        //Showing Library
        for(int i=7; i<12; i++){
            for(int j=18; j<25; j++){
                if(j==18||j==24 || i==7||i==11)
                    board[i][j]="+";
            }
        }
        //Labelling door
        board[9][18]="L";
        board[11][21]="T";

        board[8][19]="+";
        board[7][18]="_";
        board[10][19]="+";
        board[11][18]="_";

        board[8][23]="+";
        board[7][24]="X";
        board[10][23]="+";
        board[11][24]="X";


        //labelling Library
        board[9][20]="l";
        board[9][21]="i";
        board[9][22]="b";

        //Showing Billard Room
        for(int i=13; i<18; i++){
            for(int j=19; j<25; j++){
                if(j==19||j==24 || i==13||i==17)
                    board[i][j]="+";
            }
        }
        //Labelling door
        board[13][23]="B";
        board[16][19]="L";

        //labelling Billard Room
        board[15][21]="b";
        board[15][22]="i";
        board[15][23]="l";
        /***/
        board[15][24]="l";

        board[12][24]="X";
        board[18][24]="X";

        //Showing conservatory room
        for(int i=20; i<25; i++){
            for(int j=19; j<25; j++){
                if(j==19||j==24 || i==20||i==24)
                    board[i][j]="+";
            }
        }
        //labelling door
        board[21][19]="B";

        board[21][23]="+";
        board[20][19]="_";
        board[24][18]="X";
        board[20][24]="X";
        for(int i=0; i<25; i++){
            board[25][i]="X";
        }
        //labelling conservatory
        board[22][21]="c";
        board[22][22]="o";
        /***/
        board[22][23]="n";

        //Showing ballroom
        for(int i=18; i<25; i++){
            for(int j=9; j<17; j++){
                if(j==9||j==16 || i==18||i==24)
                    board[i][j]="+";
            }
        }
        //labelling doors
        board[18][10]="B";
        board[18][15]="B";
        board[20][9]="L";
        board[20][16]="R";

        //labelling ballroom
        board[21][11]="b";
        board[21][12]="a";
        board[21][13]="l";
        board[21][14]="l";

        board[24][9]="_";
        board[24][10]="_";
        board[23][9]="+";
        board[23][10]="+";
        board[23][11]="+";

        board[24][15]="_";
        board[24][16]="_";
        board[23][14]="+";
        board[23][15]="+";
        board[23][16]="+";

        //showing kitchen
        for(int i=19; i<25; i++){
            for(int j=0; j<7; j++){
                if(j==1||j==6 || i==19||i==24)
                    board[i][j]="+";
            }
        }
        //Labelling door
        board[19][5]="B";

        board[20][2]="+";
        board[17][1]="X";
        board[19][1]="X";
        board[24][7]="X";

        //labelling kitchen
        board[22][3]="k";
        board[22][4]="i";
        /***/
        board[22][5]="t";

        //Showing Dining Room
        for(int i=10; i<17; i++){
            for(int j=0; j<9; j++){
                if(j==1||j==8 || i==10||i==16)
                    board[i][j]="+";
            }
        }
        //Labelling door
        board[10][7]="B";
        board[13][8]="R";

        board[15][5]="+";
        board[15][6]="+";
        board[15][7]="+";
        board[16][8]="_";
        board[16][7]="_";
        board[16][6]="_";
        board[9][1]="x";
        board[7][1]="x";

        //Labeling dining room
        board[13][3]="d";
        board[13][4]="i";
        board[13][5]="n";

        for(int i=9; i<16; i++){
            for(int j=11; j<16; j++){
                if(j==15||j==11 || i==9||i==15)
                    board[i][j]="+";
            }
        }
    }

    /**
     * Printing the board in text form.
     * @param characters List of characters for updating their positions.
     * @param weapons List of all the weapons for updating positions
     * @param npcPys List of non playing characters, updating their positions.
     */
    public void draw(List<Item> characters, JFrame jf, List<Item> weapons, List<Item> npcPys){

        System.out.println("    A B C D E F G H I J K L M N O P Q R S T U V W X");
        System.out.println(" ");
        for(Item player :characters) board[player.x][player.y]=player.item;

        String rows = "";
        for (int row = 25; row != 0; row--) {
            if(row<10) rows +=" ";
            rows += row+" |";

            for (int col = 1; col <= 24; col++) {
                String player = board[row][col];
                if (player != null) rows += player + "|";
                else rows += "_|";
            }

            rows +=" "+ row+"\n";
        }

        System.out.printf(rows);
        System.out.println(" ");
        System.out.println("    A B C D E F G H I J K L M N O P Q R S T U V W X");

        JLayeredPane layeredPane=new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(420, 451));
        layeredPane.setBorder(BorderFactory.createTitledBorder("Board"));

        File file = new File("images/board.png");
        BufferedImage bi;

        try {
            bi = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(bi);
            JLabel jLabel = new JLabel();
            jLabel.setIcon(icon);
            jLabel.setBounds(6, 16,
                    icon.getIconWidth(),
                    icon.getIconHeight());

            layeredPane.add(jLabel, 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //getting the character images
        for(Item character : characters) {
            file = new File("images/character_"+ character.item+".png");
            try {
                bi = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(bi);
                //  f.setLayout(new FlowLayout());
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                int x = 424-((character.x-1)*17);
                int y = 6+((character.y-1)*17);
                lbl.setBounds(y,x,
                        icon.getIconWidth(),
                        icon.getIconHeight());
                layeredPane.add(lbl, 0);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //getting the images of npc's
        for(Item npc : npcPys) {
            file = new File("images/character_"+npc.item+".png");
            try {
                bi = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(bi);
                //  f.setLayout(new FlowLayout());
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                int x=424-((npc.x-1)*17);
                int y=6+((npc.y-1)*17);
                lbl.setBounds(y,x,
                        icon.getIconWidth(),
                        icon.getIconHeight());
                layeredPane.add(lbl, 0);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //getting the weapon images
        for(Item w: weapons) {
            file = new File(w.item);
            try {
                bi = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(bi);
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                int x=424-((w.x-1)*17);
                int y=6+((w.y-1)*17);
                lbl.setBounds(y,x,
                        icon.getIconWidth(),
                        icon.getIconHeight());
                layeredPane.add(lbl, 0);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        jf.add(layeredPane);
    }

    boolean visited[][]=new boolean[26][25];//change values?
    /**
     * PathFinding makes all squares other than null or _  turned into visited
     * Queue Class is used for queueing the items
     * Adding players starting positions into the queue
     * Runs until either destination found or if it reaches the end
     * Runs through all sorts of posibilites for alternative paths
     * @param startX 	starting x position
     * @param startY	starting y position
     * @param endX		end destination x
     * @param endY		end destination y
     * @return the minimum number of squares needed to get to the final destination
     */
    int pathFinding(int startX, int startY,int endX,int endY){
        for (int row = 25; row != 0; row--) {
            for (int col = 1; col <= 24; col++) {
                String player = board[row][col];
                if (player != null && player != "_") visited[row][col] = true;
                else                                 visited[row][col] = false;
            }
        }

        Queue<QueueItem> queue = new LinkedList<QueueItem>();
        QueueItem startingPos =new QueueItem(startX,startY,0);
        queue.add(startingPos);
        visited[startingPos.row][startingPos.col] = true;
        while (!queue.isEmpty()) {
            QueueItem queuePlayer = queue.peek();
            queue.poll();

            //Destination found;
            if (queuePlayer.row==endX&& queuePlayer.col==endY) return queuePlayer.dist;

            //moving up the board
            if (queuePlayer.row - 1 >= 0 &&
                    visited[queuePlayer.row - 1][queuePlayer.col] == false) {
                queue.add(new QueueItem(queuePlayer.row - 1, queuePlayer.col, queuePlayer.dist + 1));
                visited[queuePlayer.row - 1][queuePlayer.col] = true;
            }

            // moving down the board
            if (queuePlayer.row + 1 < 26 && visited[queuePlayer.row + 1][queuePlayer.col] == false) {
                queue.add(new QueueItem(queuePlayer.row + 1, queuePlayer.col,   queuePlayer.dist + 1));
                visited[queuePlayer.row + 1][queuePlayer.col] = true;
            }

            // moving left on the board
            if (queuePlayer.col - 1 >= 0 &&
                    visited[queuePlayer.row][queuePlayer.col - 1] == false) {
                queue.add(new QueueItem(queuePlayer.row, queuePlayer.col - 1, queuePlayer.dist + 1));
                visited[queuePlayer.row][queuePlayer.col - 1] = true;
            }

            // moving right on the board
            if (queuePlayer.col + 1 < 25 &&
                    visited[queuePlayer.row][queuePlayer.col + 1] == false) {
                queue.add(new QueueItem(queuePlayer.row, queuePlayer.col + 1, queuePlayer.dist + 1));
                visited[queuePlayer.row][queuePlayer.col + 1] = true;
            }
        }
        //reached the end
        return -1;
    }
}
