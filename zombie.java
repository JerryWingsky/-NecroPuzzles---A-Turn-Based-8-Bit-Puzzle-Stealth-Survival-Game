package entity;

import gamePanel.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class zombie extends entity {
    GamePanel gp;
    String direction, type, rotation;
    private final player Player;

    // Moving zombie or static zombie constructor
    public zombie(GamePanel gp, player Player, String type, int x, int y, String direction) {
        this(gp, Player, type, x, y, direction, null);
}

    // Main constructor (optimized for rotating zombie)
    public zombie(GamePanel gp, player Player, String type, int x, int y, String direction, String rotation) {
        this.gp = gp;
        this.Player = Player;
        this.type = type;
        this.x = x;
        this.y = y;
        speed = 64;
        this.direction = direction;
        this.rotation = rotation;
        getZombieImage();
}

    // To get the zombie image & animation
    public void getZombieImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/ZombieSprite1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/Zombie11.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/ZombieSprite3.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/Zombie31.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/ZombieSprite4.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/Zombie41.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/ZombieSprite2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/Zombie21.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
}
}

    // This is where the zombie movement is declared
    public void update() {
        // Basically the frame changes 3 times every second, declaring it for the sprite
        spriteAnim();

        int newX = x, newY = y;

        if (Player.zombieCanMove) {
            // For moving zombies
            if (type.equals("moving")) {
                if (direction.equals("left") && checkLeft(x, y)) { // if left tile is a path tile
                    newX -= speed;
                    updateLeftDeathTile(x, y);
                } else if (direction.equals("right") && checkRight(x, y)) { // if right tile is a path tile
                    newX += speed;
                    updateRightDeathTile(x, y);
                } else if (direction.equals("up") && checkUp(x, y)) { // if right tile is a path tile
                    newY -= speed;
                    updateUpDeathTile(x, y);
                } else if (direction.equals("down") && checkDown(x, y)) { // if right tile is a path tile
                    newY += speed;
                    updateDownDeathTile(x, y);
}
                // Check if the zombie has reached the end of the path
                else if (direction.equals("left") && !checkLeft(newX, newY)) {
                    direction = "right";
                    updateRightDeathTile(x - speed, y);
                } else if (direction.equals("right") && !checkRight(newX, newY)) {
                    direction = "left";
                    updateLeftDeathTile(x + speed, y);
                } else if (direction.equals("up") && !checkUp(newX, newY)) {
                    direction = "down";
                    updateDownDeathTile(x, y - speed);
                } else if (direction.equals("down") && !checkDown(newX, newY)) {
                    direction = "up";
                    updateUpDeathTile(x, y + speed);
}
}

        // For rotating zombies
        else if (type.equals("rotate")){
            // If zombie rotates to the right
            if (rotation.equals("right")){
                if (direction.equals("left")) {
                    direction = "up";
                    updateUpDeathTile(x, y + speed);
                } else if (direction.equals("up")) {
                    direction = "right";
                    updateRightDeathTile(x - speed, y);
                } else if (direction.equals("right")) {
                    direction = "down";
                    updateDownDeathTile(x, y - speed);
                } else if (direction.equals("down")) {
                    direction = "left";
                    updateLeftDeathTile(x + speed, y);
}
}

        // If zombie rotates to the left
        if (rotation.equals("left")){
            if (direction.equals("left")) {
                direction = "down";
                updateDownDeathTile(x, y - speed);
            } else if (direction.equals("up")) {
                direction = "left";
                updateLeftDeathTile(x + speed, y);
            } else if (direction.equals("right")) {
                direction = "up";
                updateUpDeathTile(x, y + speed);
            } else if (direction.equals("down")) {
                direction = "right";
                updateRightDeathTile(x - speed, y);
}
}
}
        // Updates the current location to the zombies
        x = newX;
        y = newY;
}
}

    // From this point here, the mess starts to pile up... sorry bout that ;-;
    // The code is dumb as rocks, but it works ¯\_(ツ)_/¯

    // Check left tile for path
    public boolean checkLeft(int x, int y) {
        int col = (x - speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
}

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
}

    //check right tile for path
    public boolean checkRight(int x, int y) {
        int col = (x + speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
}

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
}

    // Check above tile for path
    public boolean checkUp(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y - speed) / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
}

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
}

    // Check below tile for path
    public boolean checkDown(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y + speed) / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
}

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
}


    // Updates left tile and surroundings to its respective tile type
    public void updateLeftDeathTile(int x, int y) {
        int col = (x - speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Set tile beneath zombie to death tile
        if (gp.tileM.isZombiePath(col, row)){
            gp.tileM.updateTile(col, row, 25);
}
        else if (gp.tileM.isPlayerPath(col, row)){
            gp.tileM.updateTile(col, row, 35);
}

        // Set tile in front of zombie to death tile
        if (gp.tileM.isZombiePath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 25);
}
        else if (gp.tileM.isPlayerPath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 35);
}

        // Set tile behind zombie into normal tiles
        if (gp.tileM.isZombiePath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 24);
}
        else if (gp.tileM.isTileDeath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 34);
}

    // Updates for the rotating zombie
    if (type.equals("rotate")) {
        // Set tile left of zombie to normal
        if (gp.tileM.isZombiePath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 24);
        } else if (gp.tileM.isTileDeath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 34);
}

        // Set tile right of zombie to normal
        if (gp.tileM.isZombiePath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 24);
        } else if (gp.tileM.isTileDeath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 34);
}
}
}

    // Updates right tile and surroundings to its respective tile type
    public void updateRightDeathTile(int x, int y) {
        int col = (x + speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Set tile beneath zombie to death tile
        if (gp.tileM.isZombiePath(col, row)){
            gp.tileM.updateTile(col, row, 25);
}
        else if (gp.tileM.isPlayerPath(col, row)){
            gp.tileM.updateTile(col, row, 35);
}

        // Set tile in front of zombie to death tile
        if (gp.tileM.isZombiePath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 25);
}
        else if (gp.tileM.isPlayerPath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 35);
}

        // Set tile behind zombie into normal tiles
        if (gp.tileM.isZombiePath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 24);
}
        else if (gp.tileM.isTileDeath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 34);
}

    // updates for the rotating zombie
    if (type.equals("rotate")) {
        // Set tile left of zombie to normal
        if (gp.tileM.isZombiePath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 24);
        } else if (gp.tileM.isTileDeath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 34);
}

        // Set tile right of zombie to normal
        if (gp.tileM.isZombiePath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 24);
        } else if (gp.tileM.isTileDeath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 34);
}
}
}

    // Updates above tile and surroundings to its respective tile type
    public void updateUpDeathTile(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y - speed) / gp.tileSize;

        // Set tile beneath zombie to death tile
        if (gp.tileM.isZombiePath(col, row)){
            gp.tileM.updateTile(col, row, 25);
}
        else if (gp.tileM.isPlayerPath(col, row)){
            gp.tileM.updateTile(col, row, 35);
}

        // Set tile in front of zombie to death tile
        if (gp.tileM.isZombiePath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 25);
}
        else if (gp.tileM.isPlayerPath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 35);
}

        // Set tile behind zombie into normal tiles
        if (gp.tileM.isZombiePath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 24);
}
        else if (gp.tileM.isTileDeath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 34);
}

        // Updates for the rotating zombie
        if (type.equals("rotate")) {
            //set tile left of zombie to normal
            if (gp.tileM.isZombiePath(col-1, row)){
                gp.tileM.updateTile(col-1, row, 24);
            } else if (gp.tileM.isTileDeath(col-1, row)){
                gp.tileM.updateTile(col-1, row, 34);
}

            // Set tile right of zombie to normal
            if (gp.tileM.isZombiePath(col+1, row)){
                gp.tileM.updateTile(col+1, row, 24);
            } else if (gp.tileM.isTileDeath(col+1, row)){
                gp.tileM.updateTile(col+1, row, 34);
}
}
}

    // Updates below tile and surroundings to its respective tile type
    public void updateDownDeathTile(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y + speed) / gp.tileSize;

        // Set tile in front of zombie to death tile
        if (gp.tileM.isZombiePath(col, row)){
            gp.tileM.updateTile(col, row, 25);
}
        else if (gp.tileM.isPlayerPath(col, row)){
            gp.tileM.updateTile(col, row, 35);
}

        // Set tile in front of zombie to death tile
        if (gp.tileM.isZombiePath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 25);
}
        else if (gp.tileM.isPlayerPath(col, row+1)){
            gp.tileM.updateTile(col, row+1, 35);
}

        // Set tile behind zombie into normal tiles
        if (gp.tileM.isZombiePath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 24);
}
        else if (gp.tileM.isTileDeath(col, row-1)){
            gp.tileM.updateTile(col, row-1, 34);
}

    // Updates for the rotating zombie
    if (type.equals("rotate")) {
        // Set tile left of zombie to normal
        if (gp.tileM.isZombiePath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 24);
        } else if (gp.tileM.isTileDeath(col+1, row)){
            gp.tileM.updateTile(col+1, row, 34);
        }

    // Set tile right of zombie to normal
        if (gp.tileM.isZombiePath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 24);
        } else if (gp.tileM.isTileDeath(col-1, row)){
            gp.tileM.updateTile(col-1, row, 34);
}
}
}


    // Draw the sprite
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "left" -> image = (spriteNum == 1) ? left1 : left2;
            case "right" -> image = (spriteNum == 1) ? right1 : right2;
            case "up" -> image = (spriteNum == 1) ? up1 : up2;
            case "down" -> image = (spriteNum == 1) ? down1 : down2;
}
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
}

    // Sprite animation
    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 40) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
}
}
}
