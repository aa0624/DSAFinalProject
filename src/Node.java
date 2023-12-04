import javax.swing.*;
import java.awt.*;

public class Node {

    private int frictionCoef;
    private boolean isInBounds;
    private Object occupant;
    private ImageIcon icon;

    public Node (boolean isInBounds, Object occupant, String icon){
        frictionCoef = 1;
        this.isInBounds = isInBounds;
        this.occupant = occupant;
        this.icon = new ImageIcon (icon);
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

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
