package model;

import utility.GameConstants;

import static utility.GameConstants.Enemy.*;
import static utility.GameConstants.Item.*;

public class BonnieBo extends AbstractEnemy {
    public BonnieBo(float x, float y, String id) {
        super(x,y, GameConstants.Enemy.WIDTH, GameConstants.Enemy.HEIGHT, BONNIEBO, id);
        this.speed = SPEED;
        this.points = 3000;
        this.itemToDrop = PINEAPPLE;
    }
}
