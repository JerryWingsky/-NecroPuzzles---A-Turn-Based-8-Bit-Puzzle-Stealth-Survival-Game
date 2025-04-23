package gamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, escapePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    // Not used
}

    // So when the key is pressed it will set the boolean above to true
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
}
        if (code == KeyEvent.VK_S) {
            downPressed = true;
}
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
}
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
}
        if (code == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
}
}

    // Don't really mind this shit
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
}
        if (code == KeyEvent.VK_S) {
            downPressed = false;
}
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
}
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
}
        if (code == KeyEvent.VK_ESCAPE) {
            escapePressed = false;
}
}
    // This is used to reset the keys
    public void resetKeys() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        escapePressed = false;
}
}
