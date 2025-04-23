package gamePanel;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    protected GamePanel gp;
    protected player Player;
    protected List<zombie> zombies;
    protected List<button> buttons;
    protected List<spike> spikes;

    public Level(GamePanel gp, int levelNumber) {
        this.gp = gp;
        this.zombies = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.spikes = new ArrayList<>();
        gp.levelNumber = levelNumber;
        initializeLevel();
}

    protected abstract void initializeLevel();
    public void update() {
        Player.update();
        for (button button : buttons) {
            button.update();
}
        for (spike spike : spikes) {
            spike.update();
}
        for (zombie zombie : zombies) {
            zombie.update();
}
        Player.zombieCanMove = false;
        Player.checkTileUpdates(Player.x, Player.y);
}

    public void draw(Graphics2D g2) {
        gp.tileM.draw(g2);
        Player.draw(g2);
        for (zombie zombie : zombies) {
            zombie.draw(g2);
}
}
}
