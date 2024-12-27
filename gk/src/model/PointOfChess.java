package model;

public class PointOfChess implements Cloneable {
    private int x;
    private int y;

    public PointOfChess(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Object clone() {
        try {
            return (PointOfChess) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
