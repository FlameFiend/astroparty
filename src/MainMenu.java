import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainMenu {
   public static void showMenu(JFrame frame) {
   	
       // Clear any existing content
       frame.getContentPane().removeAll();
       frame.setTitle("Game Main Menu");
       // Create a panel with plain layout
       JPanel panel = new JPanel();
       panel.setLayout(new GridLayout(3, 1, 10, 10)); // Just 3 buttons, stacked
       panel.setBackground(Color.BLACK); // Optional: black background
       // Load and scale icons
       ImageIcon startIcon = scaleIcon("src/imgs/startButton.png", 400, 200);
       ImageIcon optionsIcon = scaleIcon("src/imgs/optionsButton.png", 400, 200);
       ImageIcon exitIcon = scaleIcon("src/imgs/exitButton.png", 400, 200);
       // Create buttons
       JButton startButton = new JButton(startIcon);
       JButton optionsButton = new JButton(optionsIcon);
       JButton exitButton = new JButton(exitIcon);
       // Clean button visuals
       for (JButton button : new JButton[]{startButton, optionsButton, exitButton}) {
           button.setBorderPainted(false);
           button.setContentAreaFilled(false);
           button.setFocusPainted(false);
       }
       // Add actions
       startButton.addActionListener(e -> {
 //          frame.getContentPane().removeAll();
     //     frame.repaint();
//            frame.revalidate();
           new Frame(); // assuming your game accepts a JFrame to use
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
       panel.add(startButton);
       panel.add(optionsButton);
       panel.add(exitButton);
       // Add panel to frame
       frame.add(panel);
       frame.pack(); // Resize to fit buttons
       frame.setLocationRelativeTo(null); // Center
       frame.setVisible(true);
   }
   // Helper to scale icons
   private static ImageIcon scaleIcon(String path, int width, int height) {
       ImageIcon icon = new ImageIcon(path);
       Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
       return new ImageIcon(img);
   }
   // Main to launch the menu (only if not using another entry point)
   public static void main(String[] args) {
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       showMenu(frame);
   }
}


