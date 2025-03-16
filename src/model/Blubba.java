package model;
import utility.GameConstants;

import static utility.GameConstants.Enemy.*;
import static  utility.GameConstants.Item.*;

public class Blubba extends AbstractEnemy implements Fly {
    public Blubba(float x, float y, String id) {
        super(x,y, GameConstants.Enemy.WIDTH, GameConstants.Enemy.HEIGHT, BLUBBA, id);
        this.speed = SPEED;
        this.points = 2000;
        this.itemToDrop = PEACH;
    }
}
