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
    private Chess[][] initialChessState;  // Lưu trạng thái ban đầu của bàn cờ

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

    @Override
    public void checkGameEnd() {
        String result = checkGameOver();
        if (result != null) {
            // Hiển thị hộp thoại chỉ với nút "Hủy"
            int option = JOptionPane.showOptionDialog(this, result,
                    "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Hủy"}, "Hủy");

            // Nếu người chơi chọn "Hủy", thoát khỏi chương trình
            if (option == 0) {  // 0 là lựa chọn "Hủy"
                System.exit(0);  // Thoát chương trình
            }
        }
    }


    // Phương thức sao chép trạng thái bàn cờ
    private void copyChessState(Chess[][] source, Chess[][] destination) {
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (source[i][j] != null) {
                    destination[i][j] = (Chess) source[i][j].clone();  // Clone each chess piece to avoid reference issues
                }
            }
        }
    }

    // Phương thức khôi phục lại trạng thái ban đầu
    private void resetGameState() {
        // Khôi phục lại trạng thái bàn cờ từ ban đầu
        copyChessState(initialChessState, chessCheck);

        // Xóa tất cả quân cờ trong danh sách
        chessRed.clear();
        chessBlack.clear();

        // Cập nhật lại danh sách các quân cờ đỏ và đen từ trạng thái ban đầu
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

        // Vẽ lại bàn cờ
        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(chineseChess, 0, 0, null);

        // Vẽ các quân cờ đỏ
        for (Chess chess : chessRed) {
            g2d.drawImage(chess.getImageChessR(), pointOfChessX[chess.getPoint().getX()] - 340,
                    pointOfChessY[chess.getPoint().getY()] - 55, null);
        }

        // Vẽ các quân cờ đen
        for (Chess chess : chessBlack) {
            g2d.drawImage(chess.getImageChessB(), pointOfChessX[chess.getPoint().getX()] - 340,
                    pointOfChessY[chess.getPoint().getY()] - 55, null);
        }

        // Kiểm tra nếu có quân cờ được chọn để di chuyển
        if (checkAction && chessSelect != null) {
            canGo = chessSelect.pointCanGo(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(),
                    g2d, pointOfChessX, pointOfChessY);
            canEat = chessSelect.pointCanEat(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(),
                    g2d, pointOfChessX, pointOfChessY);
        }

        // Nếu đến lượt bot, tính toán nước đi
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

                if (checkAction) {
                    if (canGo[pointX][pointY]) {
                        chessSelect.setPoint(new PointOfChess(pointX, pointY));
                        editChess();
                        remakeCanGo();
                        checkAction = false;
                        botTurn = true;
                        chessSelect = null;
                    }

                    if (canEat[pointX][pointY]) {
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
}
