package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Enemy extends Entity {
    GamePanel  gamePanel;
    int speed = 1;

    public void getEnemyImage(){
        try {
            still = ImageIO.read(new File("res/spriteAmmo.png"));
        }catch (Exception e) {
            System.out.println("Enemy image not found");
        }
            
    }

    public void update(){
        // y = y + speed;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
    

    public Enemy(GamePanel gamePanel, int i) {
        this.gamePanel = gamePanel;

        this.x = gamePanel.getTileSize()/2;
        this.y = gamePanel.getTileSize()*i - gamePanel.getTileSize()/2;
        getEnemyImage();
    }
    
    // public void spawnEnemy(GamePanel gamePanel, int i){
    //     //Eiliu turime
        

        

    // }



    //Helper spawn funkcija
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }
    
}
