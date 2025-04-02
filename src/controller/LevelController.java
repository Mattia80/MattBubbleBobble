package controller;

import model.Game;
import model.Level;
import model.Profile;
import utility.LeaderboardUtility;
import view.LevelView;

public class LevelController extends AbstractController {

    private ScoreManager scoreManager;

    public Level getLevel() {
        return (Level) this.model;
    }

    public LevelView getLevelView() {
        return (LevelView) this.view;
    }

    public boolean[][] getLevelTiles() {
        return this.getLevelView().getLevelTiles();
    }

    public void updateNumberOfEnemiesByOne() {
        Level level = this.getLevel();
        level.setEnemies(level.getEnemies() + 1);
    }

    public void updateNumberOfEnemiesKilledByOne() {
        Level level = this.getLevel();
        level.setEnemies(level.getEnemiesKilled() +1);
        if (level.getEnemiesKilled() == level.getEnemies()) {
            level.setClearedAt(System.nanoTime());
            level.setCleared(true);
        }
    }

    private void saveRecord(Game game) {
//        GameManager gameManager = GameManager.getInstance();
//        Profile currentProfile = gameManager.getProfile().getProfile();
//        this.scoreManager = new ScoreManager(currentProfile);
//        scoreManager.updateScore((int) game.getPoints());
//        scoreManager.updateLevel(game.getLevel());
//        LeaderboardUtility.saveProfileRecord(currentProfile);
    }

    @Override
    public void update() {

    }
}
