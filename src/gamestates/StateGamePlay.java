package gamestates;

import controller.*;
import model.*;
import utility.GameConstants;
import utility.HelpMethods;
import view.EnemyView;
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
import java.util.Map;
import java.util.Random;

import static utility.GameConstants.Enemy.*;

public class StateGamePlay extends AbstractGameState implements StateInterface {

    private PlayerController playerController;
    private ArrayList<BubbleController> bubbleControllers;
    private LevelController levelController;
    private ArrayList<EnemyController> enemyControllers;


    public StateGamePlay(GameManager gameManager) {
        super(gameManager);
        this.bubbleControllers = new ArrayList<BubbleController>();
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
        this.initEnemies();
    }

    private void initEnemies() {
        this.enemyControllers = new ArrayList<>();
        String path = "/levels/level_" + Game.getInstance().getLevel() + ".txt";
        try (InputStream is = LevelView.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException(path);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line = br.readLine();
                int lineNumber = 0;
                while (line != null) {
                    for (int i = 0; i < line.length(); i++) {
                        if (!HelpMethods.isNumeric(line.substring(i, i + 1))) {
                            continue;
                        }
                        int enemyType = Integer.parseInt(line.substring(i, i + 1));
                        this.createEnemy(enemyType, i, lineNumber);
                    }
                    lineNumber++;
                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createEnemy(int enemyType, int row, int column) {
        AbstractEnemy model = null;
        int x = row * GameConstants.Tiles.TILES_SIZE;
        int y = column * GameConstants.Tiles.TILES_SIZE;

        Random rn = new Random();
        String id = "enemy" + rn.nextInt();

        switch (enemyType) {
            case BENZO -> model = new Benzo(x, y, id);
            case BLUBBA -> model = new Blubba(x, y, id);
            case BONNIEBO -> model = new BonnieBo(x, y, id);
        }

        EnemyController enemyController = new EnemyController();
        EnemyView enemyView = new EnemyView(model, enemyController);
        enemyController.setModel(model);
        enemyController.setView(enemyView);
        enemyController.setPlaying(this);
        this.enemyControllers.add(enemyController);
        this.levelController.updateNumberOfEnemiesByOne();
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
        this.levelController.update();
        this.playerController.update();
        for (BubbleController bubbleController : this.bubbleControllers) {
            bubbleController.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        this.levelController.renderView(g);
        this.playerController.renderView(g);
        for (BubbleController bubbleController : this.bubbleControllers) {
            bubbleController.renderView(g);
        }
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
