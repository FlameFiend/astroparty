import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainMenu {
   public static void main(String[] args) {
       // Create the frame
       JFrame frame = new JFrame("Game Main Menu");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 300);
       frame.setLocationRelativeTo(null); // Center the window
       // Create a panel and set layout
       JPanel panel = new JPanel();
       panel.setLayout(new GridLayout(4, 1, 10, 10));
       panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
       // Create buttons
       JButton startButton = new JButton("Start Game");
       JButton optionsButton = new JButton("Options");
       JButton exitButton = new JButton("Exit");
       // Add action listeners
       startButton.addActionListener(e -> {
           JOptionPane.showMessageDialog(frame, "Game Starting...");
           frame.dispose(); // close the menu
           new Frame(); // open your game
           // Launch game code here
       });
       optionsButton.addActionListener(e -> {
           JOptionPane.showMessageDialog(frame, "Options Menu (not implemented yet)");
       });
       exitButton.addActionListener(e -> {
           int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?");
           if (confirm == JOptionPane.YES_OPTION) {
               System.exit(0);
           }
       });
       // Add buttons to panel
       panel.add(new JLabel("Main Menu", SwingConstants.CENTER));
       panel.add(startButton);
       panel.add(optionsButton);
       panel.add(exitButton);
       // Add panel to frame and display
       frame.add(panel);
       frame.setVisible(true);
   }
}
