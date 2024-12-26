package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Chess {

    public Pawn(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("PawnR.png").getImage();
        super.imageChessB = new ImageIcon("PawnB.png").getImage();
        super.value = 9;
    }

    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }

        if (y >= 6) {
            if (chessCheck[x][y - 1] == null) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[y - 1] - 32, 10, 10);
                canGo[x][y - 1] = true;
            }
        } else {
            if (x - 1 >= 1 && chessCheck[x - 1][y] == null) {
                g2d.fillOval(pointOfChessX[x - 1] - 317, pointOfChessY[y] - 32, 10, 10);
                canGo[x - 1][y] = true;
            }
            if (x + 1 <= 9 && chessCheck[x + 1][y] == null) {
                g2d.fillOval(pointOfChessX[x + 1] - 317, pointOfChessY[y] - 32, 10, 10);
                canGo[x + 1][y] = true;
            }
            if (y - 1 >= 1 && chessCheck[x][y - 1] == null) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[y - 1] - 32, 10, 10);
                canGo[x][y - 1] = true;
            }
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

        if (y >= 6) {
            if (chessCheck[x][y - 1] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][y - 1].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[y - 1] - 32, 10, 10);
                canEat[x][y - 1] = true;
            }
        } else {
            if (x - 1 >= 1 && chessCheck[x - 1][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 1][y].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x - 1] - 317, pointOfChessY[y] - 32, 10, 10);
                canEat[x - 1][y] = true;
            }
            if (x + 1 <= 9 && chessCheck[x + 1][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 1][y].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x + 1] - 317, pointOfChessY[y] - 32, 10, 10);
                canEat[x + 1][y] = true;
            }
            if (y - 1 >= 1 && chessCheck[x][y - 1] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][y - 1].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[y - 1] - 32, 10, 10);
                canEat[x][y - 1] = true;
            }
        }
        return canEat;
    }

    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (y >= 6) {
            if (chessCheck[x][y - 1] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x][y - 1] = chess[x][y];
                chess[x][y - 1].setPoint(new PointOfChess(x, y - 1));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
        } else {
            if (x - 1 >= 1 && chessCheck[x - 1][y] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x - 1][y] = chess[x][y];
                chess[x - 1][y].setPoint(new PointOfChess(x - 1, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
            if (x + 1 <= 9 && chessCheck[x + 1][y] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x + 1][y] = chess[x][y];
                chess[x + 1][y].setPoint(new PointOfChess(x + 1, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
            if (y - 1 >= 1 && chessCheck[x][y - 1] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x][y - 1] = chess[x][y];
                chess[x][y - 1].setPoint(new PointOfChess(x, y - 1));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
        }
        return newGameMoves;
    }

    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (y >= 6) {
            if (chessCheck[x][y - 1] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][y - 1].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x][y - 1] = chess[x][y];
                chess[x][y - 1].setPoint(new PointOfChess(x, y - 1));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
        } else {
            if (x - 1 >= 1 && chessCheck[x - 1][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x - 1][y].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x - 1][y] = chess[x][y];
                chess[x - 1][y].setPoint(new PointOfChess(x - 1, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
            if (x + 1 <= 9 && chessCheck[x + 1][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x + 1][y].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x + 1][y] = chess[x][y];
                chess[x + 1][y].setPoint(new PointOfChess(x + 1, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
            if (y - 1 >= 1 && chessCheck[x][y - 1] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][y - 1].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x][y - 1] = chess[x][y];
                chess[x][y - 1].setPoint(new PointOfChess(x, y - 1));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
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
