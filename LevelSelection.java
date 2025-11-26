package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelSelection {
    private JPanel levelPanel;
    private MainApp mainApp;
    private List<JLabel> currentLevels = new ArrayList<>();
    private List<JLabel> levels = new ArrayList<>();

    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 30);
    private static final Font HIGHLIGHT_FONT = new Font("Arial", Font.BOLD, 40);
    private static final Font ARROW_FONT = new Font("Arial", Font.PLAIN, 60);

    public LevelSelection(MainApp mainApp) {
        this.mainApp = mainApp;
        initializeLevels();
}

    private void initializeLevels() {
        levelPanel = new JPanel();
        levelPanel.setLayout(new BorderLayout());
        levelPanel.setBackground(Color.BLACK);

        // Title Panel.
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(800, 100));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        Component topStrut = Box.createVerticalStrut(30); // Create Vertical Strut to add space.
        titlePanel.add(topStrut);

        JLabel titleLabel = new JLabel("GAME LEVELS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the Label horizontally.
        titlePanel.add(titleLabel);

        // Levels Panel.
        JPanel levelsPanel = new JPanel();
        levelsPanel.setBackground(Color.BLACK);
        levelsPanel.setLayout(new BoxLayout(levelsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 4; i++) {
            JLabel levelLabel = createLevelLabel(i + 1);
            levels.add(levelLabel);
            JLabel currentLevel = createCurrentLevelLabel();
            currentLevels.add(currentLevel);

            JPanel levelBox = new JPanel();
            levelBox.setBackground(Color.BLACK);
            levelBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            levelBox.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add Margin on Top & Bottom.
            levelBox.add(currentLevel);
            levelBox.add(levelLabel);
            levelsPanel.add(levelBox);
}

        // Back Label.
        JLabel backLabel = new JLabel("BACK");
        backLabel.setFont(NORMAL_FONT);
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBackClick();
}

            @Override
            public void mouseEntered(MouseEvent e) {
                backLabel.setFont(HIGHLIGHT_FONT);
}

            @Override
            public void mouseExited(MouseEvent e) {
                backLabel.setFont(NORMAL_FONT);
}
});

        // Back Panel.
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.BLACK);
        backPanel.setPreferredSize(new Dimension(800, 100));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        backPanel.add(backLabel);

        // Add Components to the Level Panel.
        levelPanel.add(titlePanel, BorderLayout.NORTH);
        levelPanel.add(levelsPanel, BorderLayout.CENTER);
        levelPanel.add(backPanel, BorderLayout.SOUTH);

        // Select the First Level.
        selectCurrentLevel(0);
}

    private JLabel createLevelLabel(int levelNumber) {
        JLabel levelLabel = new JLabel("LEVEL " + levelNumber);
        levelLabel.setFont(NORMAL_FONT);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLevelClick(levelLabel);
}

            @Override
            public void mouseEntered(MouseEvent e) {
                levelLabel.setFont(ARROW_FONT);
                levelLabel.setText("> " + levelLabel.getText());
}

            @Override
            public void mouseExited(MouseEvent e) {
                levelLabel.setFont(NORMAL_FONT);
                levelLabel.setText(levelLabel.getText().substring(2));
}
});
        return levelLabel;
}

    private JLabel createCurrentLevelLabel() {
        JLabel currentLevel = new JLabel("");
        currentLevel.setFont(ARROW_FONT);
        currentLevel.setForeground(Color.BLACK);
        return currentLevel;
}

    private void handleLevelClick(JLabel source) {
        int index = levels.indexOf(source);
        selectCurrentLevel(index);
        System.out.println(source.getText() + " clicked");
        mainApp.startGame(index + 1);
}

    private void handleBackClick() {
        System.out.println("Back clicked");
        mainApp.showMenu();
}

    private void selectCurrentLevel(int index) {
        for (JLabel label : currentLevels) {
            label.setForeground(Color.BLACK);
}
        currentLevels.get(index).setForeground(Color.WHITE);
}

    public JPanel getLevelPanel() {
        return levelPanel;
}
}
