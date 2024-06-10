package Main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Space Invaders");
            window.setResizable(false);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            try {
                window.setIconImage(ImageIO.read(new File("res/icon.png")));
            } catch (IOException e) {
                System.out.println("Icon name not found");
                e.printStackTrace();
            }
    
            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
    
            window.pack();
            
            gamePanel.startGameThread();
        });


    }
}
