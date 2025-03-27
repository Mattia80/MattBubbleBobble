package gamestates;

import controller.GameManager;

public abstract class AbstractGameState implements StateInterface {
    protected GameManager gameManager;

    public AbstractGameState(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
