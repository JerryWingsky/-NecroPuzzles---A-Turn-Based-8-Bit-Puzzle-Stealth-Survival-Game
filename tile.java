package tileCode;

import java.awt.image.BufferedImage;

// This is the superclass for tiles.
public class tile {
    // This is an image.
    public BufferedImage image;
    // This is a collision for a tile.
    public boolean collision = false;
    // If the player stands here, their death (I haven't touched it).
    public boolean death = false;
    // If the player stands here, they win.
    public boolean victory = false;
    // Checks for Zombie Path.
    public boolean zombiePath = false;
    // Checks for Player Path.
    public boolean playerPath = false;
}
