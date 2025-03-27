package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateInterface {

    void update();

    void draw(Graphics g);

    void MousePressed(MouseEvent e);

    void MouseReleased(MouseEvent e);

    void MouseMoved(MouseEvent e);

    void KeyPressed(KeyEvent e);

    void KeyReleased(KeyEvent e);

}
