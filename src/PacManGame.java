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
        frame = new JFrame();

        // representation of 2D array inspired by: https://stackoverflow.com/questions/6877373/write-a-2d-array-on-a-jframe-java
        ImageIcon dot = new ImageIcon ("/pics/dot sprite.png");
        grid = new JPanel();
        grid.setLayout(new GridLayout(29, 33));
        for (int i = 0; i < 29; i++) {
            for (int n = 0; n < 33; n++) {
                grid.add(new JPanel());
            }
        }
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(640, 400));
        frame.add(grid);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}