import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.*;

public class PacManGame {

    Node[][] map;

    JFrame frame;
    JPanel grid;

    Coordinate pacManLocation;

    public PacManGame (){
        map = new Node[29][33];
        frame = new JFrame("PacMan");
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(2900, 3300));


        // representation of 2D array inspired by: https://stackoverflow.com/questions/6877373/write-a-2d-array-on-a-jframe-java
        // image rezinge help from here: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon

        grid = new JPanel();
        grid.setLayout(new GridLayout(29, 33));
        for (int i = 0; i < 29; i++) {
            for (int n = 0; n < 33; n++) {
                if (i==0 || i==28 || n == 0 || n==32){
                    ImageIcon imageIcon = new ImageIcon("pics/wall.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(false, new Wall(),imageIcon);
                }
                else if (i==2 && n== 2){
                    ImageIcon imageIcon = new ImageIcon("pics/blinky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Ghost("blinky"),imageIcon);
                }
                else if (i==23 && n== 2){
                    ImageIcon imageIcon = new ImageIcon("pics/clyde sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Ghost("clyde"),imageIcon);
                }
                else if (i==2 && n== 23){
                    ImageIcon imageIcon = new ImageIcon("pics/pinky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Ghost("pinky"),imageIcon);
                }
                else if (i==23 && n== 23){
                    ImageIcon imageIcon = new ImageIcon("pics/inky sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Ghost("inky"),imageIcon);
                }
                else if (i==15 && n== 15){
                    this.pacManLocation = new Coordinate(15,15);
                    ImageIcon imageIcon = new ImageIcon("pics/pacman.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Actor(),imageIcon);
                }
                else {
                    ImageIcon imageIcon = new ImageIcon("pics/dot sprite.png"); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(30, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    map[i][n] = new Node(true, new Dot(),imageIcon);
                }
                grid.add(new JLabel(map[i][n].getIcon()));
            }
        }


        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);


    }
    public void keyPressed(KeyStroke k){
        int keyName = k.getKeyChar();

        if (keyName == 'w'){
            pacManLocation.setY(pacManLocation.getY()-1);
        }

    }
    public static void main(String[] args) {
        PacManGame pac = new PacManGame();
    }
}