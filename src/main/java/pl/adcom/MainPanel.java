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
        String[][] possibilities = {
                {"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"},
                {"0", "3", "6"}, {"1", "4", "7"}, {"2", "5", "8"},
                {"0", "4", "8"}, {"2", "4", "6"}
        };

        for (String[] possibility : possibilities) {
            int fieldA = Integer.parseInt(possibility[0]);
            int fieldB = Integer.parseInt(possibility[1]);
            int fieldC = Integer.parseInt(possibility[2]);

            if (!buttons.get(fieldA).getText().isEmpty() &&
                    buttons.get(fieldA).getText().equals(buttons.get(fieldB).getText()) &&
                    buttons.get(fieldB).getText().equals(buttons.get(fieldC).getText())
            ) {
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
