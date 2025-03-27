package controller;

import model.AbstractModel;
import view.AbstractView;

import java.awt.*;

public interface ControllerInterface {

    AbstractModel getModel();

    void setModel(AbstractModel model);

    AbstractView getView();

    void setView(AbstractView view);

    void update();

    void renderView(Graphics g);

}
