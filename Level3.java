package gamePanel;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;


public class Level3 extends Level {
    public Level3(GamePanel gp) {
        super(gp, 3);
}

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        zombies.add(new zombie(gp, Player, "rotate", 704, 320, "down", "right"));
        zombies.add(new zombie(gp, Player, "rotate", 448, 256, "down", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 384, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 192, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 512, "left", "left"));
        
        
        buttons.add(new button(gp, Player, "player",704 ,512, "up"));
        
        spikes.add(new spike(gp, Player, "button", 704, 448, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 640, 448, "up", buttons.get(0)));
        
        spikes.add(new spike(gp, Player, "button", 512, 448, "down", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 448, 448, "down", buttons.get(0)));
        
        spikes.add(new spike(gp, Player, "button", 320, 448, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 320, 384, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 320, 320, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 320, 256, "up", buttons.get(0)));
        
        spikes.add(new spike(gp, Player, "button", 256, 384, "up", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 256, 320, "down", buttons.get(0)));
        spikes.add(new spike(gp, Player, "button", 256, 256, "down", buttons.get(0)));

        // Inserts the level tiles when you first load the level
        Player.zombieCanMove = true;
}
}
