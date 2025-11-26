package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryScreen extends JPanel {

    private static final String IMAGE_PATH = "/menuResources/Win.png";
    private static final String FONT_FAMILY = "Arial";
    private static final double LOGO_WIDTH = 400;
    private static final double FONT_SIZE_LABEL = 24;
    MainApp mainApp;
    int levelNum;

    public VictoryScreen(MainApp mainApp, int levelNum) {
        this.mainApp = mainApp;
        this.levelNum = levelNum;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create the Logo Box.
        JPanel logoBox = createLogoBox();

        // Create "YOU WIN" Label.
        JLabel youWinLabel = new JLabel("YOU WIN");
        youWinLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 48));
        youWinLabel.setForeground(Color.GREEN);
        youWinLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold the "YOU WIN" Label.
        JPanel youWinBox = new JPanel();
        youWinBox.setBackground(Color.BLACK);
        youWinBox.setLayout(new BorderLayout());
        youWinBox.add(youWinLabel, BorderLayout.CENTER);

        // Create "Next Level" Button.
        JButton nextLevelButton = new JButton("Next Level");
        nextLevelButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        nextLevelButton.setBackground(Color.WHITE);
        nextLevelButton.setForeground(Color.BLACK);
        nextLevelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        nextLevelButton.setPreferredSize(new Dimension(200, 50));
        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next Level clicked!");
                loadNextLevel();
}
});

        // Create "Quit" Button.
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(Color.BLACK);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showMenu();
}
});

        // Panel to Hold the Buttons.
        JPanel buttonBox = new JPanel();
        buttonBox.setBackground(Color.BLACK);
        buttonBox.setLayout(new GridLayout(2, 1, 20, 20));
        buttonBox.add(nextLevelButton);
        buttonBox.add(quitButton);

        // Add Components to the Main Panel.
        add(logoBox, BorderLayout.NORTH);
        add(youWinBox, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);
}

    private JPanel createLogoBox() {
        JPanel logoBox = new JPanel();
        logoBox.setBackground(Color.BLACK);
        logoBox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        ImageIcon logoImage = new ImageIcon(getClass().getResource(IMAGE_PATH));
        if (logoImage.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image image = logoImage.getImage().getScaledInstance((int) LOGO_WIDTH, -1, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(image));
            logoBox.add(logoLabel);
        } else {
            System.err.println("Image file not found: " + IMAGE_PATH);
            JLabel imageNotFoundLabel = new JLabel("Image not found");
            imageNotFoundLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, (int) FONT_SIZE_LABEL));
            imageNotFoundLabel.setForeground(Color.RED);
            logoBox.add(imageNotFoundLabel);
}

        return logoBox;
}
    public void loadNextLevel(){
        try {
            mainApp.startGame(levelNum + 1); // Makes sure that it goes to the Next Level.
            System.out.println("Load next level");
        } catch (IllegalArgumentException e){
            System.out.println("Invalid level number: " + (levelNum + 1));
            displayEndMessage();
}
}
    public void updateLevelNumber(int levelNum) {
        this.levelNum = levelNum;
}

    private void displayEndMessage() {
        // Remove current components.
        removeAll();

        // Create "Thank you for playing" Label.
        JLabel endMessageLabel = new JLabel("Thank you for playing!");
        endMessageLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 48));
        endMessageLabel.setForeground(Color.GREEN);
        endMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add Label to the Panel.
        add(endMessageLabel, BorderLayout.CENTER);

        // Create "Main Menu" Button.
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        mainMenuButton.setBackground(Color.WHITE);
        mainMenuButton.setForeground(Color.BLACK);
        mainMenuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainMenuButton.setPreferredSize(new Dimension(200, 50));
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showMenu();
}
});

        // Panel to Hold the Button.
        JPanel buttonBox = new JPanel();
        buttonBox.setBackground(Color.BLACK);
        buttonBox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonBox.add(mainMenuButton);

        // Add Button Panel to the Main Panel.
        add(buttonBox, BorderLayout.SOUTH);

        // Refresh the Panel to display the message.
        revalidate();
        repaint();
}
}
