package model;

import static utility.GameConstants.Item.*;

public class Item extends AbstractEntity {
    protected int points;
    protected int type;
    protected boolean collected;
    protected long spawnedAt;
    protected boolean active = true;


    public Item(float x, float y, int type) {
        super(x, y, WIDTH, HEIGHT);
        this.type = type;
        this.spawnedAt = System.nanoTime();

        switch (type) {
            case BANANA -> this.points = 500;
            case PEACH -> this.points = 1000;
            case PINEAPPLE -> this.points = 2000;
            case CANDY_PINK, CANDY_BLUE, CANDY_YELLOW -> this.points = 100;
            case RING_CYAN, RING_PINK, RING_RED -> this.points = 1500;
            case CROSS_RED -> this.points = 2500;
            case CLOCK, BOMB -> this.points = 3000;
            case SHOE -> this.points = 150;
        }

        initHitbox(29, 30);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public long getSpawnedAt() {
        return spawnedAt;
    }

    public void setSpawnedAt(long spawnedAt) {
        this.spawnedAt = spawnedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
