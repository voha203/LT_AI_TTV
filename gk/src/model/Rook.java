package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rook extends Chess {

    public Rook(PointOfChess point) {
        super(point);
        super.imageChessR = new ImageIcon("RookR.png").getImage();
        super.imageChessB = new ImageIcon("RookB.png").getImage();
        super.value = 64;
    }

    public boolean[][] pointCanGo(Chess[][] chessCheck, int x, int y, Graphics2D g2d, int[] pointOfChessX, int[] pointOfChessY) {
        boolean[][] canGo = new boolean[10][11];

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }

        for (int i = x + 1; i <= 9; ++i) {
            if (chessCheck[i][y] == null) {
                g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[y] - 32, 10, 10);
                canGo[i][y] = true;
            } else {
                break;
            }
        }
        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] == null) {
                g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[y] - 32, 10, 10);
                canGo[i][y] = true;
            } else {
                break;
            }
        }
        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] == null) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[i] - 32, 10, 10);
                canGo[x][i] = true;
            } else {
                break;
            }
        }
        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] == null) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[i] - 32, 10, 10);
                canGo[x][i] = true;
            } else {
                break;
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

        for (int i = x + 1; i <= 9; ++i) {
            if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[y] - 32, 10, 10);
                canEat[i][y] = true;
                break;
            } else if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) == 0)
                break;
        }
        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[i] - 317, pointOfChessY[y] - 32, 10, 10);
                canEat[i][y] = true;
                break;
            } else if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) == 0)
                break;
        }
        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[i] - 32, 10, 10);
                canEat[x][i] = true;
                break;
            } else if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) == 0)
                break;
        }
        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) != 0) {
                g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[i] - 32, 10, 10);
                canEat[x][i] = true;
                break;
            } else if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) == 0)
                break;
        }
        return canEat;
    }

    public ArrayList<Chess[][]> botCanGo(Chess[][] chessCheck, int x, int y) {

        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        for (int i = x + 1; i <= 9; ++i) {
            if (chessCheck[i][y] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[i][y] = chess[x][y];
                chess[i][y].setPoint(new PointOfChess(i, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            } else {
                break;
            }
        }
        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[i][y] = chess[x][y];
                chess[i][y].setPoint(new PointOfChess(i, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
            } else {
                break;
            }
        }
        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x][i] = chess[x][y];
                chess[x][i].setPoint(new PointOfChess(x, i));
                chess[x][y] = null;
                newGameMoves.add(chess);
            } else {
                break;
            }
        }
        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] == null) {
                Chess[][] chess = remake(chessCheck);
                chess[x][i] = chess[x][y];
                chess[x][i].setPoint(new PointOfChess(x, i));
                chess[x][y] = null;
                newGameMoves.add(chess);
            } else {
                break;
            }
        }
        return newGameMoves;
    }

    public ArrayList<Chess[][]> botCanEat(Chess[][] chessCheck, int x, int y) {

        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        for (int i = x + 1; i <= 9; ++i) {
            if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[i][y] = chess[x][y];
                chess[i][y].setPoint(new PointOfChess(i, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
                break;
            } else if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) == 0)
                break;
        }
        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[i][y] = chess[x][y];
                chess[i][y].setPoint(new PointOfChess(i, y));
                chess[x][y] = null;
                newGameMoves.add(chess);
                break;
            } else if (chessCheck[i][y] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[i][y].getStatus()) == 0)
                break;
        }
        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x][i] = chess[x][y];
                chess[x][i].setPoint(new PointOfChess(x, i));
                chess[x][y] = null;
                newGameMoves.add(chess);
                break;
            } else if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) == 0)
                break;
        }
        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) != 0) {
                Chess[][] chess = remake(chessCheck);
                chess[x][i] = chess[x][y];
                chess[x][i].setPoint(new PointOfChess(x, i));
                chess[x][y] = null;
                newGameMoves.add(chess);
                break;
            } else if (chessCheck[x][i] != null && chessCheck[x][y].getStatus().compareTo(chessCheck[x][i].getStatus()) == 0)
                break;
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
