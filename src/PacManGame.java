import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.*;

public class PacManGame {

    Node[][] map;

    JFrame frame;
    JPanel grid;

    public PacManGame (){
        map = new Node[29][33];
        frame = new JFrame("PacMan");
        frame.getContentPane().setBackground(Color.black);

        // representation of 2D array inspired by: https://stackoverflow.com/questions/6877373/write-a-2d-array-on-a-jframe-java

        grid = new JPanel();
        grid.setLayout(new GridLayout(29, 33));
        for (int i = 0; i < 29; i++) {
            for (int n = 0; n < 33; n++) {
                if (i==0 || i==28 || n == 0 || n==32){
                    map[i][n] = new Node(false, new Wall(),"pics/wall.png");
                }
                else if (i==2 && n== 2){
                    map[i][n] = new Node(true, new Ghost("blinky"),"pics/blinky sprite.png");
                }
                else if (i==23 && n== 2){
                    map[i][n] = new Node(true, new Ghost("clyde"),"pics/clyde sprite.png");
                }
                else if (i==2 && n== 23){
                    map[i][n] = new Node(true, new Ghost("pinky"),"pics/pinky sprite.png");
                }
                else if (i==23 && n== 23){
                    map[i][n] = new Node(true, new Ghost("inky"),"pics/inky sprite.png");
                }
                else if (i==15 && n== 15){
                    map[i][n] = new Node(true, new Actor(),"pics/pacman.png");
                }
                else {
                    map[i][n] = new Node(true, new Dot(),"pics/dot sprite.png");
                }
                grid.add(new JLabel(map[i][n].getIcon()));
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 400));
        frame.add(grid);
        frame.setVisible(true);


    }
    public static void main(String[] args) {
        PacManGame pac = new PacManGame();
    }
}