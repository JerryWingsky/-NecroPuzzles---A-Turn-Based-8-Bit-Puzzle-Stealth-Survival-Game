package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathScreen extends JPanel {

    private static final String IMAGE_PATH = "/menuResources/Dead.png";
    private static final String FONT_FAMILY = "Arial";
    private static final double LOGO_WIDTH = 400;
    private static final double FONT_SIZE_LABEL = 24;
    MainApp mainApp;
    int levelNum;

    public DeathScreen(MainApp mainApp, int levelNum) {
        this.mainApp = mainApp;
        this.levelNum = levelNum;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create the logo box
        JPanel logoBox = createLogoBox();

        // Create "YOU LOSE" label
        JLabel youLoseLabel = new JLabel("YOU LOSE");
        youLoseLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 48));
        youLoseLabel.setForeground(Color.RED);
        youLoseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold the "YOU LOSE" label
        JPanel youLoseBox = new JPanel();
        youLoseBox.setBackground(Color.BLACK);
        youLoseBox.setLayout(new BorderLayout());
        youLoseBox.add(youLoseLabel, BorderLayout.CENTER);

        // Create "Restart" button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        restartButton.setBackground(Color.WHITE);
        restartButton.setForeground(Color.BLACK);
        restartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        restartButton.setPreferredSize(new Dimension(200, 50));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartLevel();
}
});

        // Create "Quit" button
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
                System.out.println("Back to menu");
}
});

        // Panel to hold the buttons
        JPanel buttonBox = new JPanel();
        buttonBox.setBackground(Color.BLACK);
        buttonBox.setLayout(new GridLayout(2, 1, 20, 20));
        buttonBox.add(restartButton);
        buttonBox.add(quitButton);

        // Add components to the main frame
        add(logoBox, BorderLayout.NORTH);
        add(youLoseBox, BorderLayout.CENTER);
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

    public void restartLevel(){
        mainApp.startGame(levelNum); // Call the method to restart the game
        System.out.println("Level restarted");
}

    public void updateLevelNumber(int levelNum) {
        this.levelNum = levelNum;
}
}
