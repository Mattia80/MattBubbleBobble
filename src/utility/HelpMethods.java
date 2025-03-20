package utility;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utility.GameConstants.UserInterface.DEFAULT_FONT_SIZE;

public class HelpMethods {

    private static boolean isSolid(float x, float y, boolean[][] levelTiles, boolean isFalling) {
        if (x < 0 || x > GameConstants.Game.GAME_WIDTH || y < 0 || y > GameConstants.Game.GAME_HEIGHT) {
            return false;
        }
        int xIndex = (int) x / GameConstants.Tiles.TILES_SIZE;
        int yIndex = (int) y / GameConstants.Tiles.TILES_SIZE;

        if (!isFalling && levelTiles[yIndex][xIndex]) {
            return false;
        }
        return levelTiles[yIndex][xIndex];
    }

    public static boolean canMoveHere(float x, float y, int width, int height, boolean[][] levelTiles,
                                      boolean isFalling) {

        // Se il giocatore è in aria, permetti sempre il movimento verticale
        if (y < 0)
            return true;

        if (!isSolid(x, y, levelTiles, isFalling) && !isSolid(x + width, y, levelTiles, isFalling)
                && !isSolid(x, y + height, levelTiles, isFalling)
                && !isSolid(x + width, y + height, levelTiles, isFalling)) {
            return true;
        }
        return false;

    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, boolean[][] levelTiles) {

        int xIndexLeft = (int) (hitbox.x / GameConstants.Tiles.TILES_SIZE);
        int xIndexRight = (int) ((hitbox.x + hitbox.width) / GameConstants.Tiles.TILES_SIZE);
        int yIndex = (int) ((hitbox.y + hitbox.height + 1) / GameConstants.Tiles.TILES_SIZE);

        // Se il giocatore è fuori dai limiti della matrice, considerarlo in aria
        if (yIndex >= levelTiles.length)
            return false;

        // 🔹 Controllo aggiuntivo: il giocatore è in bilico tra due colonne?
        boolean leftTile = levelTiles[yIndex][xIndexLeft];
        boolean rightTile = levelTiles[yIndex][xIndexRight];

        // 🔹 Se il giocatore sta lasciando la piattaforma, attivare la caduta
        return leftTile || rightTile;
    }

    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / GameConstants.Tiles.TILES_SIZE);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * GameConstants.Tiles.TILES_SIZE;
            int xOffset = (int) (GameConstants.Tiles.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            // Left
            return currentTile * GameConstants.Tiles.TILES_SIZE;
    }

    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int tileSize = GameConstants.Tiles.TILES_SIZE;
        int currentTile = (int) (hitbox.y / tileSize);

        if (airSpeed > 0) {
            // Se il giocatore sta cadendo, posizioniamolo sopra il pavimento
            return (currentTile + 1) * tileSize - hitbox.height - 1;
        } else {
            // Se sta salendo, fermiamolo sotto il soffitto
            return currentTile * tileSize;
        }
    }

    public static void writeText(Graphics g, String text, int x, int y, boolean centered) {
        writeText(g, text, x, y, DEFAULT_FONT_SIZE, centered);
    }

    public static void writeText(Graphics g, String text, int x, int y, int fontSize, boolean centered) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        Font arcadeFont = ProfileUtility.getArcadeFont(18f);
        g2.setFont(arcadeFont);
        if (centered) {
            FontMetrics fm = g2.getFontMetrics(arcadeFont);
            int textWidth = fm.stringWidth(text);
            x -= textWidth / 2;
        }
        g2.drawString(text, x, y);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean areEntitiesTouching(Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2) {
        if (hitbox1.intersects(hitbox2))
            return true;
        if (hitbox2.intersects(hitbox1))
            return true;

        return false;
    }

}
