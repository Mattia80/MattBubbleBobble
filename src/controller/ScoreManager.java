package controller;

import model.Profile;

public class ScoreManager {
    private Profile profile;

    public ScoreManager(Profile profile) {
        this.profile = profile;
    }

    public void updateScore(int currentScore) {
        if (currentScore > profile.getMaxScore()) {
            profile.setMaxScore(currentScore);
        }
    }

    public void updateLevel(int currentLevel) {
        if (currentLevel > profile.getMaxLevel()) {
            profile.setMaxLevel(currentLevel);
        }
    }
}
