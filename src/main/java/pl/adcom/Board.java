package pl.adcom;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    public Board() throws HeadlessException {
        setSize(Config.MAX_X, Config.MAX_Y);
        setTitle("Kółko i krzyżyk");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Player player1 = new Player(1, "Gracz X", "X");
        Player player2 = new Player(2, "Gracz O", "O");

        add(new MainPanel(player1, player2));


    }
}
