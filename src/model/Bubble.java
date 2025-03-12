package model;

import utility.GameConstants;

public class Bubble extends AbstractBubble{
    /**
     * Campo che identifica se la bolla sta fluttuando
     */
    private boolean flying;
    /**
     * Coordinata x in cui la bolla e' stata sparata
     */
    private float shotX;
    /**
     * Coordinata y in cui la bolla e' stata sparata
     */
    private float shotY;

    /**
     * Costruttore della classe Bolla
     *
     * @param x      posizione x della bolla
     * @param y      posizione y della bolla
     * @param width  larghezza della bolla
     * @param height altezza della bolla
     */
    public Bubble(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.shotX = x;
        this.shotY = y;
        this.speed = GameConstants.Bubble.SPEED;
        initHitbox(29, 29);
    }

    /**
     * Restituisce true se la bolla sta volando, false altrimenti
     *
     * @return true se la bolla sta volando, false altrimenti
     */
    public boolean isFlying() {
        return flying;
    }

    /**
     * Imposta a true se la bolla sta volando, false altrimenti
     *
     * @param flying true se la bolla sta volando, false altrimenti
     */
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    /**
     * Restituisce la posizione x in cui e' stata sparata la bolla
     *
     * @return la posizione x in cui e' stata sparata la bolla
     */
    public float getShotX() {
        return shotX;
    }

    /**
     * Imposta la posizione x in cui e' stata sparata la bolla
     *
     * @param shotX la posizione x in cui e' stata sparata la bolla
     */
    public void setShotX(float shotX) {
        this.shotX = shotX;
    }

    /**
     * Restituisce la posizione y in cui e' stata sparata la bolla
     *
     * @return la posizione y in cui e' stata sparata la bolla
     */
    public float getShotY() {
        return shotY;
    }

    /**
     * Imposta la posizione y in cui e' stata sparata la bolla
     *
     * @param shotY la posizione y in cui e' stata sparata la bolla
     */
    public void setShotY(float shotY) {
        this.shotY = shotY;
    }

    /**
     * Override del metodo CanDamage() della classe AbstractBubble restituisce true
     * se una bolla puo' arrecare danno, ovvero se non sta volando, se non e'
     * esplosa e se e' ancora valida, false altrimenti
     *
     * @return rue se una bolla puo' arrecare danno, false altrimenti
     */
    @Override
    public boolean canDamage() {
        return (super.canDamage() && !isFlying());
    }

}
