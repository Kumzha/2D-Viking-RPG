package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;


public class Player extends Entity {
    GamePanel  gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefault();
        getPlayerImage();
    }

    public void setDefault(){
        this.x = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize()/2;
        this.y = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize()/2;
        this.speed = 2;
        direction = "down";

    }

    public void getPlayerImage(){
        try {

            downStill = ImageIO.read(new File("res/Sprites/Player/sprite_00.png"));
            moveDown1 = ImageIO.read(new File("res/Sprites/Player/sprite_01.png"));
            moveDown2 = ImageIO.read(new File("res/Sprites/Player/sprite_02.png"));

            upStill = ImageIO.read(new File("res/Sprites/Player/sprite_09.png"));
            moveUp1 = ImageIO.read(new File("res/Sprites/Player/sprite_10.png"));
            moveUp2 = ImageIO.read(new File("res/Sprites/Player/sprite_11.png"));

            leftStill = ImageIO.read(new File("res/Sprites/Player/sprite_03.png"));
            moveLeft1 = ImageIO.read(new File("res/Sprites/Player/sprite_04.png"));
            moveLeft2= ImageIO.read(new File("res/Sprites/Player/sprite_05.png"));

            rightStill = ImageIO.read(new File("res/Sprites/Player/sprite_06.png"));
            moveRight1 = ImageIO.read(new File("res/Sprites/Player/sprite_07.png"));
            moveRight2= ImageIO.read(new File("res/Sprites/Player/sprite_08.png"));
        

            
        } catch (IOException e) {
            System.out.println("Player image not found");
        }
    }

    public void update(){


        
        if(keyHandler.upPressed == true){
            y -= speed;
            direction = "up";
            isStill = false;
        }else if(keyHandler.downPressed == true){
            y += speed;
            direction = "down";
            isStill = false;
        }else if(keyHandler.rightPressed == true){
            x += speed;
            direction = "right";
            isStill = false;
        }else if(keyHandler.leftPressed == true){
            x -= speed;
            direction = "left";
            isStill = false;
        }else{
            isStill = true;
        }
  
        spriteCounter++;

        if(spriteCounter == 20){ 
            if     (spriteNum == 1) {spriteNum = 2;}
            else if(spriteNum == 2) {spriteNum = 1;}
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());

        BufferedImage image = null;

        if (isStill == true) {

            switch (direction){
                case "up":
                    image = upStill;
                    break;
                case "down":
                    image = downStill;
                    break;
                case "left":
                    image = leftStill;
                    break;
                case "right":
                    image = rightStill;
                    break;
                default:
                    image = downStill;
                    break;
            }

        }else{

            switch (direction) {
                case "up":
                    if(spriteNum == 1){image = moveUp1;}
                    if(spriteNum == 2){image = moveUp2;}
                    break;
                case "down":
                    if(spriteNum == 1){image = moveDown1;}
                    if(spriteNum == 2){image = moveDown2;}
                    break;
                case "left":
                    if(spriteNum == 1){image = moveLeft1;}
                    if(spriteNum == 2){image = moveLeft2;}
                    break;
                case "right":
                    if(spriteNum == 1){image = moveRight1;}
                    if(spriteNum == 2){image = moveRight2;}     
                    break;
                default:
                    image = downStill;
                    break;

            }
        }


        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

    }
}



