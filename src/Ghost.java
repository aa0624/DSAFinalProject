import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ghost extends Actor{
    private String ghost;
    private String direction;

    private Coordinate currLocation;

    private ImageIcon icon;

    public Ghost (String ghost, ImageIcon icon){
        super(icon);
        this.ghost=ghost;
    }


    //returns coords for where ghost should move next
    public Coordinate turn (ArrayList<String> possiblePaths, Coordinate target){
        double shortestDistance = Double.MAX_VALUE;
        Coordinate returnCoords = null;
        while (!possiblePaths.isEmpty()){
            String path = possiblePaths.remove(0);
            if (path.equals("u")){
                double currDistance = Math.sqrt(Math.pow((double)(currLocation.getX())-target.getX(), 2) + Math.pow((double)(currLocation.getY() - 1) - target.getY(), 2));
                if (currDistance < shortestDistance){
                    shortestDistance = currDistance;
                    returnCoords = new Coordinate(currLocation.getX(), currLocation.getY()-1);
                }
            }
            else if (path.equals("l")){
                double currDistance = Math.sqrt(Math.pow((double)(currLocation.getX()-1)-target.getX(), 2) + Math.pow((double)(currLocation.getY()) - target.getY(), 2));
                if (currDistance < shortestDistance){
                    shortestDistance = currDistance;
                    returnCoords = new Coordinate(currLocation.getX()-1, currLocation.getY());
                }
            }
            else if (path.equals("d")){
                double currDistance = Math.sqrt(Math.pow((double)(currLocation.getX())-target.getX(), 2) + Math.pow((double)(currLocation.getY() + 1) - target.getY(), 2));
                if (currDistance < shortestDistance){
                    shortestDistance = currDistance;
                    returnCoords = new Coordinate(currLocation.getX(), currLocation.getY()+1);
                }
            }
            else if (path.equals("r")){
                double currDistance = Math.sqrt(Math.pow((double)(currLocation.getX() +1)-target.getX(), 2) + Math.pow((double)(currLocation.getY()) - target.getY(), 2));
                if (currDistance < shortestDistance){
                    shortestDistance = currDistance;
                    returnCoords = new Coordinate(currLocation.getX() +1, currLocation.getY());
                }
            }
        }
        return returnCoords;
    }

    public Coordinate forward (Node[][] map){
        if (direction.equals("r")){
            if (map[currLocation.getX()+1][currLocation.getY()].isInBounds()){
                return (new Coordinate(currLocation.getX()+1, currLocation.getY()));
            }
            else if (map[currLocation.getX()][currLocation.getY()-1].isInBounds()){
                direction = "u";
                return (new Coordinate(currLocation.getX(), currLocation.getY()-1));
            }
            else if (map[currLocation.getX()-1][currLocation.getY()].isInBounds()){
                direction = "l";
                return (new Coordinate(currLocation.getX()-1, currLocation.getY()));
            }
            else if (map[currLocation.getX()][currLocation.getY()+1].isInBounds()){
                direction = "d";
                return (new Coordinate(currLocation.getX(), currLocation.getY()+1));
            }
        }
        if (direction.equals("u")){
            if (map[currLocation.getX()][currLocation.getY()-1].isInBounds()){
                return (new Coordinate(currLocation.getX(), currLocation.getY()-1));
            }
            else if (map[currLocation.getX()+1][currLocation.getY()].isInBounds()){
                direction = "r";
                return (new Coordinate(currLocation.getX()+1, currLocation.getY()));
            }
            else if (map[currLocation.getX()-1][currLocation.getY()].isInBounds()){
                direction = "l";
                return (new Coordinate(currLocation.getX()-1, currLocation.getY()));
            }
            else if (map[currLocation.getX()][currLocation.getY()+1].isInBounds()){
                direction = "d";
                return (new Coordinate(currLocation.getX(), currLocation.getY()+1));
            }
        }
        if (direction.equals("l")){
            if (map[currLocation.getX()-1][currLocation.getY()].isInBounds()){
                return (new Coordinate(currLocation.getX()-1, currLocation.getY()));
            }
            else if (map[currLocation.getX()+1][currLocation.getY()].isInBounds()){
                direction = "r";
                return (new Coordinate(currLocation.getX()+1, currLocation.getY()));
            }
            else if (map[currLocation.getX()][currLocation.getY()-1].isInBounds()){
                direction = "u";
                return (new Coordinate(currLocation.getX(), currLocation.getY()-1));
            }
            else if (map[currLocation.getX()][currLocation.getY()+1].isInBounds()){
                direction = "d";
                return (new Coordinate(currLocation.getX(), currLocation.getY()+1));
            }
        }
        if (direction.equals("d")){
            if (map[currLocation.getX()][currLocation.getY()+1].isInBounds()){
                return (new Coordinate(currLocation.getX(), currLocation.getY()+1));
            }
            else if (map[currLocation.getX()+1][currLocation.getY()].isInBounds()){
                direction = "r";
                return (new Coordinate(currLocation.getX()+1, currLocation.getY()));
            }
            else if (map[currLocation.getX()][currLocation.getY()-1].isInBounds()){
                direction = "u";
                return (new Coordinate(currLocation.getX(), currLocation.getY()-1));
            }
            else if (map[currLocation.getX()-1][currLocation.getY()].isInBounds()){
                direction = "l";
                return (new Coordinate(currLocation.getX()-1, currLocation.getY()));
            }
        }
        return null;
    }
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getGhost() {
        return ghost;
    }

    public void setCurrLocation(Coordinate currLocation) {
        this.currLocation = currLocation;
    }

    public Coordinate getCurrLocation (){
        return this.currLocation;
    }

}
