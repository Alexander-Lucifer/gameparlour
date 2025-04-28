import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean playerXTurn = true;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked.getText().equals("")) {
            if (playerXTurn) {
                clicked.setForeground(Color.BLUE);
                clicked.setText("X");
            } else {
                clicked.setForeground(Color.RED);
                clicked.setText("O");
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
    }

    private void checkWinner() {
        // 8 possible winning combinations
        int[][] combos = {
                {0,1,2}, {3,4,5}, {6,7,8}, // rows
                {0,3,6}, {1,4,7}, {2,5,8}, // columns
                {0,4,8}, {2,4,6}            // diagonals
        };

        for (int[] combo : combos) {
            if (buttons[combo[0]].getText().equals(buttons[combo[1]].getText()) &&
                    buttons[combo[1]].getText().equals(buttons[combo[2]].getText()) &&
                    !buttons[combo[0]].getText().equals("")) {
                announceWinner(buttons[combo[0]].getText());
                return;
            }
        }

        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            announceWinner("Draw");
        }
    }

    private void announceWinner(String winner) {
        if (winner.equals("Draw")) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
        } else {
            JOptionPane.showMessageDialog(this, "Player " + winner + " Wins!");
        }
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
