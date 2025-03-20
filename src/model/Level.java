package model;

public class Level extends AbstractModel{
    private int enemies;
    private int enemiesKilled;
    private long clearedAt;
    private boolean cleared;

    public int getEnemies() {
        return enemies;
    }

    public void setEnemies(int enemies) {
        this.enemies = enemies;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public long getClearedAt() {
        return clearedAt;
    }

    public void setClearedAt(long clearedAt) {
        this.clearedAt = clearedAt;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}
