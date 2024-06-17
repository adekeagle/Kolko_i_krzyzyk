package pl.adcom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    List<JButton> buttons;
    Player currentPlayer;
    Player player1;
    Player player2;

    public MainPanel(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        GridLayout gridLayout = new GridLayout(Config.SIDE, Config.SIDE, 0, 0);
        setLayout(gridLayout);
        buttons = new ArrayList<>();

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                button.setText(currentPlayer.getSymbol());
                button.setEnabled(false);

                if (checkGameStatus()) {
                    int response = JOptionPane.showConfirmDialog(null, "Wygrana " + currentPlayer.getName(), "Koniec gry", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        disableButtons();
                    }
                } else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }

            }
        };

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.addActionListener(buttonListener);
            buttons.add(button);
            add(button);
        }
    }

    private boolean checkGameStatus() {

        for (int i = 0; i < buttons.size(); i++) {
            if (!(buttons.get(0).getText() == "") && buttons.get(0).getText() == buttons.get(1).getText() && buttons.get(1).getText() == buttons.get(2).getText()) {
                return true;
            } else if (buttons.get(3).getText() != "" && buttons.get(3).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(5).getText()) {
                return true;
            } else if (buttons.get(6).getText() != "" && buttons.get(6).getText() == buttons.get(7).getText() && buttons.get(7).getText() == buttons.get(8).getText()) {
                return true;
            } else if (buttons.get(0).getText() != "" && buttons.get(0).getText() == buttons.get(3).getText() && buttons.get(3).getText() == buttons.get(6).getText()) {
                return true;
            } else if (buttons.get(1).getText() != "" && buttons.get(1).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(7).getText()) {
                return true;
            } else if (buttons.get(2).getText() != "" && buttons.get(2).getText() == buttons.get(5).getText() && buttons.get(5).getText() == buttons.get(8).getText()) {
                return true;
            } else if (buttons.get(0).getText() != "" && buttons.get(0).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(8).getText()) {
                return true;
            } else if (buttons.get(6).getText() != "" && buttons.get(6).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(2).getText()) {
                return true;
            }
        }

        return false;
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        currentPlayer = player1;
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
