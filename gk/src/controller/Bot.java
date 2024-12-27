package controller;

import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends FrameChess {

    private Display display;

    public Bot(Display display) {
        super();
        this.display = display;  // Nhận Display từ View
    }

    public void calculateBestMove() {
        Random random = new Random();
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();

        // Duyệt qua các quân cờ đen (bot) để tính toán các nước đi và ăn
        for (Chess chess : chessBlack) {
            newGameMoves.addAll(chess.botCanEat(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
            newGameMoves.addAll(chess.botCanGo(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
        }

        // Chọn một nước đi ngẫu nhiên từ các nước đi hợp lệ
        chessCheck = newGameMoves.get(random.nextInt(newGameMoves.size()));
        newGameMoves.clear();

        // Cập nhật lại danh sách các quân cờ đen và đỏ sau khi bot thực hiện nước đi
        chessBlack.clear();
        chessRed.clear();

        // Cập nhật lại trạng thái của các quân cờ trên bàn
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
        String result = checkGameOver();
        if (result != null) return;
        // Cập nhật lại giao diện hiển thị
        display.repaint();
        display.checkGameEnd();
    }
}
