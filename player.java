package entity;

import gamePanel.GamePanel;
import gamePanel.keyHandler;
import gameSounds.SoundPlayer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class player extends entity {
    private int Moves = 0;
    private long lastMoveTime = 0;
    private final int cooldown = 500;
    protected boolean moved = false;
    public static boolean zombieCanMove = false;
    GamePanel gp;
    keyHandler keyH;
    private final SoundPlayer footstepSound;
    private final SoundPlayer win;
    private final SoundPlayer lose;


    // Player constructor
    public player(GamePanel gp, keyHandler keyH, int x, int y, String direction) {
        this.gp = gp;
        this.keyH = keyH;
        getPlayerImage();
        this.x = x;
        this.y = y;
        speed = 64;
        this.direction = direction;
        this.footstepSound = new SoundPlayer("/gameSounds/audioFiles/footSteps.wav");
        this.win = new SoundPlayer("/gameSounds/audioFiles/win.wav");
        this.lose = new SoundPlayer("/gameSounds/audioFiles/zombienoise.wav");
}

    // Gets the player sprite
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerUp2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerRight2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerDown2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
}
}

    // Update cycle for the player
    public void update() {
        long currentTime = System.currentTimeMillis();
        // Animation
        spriteAnim();

        // The player can only move when the cooldown is out
        if (currentTime - lastMoveTime >= cooldown) {
            int newX = x, newY = y;
            moved = false;

            // Move to directions
            if (keyH.upPressed) {
                newY -= speed;
                direction = "up";
                moved = true;
            } else if (keyH.downPressed) {
                newY += speed;
                direction = "down";
                moved = true;
            } else if (keyH.leftPressed) {
                newX -= speed;
                direction = "left";
                moved = true;
            } else if (keyH.rightPressed) {
                newX += speed;
                direction = "right";
                moved = true;
            }
            // Pause when pressing escape
            else if (keyH.escapePressed) {
                gp.pauseGame();
}

            // Check for collision with walls or other obstacle
            if (moved && canMove(newX, newY)) {
                zombieCanMove = true;
                x = newX;
                y = newY;
                lastMoveTime = currentTime;
                moveUpdate();

                footstepSound.stop();
                footstepSound.play();
}

            // Resets keys pressed
            keyH.resetKeys();
}
}

    // Check for collision with walls or other obstacle
    private boolean canMove(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;

        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
}

        return !gp.tileM.isTileCollision(col, row);
}

    // Check if the player is dead or have gone through the exit
    private boolean zombieInteracted = false; // New flag to track interaction
    public void checkTileUpdates(int x, int y) {
        if (!zombieInteracted && isDead(x, y)) {
            lose.stop();
            lose.play();

            System.out.println("Player died");
            zombieInteracted = true; // Set flag to true after interaction occurs
            gp.showDeathScreen(gp.levelNumber); // Show the death screen
}
 
        if (!zombieInteracted && isVictory(x, y)) {
            win.stop();
            win.play();

            System.out.println("Player escaped");
            zombieInteracted = true; // Set flag to true after interaction occurs
            gp.showVictoryScreen(gp.levelNumber);
}
}

    // Checks for danger tile
    private boolean isDead(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;
         
        return gp.tileM.isTileDeath(col, row);
}

    // Checks for exit tile
    private boolean isVictory(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;

        return gp.tileM.isTileVictory(col, row);
}

    // Updates the amount of moves
    private void moveUpdate() {
        Moves++;
        System.out.println("Current Moves: " + Moves);
}

    // Draw the sprite
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
}
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
}

    // Sprite animation
    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
}
}
}
