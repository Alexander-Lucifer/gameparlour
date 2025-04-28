import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameParlourMainMenu extends JFrame implements ActionListener {

    private JButton flappyBirdButton;
    private JButton snakeGameButton;
    private JButton ticTacToeButton;
    private JButton pongGameButton;

    public GameParlourMainMenu() {
        setTitle("Game Parlour 🎮");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with background color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30, 30, 30)); // Dark background
        mainPanel.setLayout(new BorderLayout(20, 20)); // Top, center, bottom spacing
        add(mainPanel);

        // Title Panel
        JLabel title = new JLabel("🎮 Game Parlour 🎮", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50)); // Slightly lighter panel
        buttonPanel.setLayout(new GridLayout(4, 1, 20, 20)); // Rows, Columns, hgap, vgap
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margin around buttons
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create Buttons
        flappyBirdButton = createButton("Play Flappy Bird");
        snakeGameButton = createButton("Play Snake Game");
        ticTacToeButton = createButton("Play Tic Tac Toe");
        pongGameButton = createButton("Play Pong");

        // Add ActionListeners
        flappyBirdButton.addActionListener(this);
        snakeGameButton.addActionListener(this);
        ticTacToeButton.addActionListener(this);
        pongGameButton.addActionListener(this);

        // Add buttons to button panel
        buttonPanel.add(flappyBirdButton);
        buttonPanel.add(snakeGameButton);
        buttonPanel.add(ticTacToeButton);
        buttonPanel.add(pongGameButton);

        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flappyBirdButton) {
            FlappyBirdGame.main(new String[]{});
        } else if (e.getSource() == snakeGameButton) {
            SnakeGame.main(new String[]{});
        } else if (e.getSource() == ticTacToeButton) {
            TicTacToeGame.main(new String[]{});
        } else if (e.getSource() == pongGameButton) {
            PongGame.main(new String[]{});
        }
    }

    public static void main(String[] args) {
        new GameParlourMainMenu();
    }
}
