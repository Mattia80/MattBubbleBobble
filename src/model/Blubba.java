package model;

import utility.GameConstants;

import static utility.GameConstants.Enemy.*;
import static utility.GameConstants.Item.*;

public class Blubba extends AbstractEnemy {

    private int horizontalDirection = 1;
    private int verticalDirection = -1;

    public Blubba(float x, float y, String id) {
        super(x, y, GameConstants.Enemy.WIDTH, GameConstants.Enemy.HEIGHT, BLUBBA, id);
        this.speed = SPEED;
        this.points = 2000;
        this.itemToDrop = PEACH;
    }

    public int getHorizontalDirection() {
        return horizontalDirection;
    }

    public void setHorizontalDirection(int horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }

    public int getVerticalDirection() {
        return verticalDirection;
    }

    public void setVerticalDirection(int verticalDirection) {
        this.verticalDirection = verticalDirection;
    }
}
