package view;

import controller.GameManager;
import utility.GameConstants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final GameManager gameManager;

    public GamePanel(GameManager gameManager) {
        this.gameManager = gameManager;
        this.setSize();
        this.setBackground(Color.BLACK);
    }

    private void setSize() {
        Dimension dimension = new Dimension(GameConstants.Game.GAME_WIDTH, GameConstants.Game.GAME_HEIGHT);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gameManager.render(g);
    }
}
