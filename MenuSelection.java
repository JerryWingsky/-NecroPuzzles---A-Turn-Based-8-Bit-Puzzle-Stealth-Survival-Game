package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class MenuSelection {
    private JPanel menuPanel;
    private MainApp mainApp;
    private List<JLabel> options = new ArrayList<>();

    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 30);
    private static final Font ARROW_FONT = new Font("Arial", Font.PLAIN, 60);

    public MenuSelection(MainApp mainApp) {
        this.mainApp = mainApp;
        initializeMenu();
}

    private void initializeMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.BLACK);

        // Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.BLACK);
        logoPanel.setPreferredSize(new Dimension(800, 350));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        JLabel logoLabel = createLogoLabel("/menuResources/NecroPuzzles.png");
        logoPanel.add(logoLabel);

        // Options Panel.
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        String[] menuOptions = {"New Game", "Level Selection", "Quit"};
        for (String option : menuOptions) {
            JLabel optionLabel = createOptionLabel(option);
            options.add(optionLabel);

            JPanel optionBox = new JPanel();
            optionBox.setBackground(Color.BLACK);
            optionBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            optionBox.add(optionLabel);
            optionsPanel.add(optionBox);

            // Add Mouse Listeners for Hover Effect.
            optionLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    optionLabel.setFont(ARROW_FONT);
                    optionLabel.setText("> " + optionLabel.getText());
}

                @Override
                public void mouseExited(MouseEvent e) {
                    optionLabel.setFont(NORMAL_FONT);
                    optionLabel.setText(optionLabel.getText().substring(2));
}

                @Override
                public void mouseClicked(MouseEvent e) {
                    handleOptionClick(optionLabel);
}
});
}

        // Add Components to Menu Panel.
        menuPanel.add(logoPanel, BorderLayout.NORTH);
        menuPanel.add(optionsPanel, BorderLayout.CENTER);
}

    private JLabel createLogoLabel(String imagePath) {
        ImageIcon logoIcon = new ImageIcon(getClass().getResource(imagePath));
        JLabel logoLabel = new JLabel(logoIcon);
        if (logoIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Image file not found: " + imagePath);
            logoLabel = new JLabel("Logo not found");
            logoLabel.setForeground(Color.RED);
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
}
        return logoLabel;
}

    private JLabel createOptionLabel(String option) {
        JLabel optionLabel = new JLabel(option.toUpperCase());
        optionLabel.setFont(NORMAL_FONT);
        optionLabel.setForeground(Color.WHITE);
        return optionLabel;
}

    private void handleOptionClick(JLabel source) {
        System.out.println(source.getText() + " clicked");

        switch (source.getText()) {
            case "> NEW GAME":
                mainApp.startGame(1);
                break;
            case "> LEVEL SELECTION":
                mainApp.showLevelSelection();
                break;
            case "> QUIT":
                System.exit(0);
                break;
}
}

    public JPanel getMenuPanel() {
        return menuPanel;
}
}
