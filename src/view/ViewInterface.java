package view;

import controller.AbstractController;
import model.AbstractModel;

import java.awt.*;

public interface ViewInterface {
    AbstractModel getModel();

    void setModel(AbstractModel model);

    AbstractController getController();

    void setController(AbstractController controller);

    void render(Graphics g);
}
