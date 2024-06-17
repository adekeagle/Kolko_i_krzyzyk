package pl.adcom;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Board board = new Board();
            board.setVisible(true);
        });
    }
}