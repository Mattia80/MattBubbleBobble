package model;

import utility.GameConstants;

import static utility.GameConstants.Enemy.*;
import static utility.GameConstants.Item.*;

public class Benzo extends AbstractEnemy {
    public Benzo(float x, float y, String id) {
        super(x, y, GameConstants.Enemy.WIDTH, GameConstants.Enemy.HEIGHT, BENZO, id);
        this.speed = SPEED;
        this.points = 500;
        this.itemToDrop = BANANA;
    }
}
