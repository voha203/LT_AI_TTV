package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cannon extends Chess {

    public Cannon(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("CannonR.png").getImage();
        super.imageChessB = new ImageIcon("CannonB.png").getImage();
        super.value = 51;
    }

    @Override
    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                canGo[i][j] = false;
            }
        }

        // Check horizontal and vertical directions
        checkDirection(chessCheck, canGo, x, y, 1, 0, g2d, pointOfChessX, pointOfChessY); // Right
        checkDirection(chessCheck, canGo, x, y, -1, 0, g2d, pointOfChessX, pointOfChessY); // Left
        checkDirection(chessCheck, canGo, x, y, 0, 1, g2d, pointOfChessX, pointOfChessY); // Down
        checkDirection(chessCheck, canGo, x, y, 0, -1, g2d, pointOfChessX, pointOfChessY); // Up

        return canGo;
    }

    private void checkDirection(Chess[][] chessCheck, boolean[][] canGo, int x, int y, int dx, int dy, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        int i = x + dx;
        int j = y + dy;

        while (i >= 0 && i < 10 && j >= 0 && j < 11) {
            if (chessCheck[i][j] == null) {
                g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[j] - 32, 10, 10);
                canGo[i][j] = true;
            } else {
                break;
            }
            i += dx;
            j += dy;
        }
    }

    @Override
    public boolean[][] pointCanEat(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canEat = new boolean[10][11];
        g2d.setColor(Color.RED);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                canEat[i][j] = false;
            }
        }

        // Check horizontal and vertical directions for capturing
        checkCapture(chessCheck, canEat, x, y, 1, 0, g2d, pointOfChessX, pointOfChessY); // Right
        checkCapture(chessCheck, canEat, x, y, -1, 0, g2d, pointOfChessX, pointOfChessY); // Left
        checkCapture(chessCheck, canEat, x, y, 0, 1, g2d, pointOfChessX, pointOfChessY); // Down
        checkCapture(chessCheck, canEat, x, y, 0, -1, g2d, pointOfChessX, pointOfChessY); // Up

        return canEat;
    }

    private void checkCapture(Chess[][] chessCheck, boolean[][] canEat, int x, int y, int dx, int dy, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean foundObstacle = false;
        int i = x + dx;
        int j = y + dy;

        while (i >= 0 && i < 10 && j >= 0 && j < 11) {
            if (chessCheck[i][j] != null) {
                if (!foundObstacle) {
                    foundObstacle = true;
                } else {
                    if (chessCheck[i][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[j] - 32, 10, 10);
                        canEat[i][j] = true;
                    }
                    break;
                }
            }
            i += dx;
            j += dy;
        }
    }

    @Override
    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        simulateMoves(chessCheck, newGameMoves, x, y, 1, 0); // Right
        simulateMoves(chessCheck, newGameMoves, x, y, -1, 0); // Left
        simulateMoves(chessCheck, newGameMoves, x, y, 0, 1); // Down
        simulateMoves(chessCheck, newGameMoves, x, y, 0, -1); // Up

        return newGameMoves;
    }

    @Override
    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        simulateCaptures(chessCheck, newGameMoves, x, y, 1, 0); // Right
        simulateCaptures(chessCheck, newGameMoves, x, y, -1, 0); // Left
        simulateCaptures(chessCheck, newGameMoves, x, y, 0, 1); // Down
        simulateCaptures(chessCheck, newGameMoves, x, y, 0, -1); // Up

        return newGameMoves;
    }

    private void simulateMoves(Chess[][] chessCheck, ArrayList<Chess[][]> newGameMoves, int x, int y, int dx, int dy) {
        int i = x + dx;
        int j = y + dy;

        while (i >= 0 && i < 10 && j >= 0 && j < 11) {
            if (chessCheck[i][j] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[i][j] = chess[x][y];
                chess[i][j].setPoint(new PointOfChess(i, j));
                chess[x][y] = null;
                newGameMoves.add(chess);
            } else {
                break;
            }
            i += dx;
            j += dy;
        }
    }

    private void simulateCaptures(Chess[][] chessCheck, ArrayList<Chess[][]> newGameMoves, int x, int y, int dx, int dy) {
        boolean foundObstacle = false;
        int i = x + dx;
        int j = y + dy;

        while (i >= 0 && i < 10 && j >= 0 && j < 11) {
            if (chessCheck[i][j] != null) {
                if (!foundObstacle) {
                    foundObstacle = true;
                } else {
                    if (chessCheck[i][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        Chess[][] chess = remake(chessCheck);
                        chess[i][j] = chess[x][y];
                        chess[i][j].setPoint(new PointOfChess(i, j));
                        chess[x][y] = null;
                        newGameMoves.add(chess);
                    }
                    break;
                }
            }
            i += dx;
            j += dy;
        }
    }

    private Chess[][] remake(Chess[][] another) {
        Chess[][] chess = new Chess[10][11];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (another[i][j] != null) {
                    chess[i][j] = (Chess) another[i][j].clone();
                }
            }
        }
        return chess;
    }
}
