package controller;

import gamestates.StateGamePlay;
import model.AbstractModel;
import view.AbstractView;

import java.awt.*;

public abstract class AbstractController implements ControllerInterface {
    protected AbstractModel model;
    protected AbstractView view;
    protected StateGamePlay playing;

    public AbstractController() {
        this(null, null);
    }

    public AbstractController(AbstractModel model) {
        this.model = model;
    }

    public AbstractController(AbstractView view) {
        this.view = view;
    }

    public AbstractController(AbstractModel model, AbstractView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public AbstractModel getModel() {
        return model;
    }

    @Override
    public void setModel(AbstractModel model) {
        this.model = model;
    }

    @Override
    public AbstractView getView() {
        return view;
    }

    @Override
    public void setView(AbstractView view) {
        this.view = view;
    }

    public StateGamePlay getPlaying() {
        return playing;
    }

    public void setPlaying(StateGamePlay playing) {
        this.playing = playing;
    }

    @Override
    public void renderView(Graphics g) {
        if (this.view != null) {
            this.view.render(g);
        }
    }
}
