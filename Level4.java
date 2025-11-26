package gamePanel;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;


public class Level4 extends Level {
    public Level4(GamePanel gp) {
        super(gp, 4);
}

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 768, 192, "down");
        zombies.add(new zombie(gp, Player, "rotate", 512, 192, "left", "right"));
        zombies.add(new zombie(gp, Player, "rotate", 640, 192, "down", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 704, 320, "down", "right"));
        zombies.add(new zombie(gp, Player, "rotate", 448, 320, "down", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 512, 448, "down", "right"));
        zombies.add(new zombie(gp, Player, "moving", 640, 448, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 640, 512, "left", "left"));
        zombies.add(new zombie(gp, Player, "moving", 576, 448, "up", "left"));
        zombies.add(new zombie(gp, Player, "moving", 320, 320, "up", "left"));
        
        buttons.add(new button(gp, Player, "player",448 ,512, "up"));
        
        spikes.add(new spike(gp, Player, "button", 384, 192, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 256, 448, "up", buttons.get(0)));

        // Inserts the Level tiles when you first load the Level.
        Player.zombieCanMove = true;
}
}
