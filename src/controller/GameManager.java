package controller;

import view.GamePanel;

import java.awt.*;

public class GameManager implements Runnable {
    private static GameManager instance;
    private final GamePanel gamePanel;
    private final int FPS = 120;
    private final int UPS = 200;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager() {
        this.gamePanel = new GamePanel(this);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                this.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                this.gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
