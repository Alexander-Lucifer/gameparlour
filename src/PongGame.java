import javax.swing.JFrame;


import javax.swing.JOptionPane;


import javax.swing.JTextField;


import java.awt.GridLayout;


import javax.swing.JPanel;


import javax.swing.JLabel;


import java.awt.Dimension;
import java.awt.Toolkit;





public class PongGame {


    public static void main(String[] args) {











        // Create input panel for player names


        JPanel inputPanel = new JPanel(new GridLayout(2, 2));


        JTextField player1Field = new JTextField();


        JTextField player2Field = new JTextField();





        inputPanel.add(new JLabel("Player 1 Name:"));


        inputPanel.add(player1Field);


        inputPanel.add(new JLabel("Player 2 Name:"));


        inputPanel.add(player2Field);





        // Show dialog to get player names


        int result = JOptionPane.showConfirmDialog(null, inputPanel,


                "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);





        if (result == JOptionPane.OK_OPTION) {


            String player1Name = player1Field.getText().trim();


            String player2Name = player2Field.getText().trim();





            // Use default names if empty


            if (player1Name.isEmpty()) player1Name = "Player 1";


            if (player2Name.isEmpty()) player2Name = "Player 2";





            // Create and show game window


            JFrame frame = new JFrame("Pong Game");


            GamePanel gamePanel = new GamePanel(player1Name, player2Name, frame);





            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            frame.setSize(800, 600);


            frame.add(gamePanel);


            // Center the window manually
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 2;
            int y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);


            frame.setResizable(false);


            frame.setVisible(true);





            gamePanel.startGame();


        }


    }


}