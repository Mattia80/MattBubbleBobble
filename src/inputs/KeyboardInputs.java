package inputs;

import controller.GameManager;
import gamestates.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private final GameManager gameManager;

    public KeyboardInputs(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case GAMEPLAY -> this.gameManager.getGamePlayState().KeyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case GAMEPLAY -> this.gameManager.getGamePlayState().KeyReleased(e);
        }
    }
}
