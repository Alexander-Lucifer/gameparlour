import javax.swing.JPanel;


import java.awt.Graphics;


import java.awt.Color;


import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;


import java.awt.event.KeyEvent;


import java.awt.event.KeyListener;


import javax.swing.Timer;


import javax.swing.JOptionPane;





public class GamePanel extends JPanel implements ActionListener, KeyListener {


    private Timer timer;


    private Paddle leftPaddle;


    private Paddle rightPaddle;


    private Ball ball;


    private int scoreLeft;


    private int scoreRight;


    private boolean[] keys;


    private String player1Name;


    private String player2Name;


    private boolean gameOver;


    private static final int WINNING_SCORE = 5;





    public GamePanel(String player1Name, String player2Name) {


        this.player1Name = player1Name;


        this.player2Name = player2Name;





        setBackground(Color.BLACK);


        setFocusable(true);


        addKeyListener(this);





        timer = new Timer(10, this);


        keys = new boolean[256];





        // Initialize game objects


        leftPaddle = new Paddle(50, 250, 20, 100);


        rightPaddle = new Paddle(730, 250, 20, 100);


        ball = new Ball(400, 300, 20);





        scoreLeft = 0;


        scoreRight = 0;


        gameOver = false;


    }





    public void startGame() {


        timer.start();


    }





    @Override


    protected void paintComponent(Graphics g) {


        super.paintComponent(g);





        // Draw paddles


        g.setColor(Color.WHITE);


        leftPaddle.draw(g);


        rightPaddle.draw(g);





        // Draw ball


        ball.draw(g);





        // Draw score with player names


        g.setColor(Color.WHITE);


        g.drawString(player1Name + ": " + scoreLeft + " - " + player2Name + ": " + scoreRight, 250, 50);


    }





    @Override


    public void actionPerformed(ActionEvent e) {


        update();


        repaint();


    }





    private void update() {


        // Update paddle positions based on key presses


        if (keys[KeyEvent.VK_W]) {


            leftPaddle.moveUp();


        }


        if (keys[KeyEvent.VK_S]) {


            leftPaddle.moveDown();


        }


        if (keys[KeyEvent.VK_UP]) {


            rightPaddle.moveUp();


        }


        if (keys[KeyEvent.VK_DOWN]) {


            rightPaddle.moveDown();


        }





        // Update ball position


        ball.update();





        // Check for collisions


        checkCollisions();


    }





    private void checkCollisions() {


        // Ball collision with paddles


        if (ball.getBounds().intersects(leftPaddle.getBounds()) ||


                ball.getBounds().intersects(rightPaddle.getBounds())) {


            ball.reverseX();


        }





        // Ball collision with top and bottom walls


        if (ball.getY() <= 0 || ball.getY() >= getHeight() - ball.getSize()) {


            ball.reverseY();


        }





        // Ball out of bounds (scoring)


        if (ball.getX() <= 0) {


            scoreRight++;


            ball.reset();


        } else if (ball.getX() >= getWidth()) {


            scoreLeft++;


            ball.reset();


        }





        // Check for game over


        if (scoreLeft >= WINNING_SCORE || scoreRight >= WINNING_SCORE) {


            gameOver = true;


            timer.stop();





            // Save score to database







            // Show game over message


            String winner = scoreLeft > scoreRight ? player1Name : player2Name;


            JOptionPane.showMessageDialog(this,


                    "Game Over!\n" + winner + " wins!\nFinal Score: " +


                            player1Name + " " + scoreLeft + " - " + player2Name + " " + scoreRight);





            // Show high scores





        }


    }





    @Override


    public void keyPressed(KeyEvent e) {


        keys[e.getKeyCode()] = true;


    }





    @Override


    public void keyReleased(KeyEvent e) {


        keys[e.getKeyCode()] = false;


    }





    @Override


    public void keyTyped(KeyEvent e) {


        // Not used


    }


}