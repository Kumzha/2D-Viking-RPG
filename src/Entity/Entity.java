package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;


    Boolean isStill = true;

    public BufferedImage leftStill, moveLeft1, moveLeft2, rightStill, moveRight1, moveRight2, upStill, moveUp1, moveUp2, downStill, moveDown1, moveDown2, still;
    public String direction;

    public int spriteCounter;
    public int spriteNum = 1;
    
}
