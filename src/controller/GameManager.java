package controller;

import gamestates.StateGamePlay;
import view.GamePanel;
import view.GameWindow;

import java.awt.*;

public class GameManager implements Runnable {
    private static GameManager instance;
    private final GamePanel gamePanel;
    private GameWindow gameWindow;
    private final int FPS = 120;
    private final int UPS = 200;
    private StateGamePlay gamePlayState;


    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager() {
        this.gamePanel = new GamePanel(this);
        this.initClasses();
        this.gameWindow = new GameWindow(gamePanel);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocus();
    }

    private void initClasses() {
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void startGame() {
        this.startGameLoop();
    }

    private void startGameLoop() {
        Thread thread = new Thread(this);
        thread.start();
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
