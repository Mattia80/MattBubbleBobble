package view;

import controller.LevelController;
import model.Game;
import model.Level;
import utility.GameConstants;
import utility.HelpMethods;
import utility.LoadSave;
import utility.ProfileUtility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LevelView extends AbstractView {

    private boolean[][] levelTiles;
    private BufferedImage player;
    private BufferedImage levelImg;

    public LevelView(Level level, LevelController levelController) {
        super(level, levelController);
        this.levelTiles = new boolean[GameConstants.Game.GAME_HEIGHT * 2][GameConstants.Game.GAME_WIDTH];
        this.loadImg();
    }

    private void loadImg() {
        this.levelImg = LoadSave.loadSprite(LoadSave.LEVEL_TILES);
        this.player = LoadSave.loadSprite(LoadSave.PLAYER);
    }

    public Level getLevel() {
        return (Level) this.model;
    }

    public boolean[][] getLevelTiles() {
        return this.levelTiles;
    }

    @Override
    public void render(Graphics g) {
        HelpMethods.writeText(g, "Score: " + String.valueOf(Game.getInstance().getPoints()),
                GameConstants.Game.GAME_WIDTH / 2, 50, true);

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
                        char c = line.charAt(i);
                        if (c == 'X' || c == 'L' || c == 'R' || c == 'P') {
                            int x = i * GameConstants.Tiles.TILES_SIZE;
                            int y = lineNumber * GameConstants.Tiles.TILES_SIZE;
                            g.drawImage(this.levelImg.getSubimage(29, 16 * (Game.getInstance().getLevel() - 1), 16, 16),
                                    x, y, GameConstants.Tiles.TILES_SIZE, GameConstants.Tiles.TILES_SIZE, null);
                            if (c == 'L') {
                                String level = "LVL " + String.valueOf(Game.getInstance().getLevel());
                                Graphics2D g2 = (Graphics2D) g;
                                Font arcadeFont = ProfileUtility.getArcadeFont(18f);
                                g2.setFont(arcadeFont);
                                FontMetrics fm = g2.getFontMetrics();
                                int textWidth = fm.stringWidth(level);
                                int textHeight = fm.getHeight();
                                int ascent = fm.getAscent();

                                g2.setColor(new Color(0, 0, 0, 150));
                                g2.fillRect(x, y - ascent, textWidth, textHeight);

                                g2.setColor(Color.WHITE);
                                g2.drawString(level, x, y);
                            }

                            this.levelTiles[lineNumber][i] = true;
                        }

                    }
                    lineNumber++;
                    line = in.readLine();
                }

                drawLives(g);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void drawLives(Graphics g) {
        int xStart = 60;
        int yStart = 840;
        int size = GameConstants.Tiles.TILES_SIZE / 2;
        int lives = Game.getInstance().getLives();

        for (int i = 0; i < lives; i++) {
            g.drawImage(LoadSave.loadSprite(LoadSave.LIFE), xStart + i * GameConstants.Tiles.TILES_SIZE, yStart, size,
                    size, null);
        }
    }
}
