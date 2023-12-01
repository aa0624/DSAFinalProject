public class Node {

    private int frictionCoef;
    private boolean isInBounds;
    private Object occupant;

    public Node (boolean isInBounds, Object occupant){
        frictionCoef = 1;
        this.isInBounds = isInBounds;
        this.occupant = occupant;
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
}
