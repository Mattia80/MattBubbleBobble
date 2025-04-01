package view;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("JBubbleBobble - v.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
