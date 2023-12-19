import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Node {

    private int frictionCoef;
    private boolean isInBounds;
    private Actor occupant;

    private boolean hasDot;

    public Node (boolean isInBounds, Actor occupant, boolean hasDot){
        frictionCoef = 1;
        this.isInBounds = isInBounds;
        this.occupant = occupant;
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

    public Actor getOccupant() {
        return occupant;
    }

    public void setOccupant(Actor occupant) {
        this.occupant = occupant;
    }



    public ImageIcon getIcon() {
        if (occupant==null) return null;
        return occupant.getIcon();
    }


    public void setIcon(ImageIcon icon){
        occupant.setIcon(icon);
    }

    public boolean isHasDot() {
        return hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
    }
}
