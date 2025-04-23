package gamePanel;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;

public class Level2 extends Level {
    public Level2(GamePanel gp) {
        super(gp, 2);
}

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        // Add specific zombies for level 2
        zombies.add(new zombie(gp, Player, "moving", 704, 512, "left", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 576, 256, "left", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 448, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 256, 256, "down"));
        zombies.add(new zombie(gp, Player, "moving", 320, 192, "down"));

        buttons.add(new button(gp, Player, "player",512 ,512, "up"));

        spikes.add(new spike(gp, Player, "button", 384, 384, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 384, 448, "up", buttons.get(0)));

        // Inserts the level tiles when you first load the level
        Player.zombieCanMove = true;
}
}
