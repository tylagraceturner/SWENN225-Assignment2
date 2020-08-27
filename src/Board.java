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
 * This class represents the grid-like game board for CLuedo.
 */
public class Board {
    //MEMBER VARIABLES
   /* private Tile[][] board;

    //CONSTRUCTOR
    public Board() {
        board = new Tile[25][24];
    } //25x24

    //INTERFACE

    /**
     * @returns the board
     */
   /* public Tile[][] getBoard() {
        return board;
    }

    /**
     * Draws the board in text format using System.out.
     */
   /* public void draw() {
        String out = "";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[1].length; y++) {
                out = out.concat(" " + board[x][y].getPrint());
            }
            out = out.concat("\n");
        }
        System.out.print(out);
    }

    /**
     * Gets the tile requested.
     *
     * @param x
     * @param y
     * @returns the tile, null if it doesn't exist.
     */
   /* public Tile get(int x, int y) {
        if (x < board.length && x >= 0 && y < board[0].length && y >= 0) {
            return board[x][y];
        }
        return null;
    } /*

    */






        String[][] board =new String[26][25];

        /*
         * Sets up the board
         * Explains how it works
         * @param players	List of players
         */
        public void board(List<Item> players){
            System.out.println();
            System.out.println();
            System.out.println("How the game board works:");
            System.out.println("The room walls are shown by *");
            System.out.println("The doors are T,B,R,L depending on their position");
            System.out.println("T stands for top meaning there is a door at the top of that square");
            System.out.println("B stands for bottom (door at bottom of square)");
            System.out.println("L stands for left meaning there is a door at the left of that square");
            System.out.println("R stands for right (door at right of square)");
            System.out.println("The lowercase letters help identify what room you are in.");
            System.out.println("lou- Lounge   ha- Hall    stu- Study  lib- Library    bil- Billard Room");
            System.out.println("co- Conservatory  ball- Ball Room    ki- Kitchen  din- Dining Room");
            System.out.println("Squares with an X means that this is not a part of the board");
            System.out.println("  ");

            //set start position
            for(Item p : players) {
                if (p.name.equals("Miss Scarlett")) {
                    //Start Scarlet
                    board[1][8]="S";
                    p.x = 1;
                    p.y = 8;
                    p.item = "S";
                }
                if (p.name.equals("Professor Plum")) {
                    //Start Plum
                    board[6][24]="P";
                    p.x = 6;
                    p.y = 24;
                    p.item = "P";
                }
                if (p.name.equals("Mrs. Peacock")) {
                    //Start Peacock
                    board[19][24]="C";
                    p.x = 19;
                    p.y = 24;
                    p.item = "C";
                }
                if (p.name.equals("Mr. Green")) {
                    //Start Green
                    board[25][15]="G";
                    p.x = 25;
                    p.y = 15;
                    p.item = "G";
                }
                if (p.name.equals("Mrs. White")) {
                    //Start White
                    board[25][10]="W";
                    p.x = 25;
                    p.y = 10;
                    p.item = "W";
                }
                if (p.name.equals("Colonel Mustard")) {
                    //Start Mustard
                    board[8][1]="M";
                    p.x = 8;
                    p.y = 1;
                    p.item = "M";
                }
            }

            //Lounge
            for(int i=0; i<7; i++){
                for(int j=0; j<8; j++){
                    if(i==6||i==1||j==1||j==7)
                        board[i][j]="*";
                }
            }
            board[6][7]="T";//Door at top (horizontal)
            board[2][6]="*";

            //labelling to show lounge
            board[4][3]="l";
            board[4][4]="o";
            board[4][5]="u";

            board[1][7]="X";
            board[1][9]="X";


            //Hall
            for(int i=0; i<8; i++){
                for(int j=10; j<16; j++){
                    if(j==10||j==15 || i==1||i==7)
                        board[i][j]="*";
                }
            }
            //doors
            board[7][12]="T";
            board[7][13]="T";
            board[5][15]="R";

            //labelling for hall
            board[4][12]="h";
            board[4][13]="a";


            board[1][16]="X";

            //Study
            for(int i=0; i<5; i++){
                for(int j=18; j<25; j++){
                    if(j==18||j==24 || i==1||i==4)
                        board[i][j]="*";
                }
            }
            //door
            board[4][18]="T";

            board[2][19]="*";
            board[1][18]="X";
            board[5][24]="X";

            //labelling study
            board[3][20]="s";
            board[3][21]="t";
            board[3][22]="u";

            //Library
            for(int i=7; i<12; i++){
                for(int j=18; j<25; j++){
                    if(j==18||j==24 || i==7||i==11)
                        board[i][j]="*";
                }
            }
            //door
            board[9][18]="L";
            board[11][21]="T";

            board[8][19]="*";
            board[7][18]="_";
            board[10][19]="*";
            board[11][18]="_";

            board[8][23]="*";
            board[7][24]="X";
            board[10][23]="*";
            board[11][24]="X";


            //labelling Library
            board[9][20]="l";
            board[9][21]="i";
            board[9][22]="b";

            //Billard Room
            for(int i=13; i<18; i++){
                for(int j=19; j<25; j++){
                    if(j==19||j==24 || i==13||i==17)
                        board[i][j]="*";
                }
            }
            //door
            board[13][23]="B";
            board[16][19]="L";

            //labelling Billard Room
            board[15][21]="b";
            board[15][22]="i";
            board[15][23]="l";

            board[12][24]="X";
            board[18][24]="X";

            //conservatory room
            for(int i=20; i<25; i++){
                for(int j=19; j<25; j++){
                    if(j==19||j==24 || i==20||i==24)
                        board[i][j]="*";
                }
            }
            //door
            board[21][19]="B";

            board[21][23]="*";
            board[20][19]="_";
            board[24][18]="X";
            board[20][24]="X";
            for(int i=0; i<25; i++){
                board[25][i]="X";
            }
            //labelling conservatory

            board[22][21]="c";
            board[22][22]="o";

            //ballroom
            for(int i=18; i<25; i++){
                for(int j=9; j<17; j++){
                    if(j==9||j==16 || i==18||i==24)
                        board[i][j]="*";
                }
            }
            //doors
            board[18][10]="B";
            board[18][15]="B";
            board[20][9]="L";
            board[20][16]="R";

            //label ballroom
            board[21][11]="b";
            board[21][12]="a";
            board[21][13]="l";
            board[21][14]="l";

            board[24][9]="_";
            board[24][10]="_";
            board[23][9]="*";
            board[23][10]="*";
            board[23][11]="*";

            board[24][15]="_";
            board[24][16]="_";
            board[23][14]="*";
            board[23][15]="*";
            board[23][16]="*";

            //kitchen
            for(int i=19; i<25; i++){
                for(int j=0; j<7; j++){
                    if(j==1||j==6 || i==19||i==24)
                        board[i][j]="*";
                }
            }
            //door
            board[19][5]="B";

            board[20][2]="*";
            board[17][1]="X";
            board[19][1]="X";
            board[24][7]="X";

            //labelling kitchen
            board[22][3]="k";
            board[22][4]="i";

            //Dining Room
            for(int i=10; i<17; i++){
                for(int j=0; j<9; j++){
                    if(j==1||j==8 || i==10||i==16)
                        board[i][j]="*";
                }
            }
            //Door
            board[10][7]="B";
            board[13][8]="R";

            board[15][5]="*";
            board[15][6]="*";
            board[15][7]="*";
            board[16][8]="_";
            board[16][7]="_";
            board[16][6]="_";
            board[9][1]="x";
            board[7][1]="x";

            //Label dining room
            board[13][3]="d";
            board[13][4]="i";
            board[13][5]="n";

            for(int i=9; i<16; i++){
                for(int j=11; j<16; j++){
                    if(j==15||j==11 || i==9||i==15)
                        board[i][j]="*";
                }
            }

            //printBoard(players,f);
        }

        /**
         * Prints the board in text form.
         * @param players	List of players for updating their positions.
         * @param weapons List of all the weapons for updating positions
         * @param otherPs	List of characters who are not playing for updating their positions.
         */
        public void printBoard(List<Item> players, JFrame f, List<Item> weapons, List<Item> otherPs){

            System.out.println("    A B C D E F G H I J K L M N O P Q R S T U V W X");
            System.out.println(" ");
            for(Item p:players) {
                board[p.x][p.y]=p.item;
            }
            String r="";
            for (int row = 25; row != 0; row--) {
                if(row<10){
                    r +=" ";
                }
                r +=row+" |";
                for (int col = 1; col <= 24; col++) {
                    String p = board[row][col];
                    if (p != null) {
                        r += p + "|";
                    }
                    else {
                        r += "_|";
                    }
                }
                r +=" "+ row+"\n";
            }
            System.out.printf(r);
            System.out.println(" ");
            System.out.println("    A B C D E F G H I J K L M N O P Q R S T U V W X");



            JLayeredPane layeredPane=new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(420, 451));
            layeredPane.setBorder(BorderFactory.createTitledBorder(
                    "Board"));


            File file = new File("src/images/board.png");
            BufferedImage img;
            try {
                img = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(img);
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                lbl.setBounds(6, 16,
                        icon.getIconWidth(),
                        icon.getIconHeight());

                layeredPane.add(lbl, 0);


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            for(Item pl:players) {
                file = new File("src/images/suspect_"+pl.item+".png");
                try {
                    img = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(img);
                    //  f.setLayout(new FlowLayout());
                    JLabel lbl = new JLabel();
                    lbl.setIcon(icon);
                    int x=424-((pl.x-1)*17);
                    int y=6+((pl.y-1)*17);
                    lbl.setBounds(y,x,
                            icon.getIconWidth(),
                            icon.getIconHeight());

                    layeredPane.add(lbl, 0);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            for(Item pl:otherPs) {
                file = new File("src/images/suspect_"+pl.item+".png");
                try {
                    img = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(img);
                    //  f.setLayout(new FlowLayout());
                    JLabel lbl = new JLabel();
                    lbl.setIcon(icon);
                    int x=424-((pl.x-1)*17);
                    int y=6+((pl.y-1)*17);
                    lbl.setBounds(y,x,
                            icon.getIconWidth(),
                            icon.getIconHeight());

                    layeredPane.add(lbl, 0);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            for(Item w:weapons) {
                file = new File(w.item);
                try {
                    img = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(img);
                    //  f.setLayout(new FlowLayout());
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


            f.add(layeredPane);

        }


        boolean visited[][]=new boolean[26][25];
        //Help from https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/

        /**
         * Min distance is a path finding algorithm
         * It makes all squares other than null or _ visited
         * There is a class called QItem which is what is used to queue the items it has an x and y position as well as a distance
         * It adds the players starting position to the queue
         * It goes through until either the q is empty (meaning no possible path return -1) or it has reached the final destination
         * When going through it tries to find all the possible paths (top, bottom, left and right)
         * @param startX 	player's x position
         * @param startY	player's y position
         * @param endX		final destination x
         * @param endY		final destination y
         * @return the minimum number of squares needed to get to the final destination
         */
        int minDistance( int startX, int startY,int endX,int endY){
            for (int row = 25; row != 0; row--) {
                for (int col = 1; col <= 24; col++) {
                    String p = board[row][col];
                    if (p != null && p != "_") {
                        visited[row][col]=true;
                    }
                    else {
                        visited[row][col]=false;
                    }
                }
            }
            Queue<QueueItem> q = new LinkedList<QueueItem>();
            QueueItem source=new QueueItem(startX,startY,0);
            q.add(source);
            visited[source.row][source.col] = true;
            while (!q.isEmpty()) {
                QueueItem p = q.peek();
                q.poll();

                // Destination found;
                if (p.row==endX&& p.col==endY)
                    return p.dist;

                // moving up
                if (p.row - 1 >= 0 &&
                        visited[p.row - 1][p.col] == false) {
                    q.add(new QueueItem(p.row - 1, p.col, p.dist + 1));
                    visited[p.row - 1][p.col] = true;
                }

                // moving down
                if (p.row + 1 < 26 && visited[p.row + 1][p.col] == false) {
                    q.add(new QueueItem(p.row + 1, p.col, p.dist + 1));
                    visited[p.row + 1][p.col] = true;
                }


                // moving left
                if (p.col - 1 >= 0 &&
                        visited[p.row][p.col - 1] == false) {
                    q.add(new QueueItem(p.row, p.col - 1, p.dist + 1));
                    visited[p.row][p.col - 1] = true;
                }

                // moving right
                if (p.col + 1 < 25 &&
                        visited[p.row][p.col + 1] == false) {
                    q.add(new QueueItem(p.row, p.col + 1, p.dist + 1));
                    visited[p.row][p.col + 1] = true;
                }
            }

            return -1;
        }
    }
