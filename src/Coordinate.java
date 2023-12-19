public class Coordinate {
    private int x;
    private int y;

    public Coordinate (int x, int y){
        this.x = y;
        this.y = x;
    }

    public int getX() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return (x+", "+y);
    }
}
