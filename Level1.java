package gamePanel;

import entity.player;
import entity.zombie;


public class Level1 extends Level {
    public Level1(GamePanel gp) {
        super(gp, 1);
}

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        zombies.add(new zombie(gp, Player, "moving", 640, 384, "right"));
        zombies.add(new zombie(gp, Player, "static", 448, 256, "right"));

        // Inserts the level tiles when you first load the level
        Player.zombieCanMove = true;
}
}
