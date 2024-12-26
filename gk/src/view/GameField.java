package view;

import javax.swing.*;
import controller.*;
import model.*;

public class GameField extends JFrame {
    public GameField() {
        setLocation(300, 0);
        setSize(550, 640);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        add(new Display());
        setVisible(true);
    }
}
 