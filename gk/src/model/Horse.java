package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Horse extends Chess {

    public Horse(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("HorseR.png").getImage();
        super.imageChessB = new ImageIcon("HorseB.png").getImage();
        super.value = 27;
    }

    @Override
    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        // Initialize the array to false
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }

        // Horse movement logic (L-shape)
        int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // vertical moves
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // horizontal moves
        };

        // Check all possible moves
        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidMove(chessCheck, newX, newY)) {
                g2d.fillOval(pointOfChessX[newX] - 317, pointOfChessY[newY] - 32, 10, 10);
                canGo[newX][newY] = true;
            }
        }
        return canGo;
    }

    @Override
    public boolean[][] pointCanEat(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        g2d.setColor(Color.RED);
        boolean[][] canEat = new boolean[10][11];

        // Initialize the array to false
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canEat[i][j] = false;
            }
        }

        // Horse eat logic (L-shape)
        int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // vertical moves
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // horizontal moves
        };

        // Check all possible moves to eat
        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidEat(chessCheck, x, y, newX, newY)) {
                g2d.fillOval(pointOfChessX[newX] - 317, pointOfChessY[newY] - 32, 10, 10);
                canEat[newX][newY] = true;
            }
        }
        return canEat;
    }

    private boolean isValidMove(Chess[][] chessCheck, int newX, int newY) {
        return newX >= 1 && newX <= 9 && newY >= 1 && newY <= 10 && chessCheck[newX][newY] == null;
    }

    private boolean isValidEat(Chess[][] chessCheck, int x, int y, int newX, int newY) {
        return newX >= 1 && newX <= 9 && newY >= 1 && newY <= 10
                && chessCheck[newX][newY] != null
                && !chessCheck[x][y].getStatus().equals(chessCheck[newX][newY].getStatus());
    }

    @Override
    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        // Horse movement logic (L-shape)
        int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // vertical moves
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // horizontal moves
        };

        // Check all possible moves
        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidMove(chessCheck, newX, newY)) {
                Chess[][] chess = remake(chessCheck);
                chess[newX][newY] = chess[x][y];
                chess[newX][newY].setPoint(new PointOfChess(newX, newY));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
        }
        return newGameMoves;
    }

    @Override
    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        // Horse eat logic (L-shape)
        int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // vertical moves
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // horizontal moves
        };

        // Check all possible moves to eat
        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidEat(chessCheck, x, y, newX, newY)) {
                Chess[][] chess = remake(chessCheck);
                chess[newX][newY] = chess[x][y];
                chess[newX][newY].setPoint(new PointOfChess(newX, newY));
                chess[x][y] = null;
                newGameMoves.add(chess);
            }
        }
        return newGameMoves;
    }

    private Chess[][] remake(Chess[][] chessCheck) {
        Chess[][] chess = new Chess[10][11];
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (chessCheck[i][j] != null) {
                    chess[i][j] = (Chess) chessCheck[i][j].clone();
                }
            }
        }
        return chess;
    }
}
