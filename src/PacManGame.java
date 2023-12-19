import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PacManGame {

    Node[][] map;

    JFrame frame;
    JPanel grid;

    Coordinate pacManLocation;
    String pacManDirection;
    Ghost blinky, pinky, inky, clyde;

    Actor pacman;

    public PacManGame (){
        map = new Node[36][28];
        frame = new JFrame("PacMan");
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(2900, 3300));


        // representation of 2D array inspired by: https://stackoverflow.com/questions/6877373/write-a-2d-array-on-a-jframe-java
        // image resizing help from here: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon

        for (int i = 0; i < 36; i++) {
            for (int n = 0; n < 28; n++) {
                if ((i==3 || i==33 || (n == 0 && i>=3 && i<=33) || (n == 27 && i>=3 && i<=33) || (i>=5 && i<=7 && n>=2 && n<=5) || (i>=9 && i<=10 && n>= 2 && n<=5) || (i>=5 && i<=7 && n>=7 && n<=11) ||
                        (i<=7 && i>=3 && n>=13 && n<=14) || (i>=5 && i<=7 && n>=16 && n<=20) || (i>=5 && i<=7 && n>= 22 && n<=25) || (i>=9 && i<=16 && n>=7 && n<=8) || (i>=12 && i<=13 && n>=9 &&
                        n<=11) || (i>=9 && i<=10 && n>=10 && n<=17) || (i>=11 && i<=13 && n>=13 && n<=14) || (i>=9 && i<=16 && n>=19 && n<=20) || (i>=12 && i<=13 && n>=16 && n<=18)||
                        (i>=9 && i<=10 && n>= 22 && n<=25) || (i>=12 && i<=16 && n<=5) || (i>=18 && i<=22 && n<=5) || (i>=15 && i<=19 && n>= 10 && n<=11) || (i>=15 && i<=16 && (n==12 || (n>=15 && n<=17))) ||
                        (i>=17 && i<=19 && n>= 16 && n<=17) || (i>=18 && i<=19 && n>= 12 && n<=15) || (i>=18 && i<=22 && n>=7&&n<=8) || (i>=21 && i<=22 && n>=10&&n<=17) || (i>=23 && i<=25 && n>=13&&n<=14) ||
                        (i>=18 && i<=22 && n>=19&&n<=20) || (i>=12 && i<=16 && n>=22) || (i>=18 && i<=22 && n>=22) || (i>=24 && i<=25 && n>=2 && n<=5) || (i>=26 && i<=28 && n>=4 && n<=5) ||
                        (i>=24 && i<=25 && n>=7 && n<=11) || (i>=24 && i<=25 && n>=16 && n<=20) || (i>=24 && i<=25 && n>=22 && n<=25) || (i>=26 && i<=28 && n>=22 && n<=23) || (i>=27 && i<=28 && n<=2) ||
                        (i>=27 && i<=28 && n>=25) || (i>=30 && i<=31 && n>=2 && n<=11) || (i>=27 && i<=29 && n>=7 && n<=8)|| (i>=27 && i<=28 && n>=10 && n<=17) || (i>=29 && i<=31 && n>=13 && n<=14) ||
                        (i>=30 && i<=31 && n>=16 && n<=25) || (i>=27 && i<=29 && n>=19 && n<=20))){
                    ImageIcon imageIcon = new ImageIcon("pics/wall.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newImg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newImg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    list.add(new Wall(imageIcon));
                    map[i][n] = new Node(false, list , false);
                }
                else if (i==14 && n== 13){
                    ImageIcon imageIcon = new ImageIcon("pics/blinky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newImg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newImg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    blinky = new Ghost("blinky", imageIcon);
                    blinky.setDirection("l");
                    blinky.setCurrLocation(new Coordinate(i,n));
                    list.add(blinky);
                    map[i][n] = new Node(true, list, true);
                }
                else if (i==17 && n== 12){
                    ImageIcon imageIcon = new ImageIcon("pics/clyde sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newImg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newImg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    clyde = new Ghost("clyde", imageIcon);
                    clyde.setDirection("r");
                    clyde.setCurrLocation(new Coordinate(i,n));
                    list.add(clyde);
                    map[i][n] = new Node(true, list, true);
                }
                else if (i==17 && n== 13){
                    ImageIcon imageIcon = new ImageIcon("pics/pinky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    pinky = new Ghost("pinky", imageIcon);
                    pinky.setDirection("u");
                    pinky.setCurrLocation(new Coordinate(i,n));
                    list.add(pinky);
                    map[i][n] = new Node(true, list, true);
                }
                else if (i==17 && n== 14){
                    ImageIcon imageIcon = new ImageIcon("pics/inky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    inky = new Ghost("inky", imageIcon);
                    inky.setDirection("u");
                    inky.setCurrLocation(new Coordinate(i,n));
                    list.add(inky);
                    map[i][n] = new Node(true, list, true);
                }
                else if (i==26 && n== 13){
                    this.pacManLocation = new Coordinate(i,n);
                    ImageIcon imageIcon = new ImageIcon("pics/pacman.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    pacman = new Actor(imageIcon);
                    pacManDirection = "r";
                    list.add(pacman);
                    map[i][n] = new Node(true, list , false);
                }
                else if (i<3 || i>33){
                    map[i][n] = new Node(false, null ,false);
                }
                else {
                    ImageIcon imageIcon = new ImageIcon("pics/dot sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    LinkedList<Actor> list = new LinkedList();
                    list.add(new Dot(imageIcon));
                    map[i][n] = new Node(true, list , true);
                }
            }
        }
        repaint();
//        JPanel panel2 = new JPanel();
//        JButton button = new JButton("Start");
//        panel2.add(button);
//        frame.add(panel2);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyName = e.getKeyChar();

                Coordinate oldPacLocation = pacManLocation;

                if (keyName == 'w' && map[pacManLocation.getX()][pacManLocation.getY()-1].isInBounds()){
                    pacManDirection = "u";
                    pacManLocation.setY(pacManLocation.getY()-1);
                }
                else if (keyName == 'a' && map[pacManLocation.getX()-1][pacManLocation.getY()].isInBounds()){
                    pacManDirection = "l";
                    pacManLocation.setX(pacManLocation.getX()-1);
                }
                else if (keyName == 's' && map[pacManLocation.getX()][pacManLocation.getY()+1].isInBounds()){
                    pacManDirection = "d";
                    pacManLocation.setY(pacManLocation.getY()+1);
                }
                else if (keyName == 'd' && map[pacManLocation.getX()+1][pacManLocation.getY()].isInBounds()){
                    pacManDirection = "r";
                    pacManLocation.setX(pacManLocation.getX()+1);
                }

                map[oldPacLocation.getX()][oldPacLocation.getY()].removeLast();

                map[pacManLocation.getX()][pacManLocation.getY()].addOccupant(pacman);





                Coordinate oldBlinkyLoc = blinky.getCurrLocation();

                Coordinate blinkyMove = oldBlinkyLoc;

                if (isIntersection(blinky.getCurrLocation())){
                     blinkyMove = blinky.turn(possiblePaths(blinky.getCurrLocation()), pacManLocation);
                }
                else {
                     blinkyMove = blinky.forward(map);
                }

                map[oldBlinkyLoc.getX()][oldBlinkyLoc.getY()].removeLast();
                map[blinkyMove.getX()][blinkyMove.getY()].addOccupant(blinky);




                Coordinate oldPinkyLoc = blinky.getCurrLocation();

                Coordinate pinkyMove = oldPinkyLoc;

                if (isIntersection(pinky.getCurrLocation())){
                    if (pacManDirection.equals("r")){
                         pinkyMove = pinky.turn(possiblePaths(pinky.getCurrLocation()), new Coordinate(pacManLocation.getX()+4, pacManLocation.getY()));
                    }
                    else if (pacManDirection.equals("l")){
                         pinkyMove = pinky.turn(possiblePaths(pinky.getCurrLocation()), new Coordinate(pacManLocation.getX()-4, pacManLocation.getY()));
                    }
                    else if (pacManDirection.equals("u")){
                         pinkyMove = pinky.turn(possiblePaths(pinky.getCurrLocation()), new Coordinate(pacManLocation.getX(), pacManLocation.getY()-4));
                    }
                    else if (pacManDirection.equals("d")){
                         pinkyMove = pinky.turn(possiblePaths(pinky.getCurrLocation()), new Coordinate(pacManLocation.getX(), pacManLocation.getY()+4));
                    }
                }
                else {
                     pinkyMove = pinky.forward(map);
                }

                map[oldPinkyLoc.getX()][oldPinkyLoc.getY()].removeLast();
                map[pinkyMove.getX()][pinkyMove.getY()].addOccupant(pinky);




                repaint();

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }


        });

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);


    }

    public void repaint(){

        grid = new JPanel();
        grid.setLayout(new GridLayout(map.length, map[0].length));
        for (int i = 0; i<map.length; i++){
            for (int j = 0; j<map[i].length; j++){
                grid.add(new JLabel(map[i][j].getIcon()));
            }
        }
        frame.add(grid);
        frame.repaint();

        System.out.println("repainted!");
    }


    public boolean isIntersection (Coordinate coord){
        return possiblePaths(coord).size() > 2;
    }

    public ArrayList<String> possiblePaths (Coordinate coord){
        ArrayList<String> possiblePaths = new ArrayList<>();

        if (map[coord.getX()-1][coord.getY()].isInBounds()){
            possiblePaths.add("l");
        }
        if (map[coord.getX()][coord.getY()-1].isInBounds()){
            possiblePaths.add("u");
        }
        if (map[coord.getX()+1][coord.getY()].isInBounds()){
            possiblePaths.add("r");
        }
        if (map[coord.getX()][coord.getY()+1].isInBounds()){
            possiblePaths.add("d");
        }
        return possiblePaths;
    }

    public static void main(String[] args) {
        PacManGame pac = new PacManGame();

    }
}