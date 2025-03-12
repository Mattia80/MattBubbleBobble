package model;

public class AbstractBubble extends AbstractEntity{
    /**
     * Indica se la bolla e' stata sparata verso sinistra
     */
    protected boolean left;
    /**
     * Indica se la bolla e' stata sparata verso destra
     */
    protected boolean right;
    /**
     * Indica se la bolla e' scoppiata
     */
    protected boolean popped;
    /**
     * Indica se la bolla e' ancora valida e puo' essere usata per intrappolare i
     * nemici
     */
    protected boolean expired;

    /**
     * Costruttore della bolla generica
     * @param x posizione x della bolla
     * @param y posizione y della bolla
     * @param width larghezza della bolla
     * @param height altezza della bolla
     */
    public AbstractBubble(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPopped() {
        return popped;
    }

    public void setPopped(boolean popped) {
        this.popped = popped;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    /**
     * Restituisce true se la bolla puo' effettivamente infliggere danni, false
     * altrimenti
     *
     * @return true se la bolla puo' effettivamente infliggere danni, false
     */
    public boolean canDamage() {
        return !this.expired && !this.popped;
    }

}
