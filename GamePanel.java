package gamePanel;

import tileCode.tileManager;
import java.awt.*;
import javax.swing.JPanel;

import menuScreens.MainApp;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int size = 4;
    public int tileSize = originalTileSize * size;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    public tileManager tileM;
    keyHandler keyH = new keyHandler();
    private Thread gameThread;
    public Level currentLevel;
    private MainApp mainApp;
    public int levelNumber;
    private boolean running;


    public GamePanel(MainApp mainApp) {
        this.mainApp = mainApp;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
}

    public keyHandler getKeyHandler() {
        return keyH;
}

    public void loadLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                currentLevel = new Level1(this);
                break;
            case 2:
                currentLevel = new Level2(this);
                break;
            case 3:
                currentLevel = new Level3(this);
                break;
            case 4:
                currentLevel = new Level4(this);
                break;
            
            default:
                throw new IllegalArgumentException("Invalid level number");
}
        this.tileM = new tileManager(this, levelNumber);
        System.out.println("Loaded level: " + levelNumber);
        startGameThread();
}

    public void pauseGame() {
        if (mainApp != null) {
            mainApp.showPauseScreen(); // Show the pause screen
            this.stopGame(); // Stop the game loop
            System.out.println("Game paused");
}
}

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            running = true;
            gameThread.start();
}
}

    public void resumeGame() {
        startGameThread();
        System.out.println("Game resumed");
}

    public void stopGame() {
        running = false;
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
}
}


    @Override
    public void run() {
        while (running) {
            update();
            repaint();

            try {
                Thread.sleep(16); // Approx 60 FPS
            } catch (InterruptedException e) {
                // Handle the interrupt, probably by breaking the loop
                running = false;
                System.err.println("Game thread interrupted");
}
}
}

    public void update() {
        if (currentLevel != null) {
            currentLevel.update();
}
}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (currentLevel != null) {
            currentLevel.draw(g2);
}
        g2.dispose();
}

    public void showDeathScreen(int levelNum) {
        gameThread = null; // Stop the game thread
        mainApp.showDeathScreen(levelNum); // Transition to the death screen
}

    public void showVictoryScreen(int levelNum) {
        gameThread = null;
        mainApp.showVictoryScreen(levelNum);
}
}
