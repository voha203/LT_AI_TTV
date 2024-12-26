package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Elephant extends Chess {
    public Elephant(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("ElephantR.png").getImage();
        super.imageChessB = new ImageIcon("ElephantB.png").getImage();
        super.value = 22;
    }

    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }

        if (x + 2 <= 9 && y + 2 <= 10 && chessCheck[x + 2][y + 2] == null) {
            g2d.fillOval(pointOfChessX[x + 2] - 317, pointOfChessY[y + 2] - 32, 10, 10);
            canGo[x + 2][y + 2] = true;
        }

        if (x + 2 <= 9 && y - 2 >= 6 && chessCheck[x + 2][y - 2] == null) {
            g2d.fillOval(pointOfChessX[x + 2] - 317, pointOfChessY[y - 2] - 32, 10, 10);
            canGo[x + 2][y - 2] = true;
        }

        if (x - 2 >= 1 && y + 2 <= 10 && chessCheck[x - 2][y + 2] == null) {
            g2d.fillOval(pointOfChessX[x - 2] - 317, pointOfChessY[y + 2] - 32, 10, 10);
            canGo[x - 2][y + 2] = true;
        }

        if (x - 2 >= 1 && y - 2 >= 6 && chessCheck[x - 2][y - 2] == null) {
            g2d.fillOval(pointOfChessX[x - 2] - 317, pointOfChessY[y - 2] - 32, 10, 10);
            canGo[x - 2][y - 2] = true;
        }

        return canGo;
    }

    public boolean[][] pointCanEat(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        g2d.setColor(Color.RED);
        boolean[][] canEat = new boolean[10][11];

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canEat[i][j] = false;
            }
        }

        if (x + 2 <= 9 && y + 2 <= 10 && chessCheck[x + 2][y + 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 2][y + 2].getStatus()) != 0) {
            g2d.fillOval(pointOfChessX[x + 2] - 317, pointOfChessY[y + 2] - 32, 10, 10);
            canEat[x + 2][y + 2] = true;
        }

        if (x + 2 <= 9 && y - 2 >= 6 && chessCheck[x + 2][y - 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 2][y - 2].getStatus()) != 0) {
            g2d.fillOval(pointOfChessX[x + 2] - 317, pointOfChessY[y - 2] - 32, 10, 10);
            canEat[x + 2][y - 2] = true;
        }

        if (x - 2 >= 1 && y + 2 <= 10 && chessCheck[x - 2][y + 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 2][y + 2].getStatus()) != 0) {
            g2d.fillOval(pointOfChessX[x - 2] - 317, pointOfChessY[y + 2] - 32, 10, 10);
            canEat[x - 2][y + 2] = true;
        }

        if (x - 2 >= 1 && y - 2 >= 6 && chessCheck[x - 2][y - 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 2][y - 2].getStatus()) != 0) {
            g2d.fillOval(pointOfChessX[x - 2] - 317, pointOfChessY[y - 2] - 32, 10, 10);
            canEat[x - 2][y - 2] = true;
        }

        return canEat;
    }

    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (x + 2 <= 9 && y + 2 <= 10 && chessCheck[x + 2][y + 2] == null) {
            Chess[][] chess = remake(chessCheck);
            chess[x + 2][y + 2] = chess[x][y];
            chess[x + 2][y + 2].setPoint(new PointOfChess(x + 2, y + 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x + 2 <= 9 && y - 2 >= 6 && chessCheck[x + 2][y - 2] == null) {
            Chess[][] chess = remake(chessCheck);
            chess[x + 2][y - 2] = chess[x][y];
            chess[x + 2][y - 2].setPoint(new PointOfChess(x + 2, y - 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x - 2 >= 1 && y + 2 <= 10 && chessCheck[x - 2][y + 2] == null) {
            Chess[][] chess = remake(chessCheck);
            chess[x - 2][y + 2] = chess[x][y];
            chess[x - 2][y + 2].setPoint(new PointOfChess(x - 2, y + 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x - 2 >= 1 && y - 2 >= 6 && chessCheck[x - 2][y - 2] == null) {
            Chess[][] chess = remake(chessCheck);
            chess[x - 2][y - 2] = chess[x][y];
            chess[x - 2][y - 2].setPoint(new PointOfChess(x - 2, y - 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        return newGameMoves;
    }

    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (x + 2 <= 9 && y + 2 <= 10 && chessCheck[x + 2][y + 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 2][y + 2].getStatus()) != 0) {
            Chess[][] chess = remake(chessCheck);
            chess[x + 2][y + 2] = chess[x][y];
            chess[x + 2][y + 2].setPoint(new PointOfChess(x + 2, y + 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x + 2 <= 9 && y - 2 >= 6 && chessCheck[x + 2][y - 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 2][y - 2].getStatus()) != 0) {
            Chess[][] chess = remake(chessCheck);
            chess[x + 2][y - 2] = chess[x][y];
            chess[x + 2][y - 2].setPoint(new PointOfChess(x + 2, y - 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x - 2 >= 1 && y + 2 <= 10 && chessCheck[x - 2][y + 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 2][y + 2].getStatus()) != 0) {
            Chess[][] chess = remake(chessCheck);
            chess[x - 2][y + 2] = chess[x][y];
            chess[x - 2][y + 2].setPoint(new PointOfChess(x - 2, y + 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        if (x - 2 >= 1 && y - 2 >= 6 && chessCheck[x - 2][y - 2] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 2][y - 2].getStatus()) != 0) {
            Chess[][] chess = remake(chessCheck);
            chess[x - 2][y - 2] = chess[x][y];
            chess[x - 2][y - 2].setPoint(new PointOfChess(x - 2, y - 2));
            chess[x][y] = null;
            newGameMoves.add(chess);
        }

        return newGameMoves;
    }

    private Chess[][] remake(Chess[][] another) {
        Chess[][] chess = new Chess[10][11];
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (another[i][j] != null) {
                    chess[i][j] = (Chess) another[i][j].clone();
                }
            }
        }
        return chess;
    }
}
