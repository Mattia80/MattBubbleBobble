package view;

import controller.AbstractController;
import model.AbstractModel;

public abstract class AbstractView implements ViewInterface {
    protected AbstractModel model;
    protected AbstractController controller;

    public AbstractView() {
        this(null, null);
    }

    public AbstractView(AbstractModel model) {
        this.model = model;
    }

    public AbstractView(AbstractController controller) {
        this.controller = controller;
    }

    public AbstractView(AbstractModel model, AbstractController controller) {
        this.model = model;
        this.controller = controller;
    }

    public AbstractModel getModel() {
        return model;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }
}
