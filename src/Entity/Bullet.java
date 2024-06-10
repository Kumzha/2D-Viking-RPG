package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Bullet extends Entity {
    GamePanel gamePanel;
    int speed = 1;

    public Bullet(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;

        this.x = player.x;
        this.y = player.y;

        getBulletImage();
    }
    public void getBulletImage(){
        try {
            still = ImageIO.read(new File("res/spriteAmmo.png"));
            
        } catch (IOException e) {
            System.out.println("Bullet image not found");
        }
    }

    public void update(){
        x -= speed;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = still;
        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

    }
}
