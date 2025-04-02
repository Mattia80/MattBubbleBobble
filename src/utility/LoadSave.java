package utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    /**
     * Nome del file che contiene gli sprites del giocatore
     */
    public static final String PLAYER = "player.png";
    /**
     * Nome del file che contiene l'immagine della singola vita
     */
    public static final String LIFE = "life.png";
    /**
     * Nome del file che contiene gli sprites delle bolle
     */
    public static final String BUBBLE = "bubble.png";
    /**
     * Nome del file che contiene gli sprites dei vari nemici del gioco
     */
    public static final String ENEMIES = "enemies.png";
    /**
     * Nome del file che contiene gli sprites delle tiles dei livelli
     */
    public static final String LEVEL_TILES = "level_tiles.png";
    /**
     * Nome del file che contiene gli sprites degli items e power up
     */
    public static final String ITEMS = "items.png";
    /**
     * Nome del file che contiene gli sprites per la scritta del giocatore 1
     */
    public static final String MENU_ITEM_1P = "menu_item_1p.png";
    /**
     * Nome del file che contiene gli sprites per la scritta del giocatore 1 -
     * Continua
     */
    public static final String MENU_ITEM_1P_CONTINUE = "menu_item_1p.png";
    /**
     * Nome del file che contiene gli sprites per la classifica dei punteggi
     */
    public static final String MENU_ITEM_RECORDS = "menu_item_records.png";
    /**
     * Nome del file che contiene gli sprites del titolo
     */
    public static final String MENU_TITLE = "title.png";
    /**
     * Nome del file che contiene gli sprites della schermata di Game Over
     */
    public static final String GAME_OVER = "gameover.png";
    /**
     * Nome del file che contiene l'immagine della schermata di vittoria
     */
    public static final String VICTORY = "victory.png";
    /**
     * Nome del file che contiene l'immagine iniziale del gioco
     */
    public static final String JBUBBLEBOBBLE = "bubblebobblestart.png";
    /**
     * Nome del file che contiene l'immagine della schermata di pausa del gioco
     */
    public static final String CONTINUE = "pause.png";

    /**
     * Metodo statico che carica gli sprites del nome file passato in input. Il nome
     * del file viene aggiunto alla directory predefinita che contiene le immagini
     *
     * @param filename file che identifica le immagini da caricare
     * @return le immagini presenti nel file passato in input
     */
    public static BufferedImage loadSprite(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("sprites/" + filename);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return img;
    }

}
