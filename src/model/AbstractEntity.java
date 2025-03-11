package model;

import java.awt.geom.Rectangle2D;

public abstract class AbstractEntity extends AbstractModel {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected  boolean moving;
    protected boolean jumping;
    protected boolean inAir;
    protected float Airspeed;
    protected float speed;
    protected Rectangle2D.Float hitbox;

    public AbstractEntity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public float getAirspeed() {
        return Airspeed;
    }

    public void setAirspeed(float airspeed) {
        Airspeed = airspeed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D.Float hitbox) {
        this.hitbox = hitbox;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(this.x, this.y, width, height);
    }
}
