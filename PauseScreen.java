package menuScreens;

import gamePanel.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JPanel {

    private GamePanel gamePanel;
    private MainApp mainApp;

    public PauseScreen(MainApp mainApp, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mainApp = mainApp;
        initialize();
}

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 20)); // 4 rows, 1 column, 20px vertical gap
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Increased padding

        // Create buttons
        JButton resumeButton = createButton("Resume");
        JButton restartButton = createButton("Restart");
        JButton backToMenuButton = createButton("Back to Menu");

        // Add listeners to buttons
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeGame();
}
});
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
}
});
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMenu();
}
});

        // Add buttons to panel
        buttonPanel.add(resumeButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(backToMenuButton);

        // Create label for "Paused" text
        JLabel pausedLabel = new JLabel("Paused", SwingConstants.CENTER);
        pausedLabel.setForeground(Color.WHITE);
        pausedLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size

        // Create panel for the "Paused" label
        JPanel pausedPanel = new JPanel(new BorderLayout());
        pausedPanel.setBackground(Color.BLACK);
        pausedPanel.add(pausedLabel, BorderLayout.CENTER);
        pausedPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0)); // Adjusted padding

        // Add panels to main content pane
        add(buttonPanel, BorderLayout.CENTER);
        add(pausedPanel, BorderLayout.NORTH);
}

    // Method to create a styled JButton
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 24)); // Larger font size
        button.setMargin(new Insets(20, 40, 20, 40)); // Increased padding
        return button;
}

    // Resume the game
    private void resumeGame() {
        this.setVisible(false);

        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        gamePanel.resumeGame();
}

    // Restart the game
    private void restartGame() {
        // Hide the panel
        this.setVisible(false);
        // Reload current level
        gamePanel.loadLevel(gamePanel.levelNumber);
        // Ensure game panel gets focus
        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        System.out.println("Game restarted");
}

    // Go back to the main menu
    private void backToMenu() {
        System.out.println("Back to menu");
        gamePanel.stopGame();
        this.setVisible(false); // Hide the panel
        mainApp.showMenu(); // Show the main menu
}
}
