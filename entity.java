package entity;

import java.awt.image.BufferedImage;

// The Entity Superclass so Entities can use it to instantiate their variables.
public class entity {
    public int x,y;
    public int speed;
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
