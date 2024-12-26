package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Advisor extends Chess {

    public Advisor(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("AdvisorR.png").getImage();
        super.imageChessB = new ImageIcon("AdvisorB.png").getImage();
        super.value = 14;
    }

    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }

        if (x == 5 && y == 9) {
            if (chessCheck[4][10] == null) {
                g2d.fillOval(pointOfChessX[4] - 317, pointOfChessY[10] - 32, 10, 10);
                canGo[4][10] = true;
            }
            if (chessCheck[4][8] == null) {
                g2d.fillOval(pointOfChessX[4] - 317, pointOfChessY[8] - 32, 10, 10);
                canGo[4][8] = true;
            }
            if (chessCheck[6][10] == null) {
                g2d.fillOval(pointOfChessX[6] - 317, pointOfChessY[10] - 32, 10, 10);
                canGo[6][10] = true;
            }
            if (chessCheck[6][8] == null) {
                g2d.fillOval(pointOfChessX[6] - 317, pointOfChessY[8] - 32, 10, 10);
                canGo[6][8] = true;
            }
        } else if (chessCheck[5][9] == null) {
            g2d.fillOval(pointOfChessX[5] - 317, pointOfChessY[9] - 32, 10, 10);
            canGo[5][9] = true;
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

        if (x == 5 && y == 9) {
            if (chessCheck[4][10] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[4][10].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[4] - 317, pointOfChessY[10] - 32, 10, 10);
                canEat[4][10] = true;
            }
            if (chessCheck[4][8] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[4][8].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[4] - 317, pointOfChessY[8] - 32, 10, 10);
                canEat[4][8] = true;
            }
            if (chessCheck[6][10] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[6][10].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[6] - 317, pointOfChessY[10] - 32, 10, 10);
                canEat[6][10] = true;
            }
            if (chessCheck[6][8] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[6][8].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[6] - 317, pointOfChessY[8] - 32, 10, 10);
                canEat[6][8] = true;
            }
        } else if (chessCheck[5][9] != null && chessCheck[5][9].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
            g2d.fillOval(pointOfChessX[5] - 317, pointOfChessY[9] - 32, 10, 10);
            canEat[5][9] = true;
        }
        return canEat;
    }

    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {

        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (x == 5 && y == 9) {
            if (chessCheck[4][10] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[4][10] = chess[5][9];
                chess[4][10].setPoint(new PointOfChess(4, 10));
                chess[5][9] = null;

                newGameMoves.add(chess);
            }
            if (chessCheck[4][8] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[4][8] = chess[5][9];
                chess[4][8].setPoint(new PointOfChess(4, 8));
                chess[5][9] = null;

                newGameMoves.add(chess);

            }
            if (chessCheck[6][10] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[6][10] = chess[5][9];
                chess[6][10].setPoint(new PointOfChess(6, 10));
                chess[5][9] = null;

                newGameMoves.add(chess);

            }
            if (chessCheck[6][8] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[6][8] = chess[5][9];
                chess[6][8].setPoint(new PointOfChess(6, 8));
                chess[5][9] = null;

                newGameMoves.add(chess);

            }
        } else if (chessCheck[5][9] == null) {
            Chess[][] chess = remake(chessCheck);
            chess[5][9] = chess[x][y];
            chess[5][9].setPoint(new PointOfChess(5, 9));
            chess[x][y] = null;

            newGameMoves.add(chess);

        }
        return newGameMoves;
    }

    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {

        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        if (x == 5 && y == 9) {
            if (chessCheck[4][10] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[4][10].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[4][10] = chess[5][9];
                chess[4][10].setPoint(new PointOfChess(4, 10));
                chess[5][9] = null;

                newGameMoves.add(chess);
            }
            if (chessCheck[4][8] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[4][8].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[4][8] = chess[5][9];
                chess[4][8].setPoint(new PointOfChess(4, 8));
                chess[5][9] = null;

                newGameMoves.add(chess);
            }
            if (chessCheck[6][10] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[6][10].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[6][10] = chess[5][9];
                chess[6][10].setPoint(new PointOfChess(6, 10));
                chess[5][9] = null;

                newGameMoves.add(chess);
            }
            if (chessCheck[6][8] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[6][8].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[6][8] = chess[5][9];
                chess[6][8].setPoint(new PointOfChess(6, 8));
                chess[5][9] = null;

                newGameMoves.add(chess);
            }
        } else if (chessCheck[5][9] != null && chessCheck[5][9].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
            Chess[][] chess = remake(chessCheck);
            chess[5][9] = chess[x][y];
            chess[5][9].setPoint(new PointOfChess(5, 9));
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
