package model;

import java.awt.*;
import java.util.ArrayList;

public class Chess implements Cloneable {
    protected PointOfChess point;
    protected Image imageChessR;
    protected Image imageChessB;
    protected String status;
    protected int value;

    public Chess() {
    }

    public Chess(Chess another) {
        this.imageChessB = another.getImageChessB();
        this.imageChessR = another.getImageChessR();
        this.point = another.getPoint();
        this.status = another.getStatus();
        this.value = another.getValue();
    }

    public Chess(PointOfChess point) {
        this.point = point;
    }

    public PointOfChess getPoint() {
        return point;
    }

    public void setPoint(PointOfChess point) {
        this.point = point;
    }

    public Image getImageChessB() {
        return imageChessB;
    }

    public Image getImageChessR() {
        return imageChessR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX,
            int[] pointOfChessY) {
        return null;
    }

    public boolean[][] pointCanEat(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX,
            int[] pointOfChessY) {
        return null;
    }

    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {
        return null;
    }

    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {
        return null;
    }

    @Override
	public Object clone() {
	    try {
	        return (Chess) super.clone();
	    } catch (CloneNotSupportedException e) {
	        return "!";
	    }
	}

    @Override
    public String toString() {
        return point + " " + status + " " + value;
    }
}
