import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Node {

    private int frictionCoef;
    private boolean isInBounds;
    private LinkedList<Actor> occupants;

    private boolean hasDot;

    public Node (boolean isInBounds, LinkedList<Actor> occupants, boolean hasDot){
        frictionCoef = 1;
        this.isInBounds = isInBounds;
        this.occupants = occupants;
        this.hasDot = hasDot;
    }

    public int getFrictionCoef() {
        return frictionCoef;
    }

    public void setFrictionCoef(int frictionCoef) {
        this.frictionCoef = frictionCoef;
    }


    public boolean isInBounds() {
        return isInBounds;
    }

    public void setInBounds(boolean inBounds) {
        isInBounds = inBounds;
    }

    public LinkedList<Actor> getOccupants() {
        return occupants;
    }

    public void setOccupants(LinkedList<Actor> occupants) {
        this.occupants = occupants;
    }

    public Object getLast(){
        return occupants.getLast();
    }

    public void removeLast(){
        occupants.removeLast();
    }

    public void addOccupant (Actor object){
        occupants.add(object);
    }

    public ImageIcon getIcon() {
        if (occupants!=null) return occupants.getLast().getIcon();
        else return null;
    }



    public boolean isHasDot() {
        return hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
    }
}
