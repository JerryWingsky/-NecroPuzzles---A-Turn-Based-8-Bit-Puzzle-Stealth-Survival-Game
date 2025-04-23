package menuScreens;

import gamePanel.GamePanel;
import gameSounds.SoundPlayer;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private MenuSelection menuSelection;
    private LevelSelection levelSelection;
    private GamePanel gamePanel;
    private DeathScreen deathScreen;
    private VictoryScreen victoryScreen;
    private PauseScreen pauseScreen;
    private SoundPlayer backgroundMusic;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().createAndShowGUI());
}

    private void createAndShowGUI() {
        frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setResizable(false); // Make the window non-resizable

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        menuSelection = new MenuSelection(this);
        levelSelection = new LevelSelection(this);
        gamePanel = new GamePanel(this);

        cardPanel.add(menuSelection.getMenuPanel(), "Menu");
        cardPanel.add(levelSelection.getLevelPanel(), "LevelSelection");
        cardPanel.add(gamePanel, "Game");

        frame.add(cardPanel);
        frame.setVisible(true);

        showMenu();

        this.backgroundMusic = new SoundPlayer("/gameSounds/audioFiles/music.wav");
        backgroundMusic.setVolume(0.74f);
        backgroundMusic.loop();

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (gamePanel != null) {
                    gamePanel.stopGame(); // Stop the game loop
}
                System.exit(0);
}
});
}

    public void showMenu() {
        cardLayout.show(cardPanel, "Menu");
}

    public void showLevelSelection() {
        cardLayout.show(cardPanel, "LevelSelection");
}

    public void startGame(int levelNumber) {
        if (gamePanel != null) {
            gamePanel.stopGame();
}
        gamePanel.loadLevel(levelNumber);
        cardLayout.show(cardPanel, "Game");
        gamePanel.requestFocus();
}

    public void showDeathScreen(int levelNum) {
        if (deathScreen == null) {
            deathScreen = new DeathScreen(this, levelNum); // Pass the main app instance to the death screen
}
        else {
            deathScreen.updateLevelNumber(levelNum); // Ensure level number is updated
}
        cardPanel.add(deathScreen, "DeathScreen");
        cardLayout.show(cardPanel, "DeathScreen");
}

    public void showVictoryScreen(int levelNum) {
        if (victoryScreen == null) {
            victoryScreen = new VictoryScreen(this, levelNum);
}
        else {
            victoryScreen.updateLevelNumber(levelNum); // Ensure level number is updated
}
        cardPanel.add(victoryScreen,"VictoryScreen");
        cardLayout.show(cardPanel,"VictoryScreen");
}

    public void showPauseScreen() {
        if (pauseScreen == null) {
            pauseScreen = new PauseScreen(this, gamePanel); // Pass GamePanel to PauseScreen
}
        cardPanel.add(pauseScreen, "PauseScreen");
        cardLayout.show(cardPanel, "PauseScreen");
}
}
