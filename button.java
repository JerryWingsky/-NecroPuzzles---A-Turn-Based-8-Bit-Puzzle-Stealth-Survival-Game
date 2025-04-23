package entity;

import gamePanel.GamePanel;
import gameSounds.SoundPlayer;

public final class button extends entity {
    GamePanel gp;
    String type, state;
    private final player Player;
    private final SoundPlayer buttonSound;

    // Just a constructor to connect the main game panel
    public button(GamePanel gp, player Player, String type, int x, int y, String state) {
        this.gp = gp;
        this.Player = Player;
        this.type = type;
        this.x = x;
        this.y = y;
        this.state = state;
        this.buttonSound = new SoundPlayer("/gameSounds/audioFiles/button_press.wav");
}

    public void update() {
        if (Player.zombieCanMove) {
            // Only player can change the state of this button
            if (type.equals("player")) {
                // Changes the button state from up to down
                if (Player.x == x && Player.y == y && state == "up") {
                    state = "down";
                    System.out.println("Button pushed");

                    buttonSound.stop();
                    buttonSound.play();
}
}

        else if (type.equals("box")) {

}         
        chgBtnState(state);
}
}

    // Apply the changes made from the update(); method
    public void chgBtnState (String state) {
        int col = x / gp.tileSize;
        int row = y / gp.tileSize;
        if (state.equals("up")){
            gp.tileM.updateTile(col, row, 44);
}    
        else if (state.equals("down")){
            gp.tileM.updateTile(col, row, 43);
}
}
}
