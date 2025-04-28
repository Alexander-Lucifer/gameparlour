import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {
    private int birdY = 250;
    private int birdVelocity = 0;
    private final int gravity = 1;
    private boolean gameRunning = false;
    private boolean showHomePage = true;
    private int score = 0;
    private static final int HIGH_SCORE_X = 10;
    private static final int HIGH_SCORE_Y = 30;
    private int highScore;
    private long startTime;
    private long playTime;

    private ArrayList<Rectangle> pillars;
    private Timer timer;
    private JButton startButton;

    public FlappyBirdGame() {
        setPreferredSize(new Dimension(400, 500));
        setBackground(Color.CYAN);
        setLayout(null); // we'll position button manually
        addKeyListener(this);
        setFocusable(true);

        pillars = new ArrayList<>();
        timer = new Timer(20, this);

        highScore = DatabaseUtil.getHighScore("FlappyBird");
        startTime = System.currentTimeMillis();

        setupHomePage();
    }

    private void setupHomePage() {
        startButton = new JButton("Start Flappy Bird");
        startButton.setBounds(120, 200, 160, 50);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> {
            showHomePage = false;
            startButton.setVisible(false);
            requestFocusInWindow();
            startGame();
        });
        add(startButton);
    }

    private void startGame() {
        gameRunning = true;
        birdY = 250;
        birdVelocity = -10;
        pillars.clear();
        spawnPillar();
        score = 0;
        timer.start();
    }

    private void spawnPillar() {
        int gap = 120;
        int height = new Random().nextInt(200) + 50;
        pillars.add(new Rectangle(400, 0, 50, height)); // Top pillar
        pillars.add(new Rectangle(400, height + gap, 50, 500 - height - gap)); // Bottom pillar
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (showHomePage) {
            drawHomePage(g);
        } else {
            drawGame(g);
        }
    }

    private void drawHomePage(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Welcome to Flappy Bird!", 50, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Press Start to Play", 120, 250);
    }

    private void drawGame(Graphics g) {
        // Draw Bird
        g.setColor(Color.ORANGE);
        g.fillOval(100, birdY, 30, 30);

        // Eye (white part)
        g.setColor(Color.WHITE);
        g.fillOval(110, birdY + 5, 10, 10);

        // Pupil (black dot)
        g.setColor(Color.BLACK);
        g.fillOval(113, birdY + 7, 5, 5);

        // Beak
        g.setColor(Color.YELLOW);
        int[] beakX = {125, 135, 125};
        int[] beakY = {birdY + 12, birdY + 15, birdY + 18};
        g.fillPolygon(beakX, beakY, 3);

        // Draw Pillars
        g.setColor(Color.GREEN);
        for (Rectangle pillar : pillars) {
            g.fillRect(pillar.x, pillar.y, pillar.width, pillar.height);
        }

        // Draw Scores
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("High Score: " + highScore, HIGH_SCORE_X, HIGH_SCORE_Y);

        // Instructions or Game Over
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (!gameRunning && score == 0) {
            g.drawString("Press SPACE to Start", 100, 250);
        } else if (!gameRunning && score > 0) {
            g.drawString("Game Over!", 140, 200);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press SPACE to Restart", 110, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            birdY += birdVelocity;
            birdVelocity += gravity;

            for (int i = 0; i < pillars.size(); i++) {
                pillars.get(i).x -= 5;
            }

            if (pillars.get(0).x + pillars.get(0).width < 0) {
                pillars.remove(0);
                pillars.remove(0);
                spawnPillar();
                score++;
            }

            for (Rectangle pillar : pillars) {
                if (pillar.intersects(new Rectangle(100, birdY, 30, 30))) {
                    endGame();
                }
            }

            if (birdY > 470 || birdY < 0) {
                endGame();
            }
        }
        repaint();
    }

    private void endGame() {
        gameRunning = false;
        timer.stop();
        playTime = (System.currentTimeMillis() - startTime) / 1000; // Convert to seconds
        if (score > highScore) {
            highScore = score;
            DatabaseUtil.saveGameScore("FlappyBird", score, playTime);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!showHomePage) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!gameRunning) {
                    startGame();
                } else {
                    birdVelocity = -10;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBirdGame game = new FlappyBirdGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
