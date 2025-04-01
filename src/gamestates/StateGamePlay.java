package gamestates;

import controller.BubbleController;
import controller.GameManager;
import controller.LevelController;
import controller.PlayerController;
import model.Game;
import model.Level;
import model.Player;
import utility.GameConstants;
import view.LevelView;
import view.PlayerView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StateGamePlay extends AbstractGameState implements StateInterface {

    private PlayerController playerController;
    private ArrayList<BubbleController> bubbleControllers;
    private LevelController levelController;


    public StateGamePlay(GameManager gameManager) {
        super(gameManager);
        this.bubbleControllers = new ArrayList<>();
        this.init();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public ArrayList<BubbleController> getBubbleControllers() {
        return bubbleControllers;
    }

    private void init() {
        this.initGame();
        this.initPlayer();
        this.initLevel();
    }

    private void initGame() {
        Game game = Game.newInstance();
    }

    private void initLevel() {
        this.levelController = new LevelController();
        Level level = new Level();
        LevelView levelView = new LevelView(level, levelController);
        this.levelController.setModel(level);
        this.levelController.setView(levelView);
        this.levelController.setPlaying(this);
    }

    private void initPlayer() {

        ArrayList<Integer> previousItems = new ArrayList<Integer>();
        if (this.playerController != null) {
            previousItems.addAll(this.playerController.getPlayer().getItemsCollected());
        }

        String path = "/levels/level_" + Game.getInstance().getLevel() + ".txt";

        try (InputStream is = LevelView.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException(path);
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line = in.readLine();
                int lineNumber = 0;
                while (line != null) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == 'S') {
                            this.playerController = new PlayerController();
                            Player player = new Player(i * GameConstants.Tiles.TILES_SIZE,
                                    lineNumber * GameConstants.Tiles.TILES_SIZE, (int) (32 * GameConstants.Tiles.SCALE),
                                    (int) (32 * GameConstants.Tiles.SCALE));
                            PlayerView playerView = new PlayerView(player, this.playerController);
                            this.playerController.setModel(player);
                            this.playerController.setView(playerView);
                            this.playerController.setPlaying(this);

                            for (Integer item : previousItems) {
                                player.addItem(item);
                            }

                            break;
                        }
                    }
                    lineNumber++;
                    line = in.readLine();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void respawnPlayer() {
        this.bubbleControllers.clear();
        this.initPlayer();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void MousePressed(MouseEvent e) {

    }

    @Override
    public void MouseReleased(MouseEvent e) {

    }

    @Override
    public void MouseMoved(MouseEvent e) {

    }

    @Override
    public void KeyPressed(KeyEvent e) {

    }

    @Override
    public void KeyReleased(KeyEvent e) {

    }
}
