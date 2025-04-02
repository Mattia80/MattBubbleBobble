package gamestates;

public enum GameState {
    MENU, PROFILE, GAMEPLAY, CONTINUE, LEADERBOARD, GAMEOVER, VICTORY;

    public static GameState state = GAMEPLAY;
}
