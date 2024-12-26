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
            if (chessCheck[i][y] != null) {
                for (int j = i + 1; j <= 9; ++j) {
                    if (chessCheck[j][y] != null && chessCheck[j][y].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        g2d.fillOval(pointOfChessX[j] - 317, pointOfChessY[y] - 32, 10, 10);
                        canEat[j][y] = true;
                        break;
                    }
                }
                break;
            }
        }

        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] != null) {
                for (int j = i - 1; j >= 1; --j) {
                    if (chessCheck[j][y] != null && chessCheck[j][y].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        g2d.fillOval(pointOfChessX[j] - 317, pointOfChessY[y] - 32, 10, 10);
                        canEat[j][y] = true;
                        break;
                    }
                }
                break;
            }
        }

        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] != null) {
                for (int j = i + 1; j <= 10; ++j) {
                    if (chessCheck[x][j] != null && chessCheck[x][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[j] - 32, 10, 10);
                        canEat[x][j] = true;
                        break;
                    }
                }
                break;
            }
        }

        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] != null) {
                for (int j = i - 1; j >= 1; --j) {
                    if (chessCheck[x][j] != null && chessCheck[x][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        g2d.fillOval(pointOfChessX[x] - 317, pointOfChessY[j] - 32, 10, 10);
                        canEat[x][j] = true;
                        break;
                    }
                }
                break;
            }
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
            if (chessCheck[i][y] != null) {
                for (int j = i + 1; j <= 9; ++j) {
                    if (chessCheck[j][y] != null && chessCheck[j][y].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        Chess[][] chess = remake(chessCheck);
                        chess[j][y] = chess[x][y];
                        chess[j][y].setPoint(new PointOfChess(j, y));
                        chess[x][y] = null;
                        newGameMoves.add(chess);
                        break;
                    }
                }
                break;
            }
        }

        for (int i = x - 1; i >= 1; --i) {
            if (chessCheck[i][y] != null) {
                for (int j = i - 1; j >= 1; --j) {
                    if (chessCheck[j][y] != null && chessCheck[j][y].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        Chess[][] chess = remake(chessCheck);
                        chess[j][y] = chess[x][y];
                        chess[j][y].setPoint(new PointOfChess(j, y));
                        chess[x][y] = null;
                        newGameMoves.add(chess);
                        break;
                    }
                }
                break;
            }
        }

        for (int i = y + 1; i <= 10; ++i) {
            if (chessCheck[x][i] != null) {
                for (int j = i + 1; j <= 10; ++j) {
                    if (chessCheck[x][j] != null && chessCheck[x][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        Chess[][] chess = remake(chessCheck);
                        chess[x][j] = chess[x][y];
                        chess[x][j].setPoint(new PointOfChess(x, j));
                        chess[x][y] = null;
                        newGameMoves.add(chess);
                        break;
                    }
                }
                break;
            }
        }

        for (int i = y - 1; i >= 1; --i) {
            if (chessCheck[x][i] != null) {
                for (int j = i - 1; j >= 1; --j) {
                    if (chessCheck[x][j] != null && chessCheck[x][j].getStatus().compareTo(chessCheck[x][y].getStatus()) != 0) {
                        Chess[][] chess = remake(chessCheck);
                        chess[x][j] = chess[x][y];
                        chess[x][j].setPoint(new PointOfChess(x, j));
                        chess[x][y] = null;
                        newGameMoves.add(chess);
                        break;
                    }
                }
                break;
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
