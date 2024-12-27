package view;

import model.*;
import controller.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;

public class Display extends FrameChess implements ActionListener {
    private Image chineseChess;
    private Chess chessSelect;
    private Chess[][] initialChessState;  // Thêm biến để lưu trạng thái ban đầu

    private void loadImage() {
        chineseChess = new ImageIcon("chess.png").getImage();
    }

    public Display() {
        loadImage();
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);

        // Lưu trạng thái ban đầu của bàn cờ
        initialChessState = new Chess[10][11];
        copyChessState(chessCheck, initialChessState);
    }

    // Phương thức sao chép trạng thái bàn cờ
    private void copyChessState(Chess[][] source, Chess[][] destination) {
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (source[i][j] != null) {
                    destination[i][j] = (Chess) source[i][j].clone();
                }
            }
        }
    }

    // Phương thức khôi phục lại trạng thái ban đầu
    private void resetGameState() {
        copyChessState(initialChessState, chessCheck);

        chessRed.clear();
        chessBlack.clear();

        // Cập nhật lại danh sách các quân cờ
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (chessCheck[i][j] != null) {
                    if (chessCheck[i][j].getStatus().equals("RED")) {
                        chessRed.add(chessCheck[i][j]);
                    } else {
                        chessBlack.add(chessCheck[i][j]);
                    }
                }
            }
        }

        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(chineseChess, 0, 0, null);

        for (Chess chess : chessRed) {
            g2d.drawImage(chess.getImageChessR(), pointOfChessX[chess.getPoint().getX()] - 340,
                    pointOfChessY[chess.getPoint().getY()] - 55, null);
        }

        for (Chess chess : chessBlack) {
            g2d.drawImage(chess.getImageChessB(), pointOfChessX[chess.getPoint().getX()] - 340,
                    pointOfChessY[chess.getPoint().getY()] - 55, null);
        }

        if (checkAction == true && chessSelect != null) {
            canGo = chessSelect.pointCanGo(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(),
                    g2d, pointOfChessX, pointOfChessY);
            canEat = chessSelect.pointCanEat(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(),
                    g2d, pointOfChessX, pointOfChessY);
        }

        if (botTurn) {
            calculateBestMove();

            botTurn = false;
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    class MovingAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            doMove(e);
        }

        private void doMove(MouseEvent e) {
            if (!botTurn) {
                PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                Point point = pointerInfo.getLocation();

                int pointX = getPointX((int) point.getX());
                int pointY = getPointY((int) point.getY());

                if (chessRed.contains(chessCheck[pointX][pointY])) {
                    chessSelect = chessCheck[pointX][pointY];
                    checkAction = true;
                }

                if (checkAction == true) {
                    if (canGo[pointX][pointY] == true) {
                        chessSelect.setPoint(new PointOfChess(pointX, pointY));

                        editChess();
                        remakeCanGo();
                        checkAction = false;
                        botTurn = true;
                        chessSelect = null;
                    }

                    if (canEat[pointX][pointY] == true) {
                        chessBlack.remove(chessCheck[pointX][pointY]);
                        chessSelect.setPoint(new PointOfChess(pointX, pointY));

                        editChess();
                        remakeCanEat();
                        checkAction = false;
                        botTurn = true;
                        chessSelect = null;
                        String result = checkGameOver();
                        if (result != null) return;
                    }
                }

                repaint();
                checkGameEnd();
            }
        }
    }

    public void checkGameEnd() {
        String result = checkGameOver();
        if (result != null) {
            int option = JOptionPane.showOptionDialog(this, result,
                    "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK", "Hủy"}, "OK");

            if (option == 0) {  // 0 là lựa chọn "OK"
                resetGameState();  // Khôi phục lại trạng thái ban đầu
            } else if (option == 1) {  // 1 là lựa chọn "Hủy"
                System.exit(0);  // Thoát chương trình
            }
        }
    }
}
