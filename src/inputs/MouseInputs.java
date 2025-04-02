package inputs;

import controller.GameManager;
import gamestates.GameState;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseInputListener, MouseMotionListener {

    private GameManager gameManager;

    public MouseInputs(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case GAMEPLAY -> this.gameManager.getGamePlayState().MousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
