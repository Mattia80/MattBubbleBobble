package utility;

import java.util.Map;

public class GameConstants {
    /**
     * Classe Statica che contiene la varie costanti che riguardano il gioco.
     */
    public static class Game {
        /**
         * Rappresenta la larghezza della finestra di gioco.
         */
        public static final int GAME_WIDTH = Tiles.TILES_IN_WIDTH * Tiles.TILES_SIZE;
        /**
         * Rappresenta l'altezza della finestra di gioco
         */
        public static final int GAME_HEIGHT = Tiles.TILES_IN_HEIGHT * Tiles.TILES_SIZE;
        /**
         * Rappresenta la gravita' presente nel gioco
         */
        public static final float GRAVITY = 0.04f * Tiles.SCALE;

    }

    /**
     * Classe statica che raggruppa le costanti relative ai Tiles del gioco. Tutte
     * le costanti sono espresse in pixel
     */
    public static class Tiles {
        /**
         * Dimensione di default del Tile
         */
        public static final int TILES_DEFAULT_SIZE = 32;
        /**
         * Costante che rappresenta la scala con cui vengono gestiti i Tiles e permette
         * di adattare le dimensioni dei Tiles
         */
        public static final float SCALE = 1.5f;
        /**
         * Numero massimo di Tiles in larghezza
         */
        public static final int TILES_IN_WIDTH = 26;
        /**
         * Numero massimo di Tiles in altezza
         */
        public static final int TILES_IN_HEIGHT = 18;
        /**
         * Dimesione dei Tiles, e viene stabilita moltiplicando la dimensione di default
         * con la scala
         */
        public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    }

    /**
     * Classe statica che rappresenta le costanti associate alle possibili direzioni
     */
    public static class Directions {
        /**
         * Costante per la direzione sinistra
         */
        public static final int LEFT = 0;
        /**
         * Costante per la direzione destra
         */
        public static final int RIGHT = 1;
        /**
         * Costante per la direzione su
         */
        public static final int UP = 2;
        /**
         * Costante per la direzione giu'
         */
        public static final int DOWN = 3;
    }

    /**
     * Classe statica che raggruppa le costanti relative alle bolle
     */
    public static class Bubble {
        /**
         * Rappresenta la velocita' di movimento della bolla
         */
        public static final float SPEED = 1.5f;
        /**
         * Rappresenta la velocita' con cui volano le bolle
         */
        public static final float FLYING_SPEED = 0.4f;
    }

    /**
     * Classe statica che racchiude tutti i parametri costanti del giocatore.
     * Contiene anche il metodo statico getNumberOfSprites() che restituisce il
     * numero di sprite in base ad ogni azione
     */
    public static class Player {
        /**
         * Indica l'azione neutra
         */
        public static final int IDLE = 0;
        /**
         * Indica l'azione di movimento
         */
        public static final int RUN = 1;
        /**
         * Indica l'azione di salto
         */
        public static final int JUMP = 2;
        /**
         * Indica l'azione di caduta
         */
        public static final int FALL = 3;
        /**
         * Indica l'azione di atterraggio
         */
        public static final int FLOOR = 4;
        /**
         * Indica l'azione di attacco
         */
        public static final int ATTACK = 5;
        /**
         * Indica l'azione di morte
         */
        public static final int HIT = 6;
        /**
         * Indica la velocita' di movimento del giocatore
         */
        public static final float SPEED = 0.9f;
        /**
         * Indica la velocita' di salto del giocatore
         */
        public static final float JUMP_SPEED = -3.2f * Tiles.SCALE;
        /**
         * Indica la velocita' di caduta del giocatore
         */
        public static final float FALL_SPEED = 0.1f * Tiles.SCALE;

        /**
         * Metodo statico che restituisce il numero di sprite in base ad ogni azione
         *
         * @param action azione richiesta da cui ottenere il numero di sprites da cui e'
         *               composta la specifica animazione
         * @return il numero di sprites da cui e' composta la specifica animazione
         */
        public static int getNumberOfSprites(int action) {
            return switch (action) {
                case IDLE, RUN, JUMP -> 2;
                case HIT -> 6;
                case FALL, FLOOR, ATTACK -> 1;
                default -> 0;
            };
        }
    }

    /**
     * Classe statica che raggruppa le costanti relative alla user interface
     */
    public static class UserInterface {

        /**
         * Larghezza di default del pulsante
         */
        public final static int BUTTON_DEFAULT_WIDTH = 200;
        /**
         * Altezza di default del pulsante
         */
        public final static int BUTTON_DEFAULT_HEIGHT = 30;
        /**
         * Larghezza del pulsante
         */
        public final static int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH * Tiles.SCALE);
        /**
         * Altezza del pulsante
         */
        public final static int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Tiles.SCALE);
        /**
         * Offset orizzontale per centrare il testo
         */
        public final static int X_OFFSET_CENTER = BUTTON_WIDTH / 2;
        /**
         * Dimensione del font utilizzato nel menu
         */
        public final static int DEFAULT_FONT_SIZE = 24;

    }

    /**
     * Classe statica che racchiude le costanti per i nemici
     */
    public static class Enemy {
        /**
         * Identificativo del nemico "Benzo"
         */
        public static final int BENZO = 0;
        /**
         * Identificativo del nemico "Bonnie-Bo"
         */
        public static final int BONNIEBO = 5;
        /**
         * Identificativo del nemico "Blubba"
         */
        public static final int BLUBBA = 3;

        /**
         * Larghezza del nemico
         */
        public static final int WIDTH = (int) (32 * Tiles.SCALE);
        /**
         * Altezza del nemico
         */
        public static final int HEIGHT = (int) (32 * Tiles.SCALE);
        /**
         * Velocita' del nemico
         */
        public static final float SPEED = 0.4f;
        /**
         * Velocita' del nemico quando e' intrappolato
         */
        public static final float BOXED_SPEED = 0.3f;
        /**
         * Velocita' del nemico durante il salto
         */
        public static final float JUMP_SPEED = -2.99f * Tiles.SCALE;
        /**
         * Velocita' del nemico in caduta
         */
        public static final float FALL_SPEED = 0.5f * Tiles.SCALE;

        /**
         * Identificativo dell'azione quando il nemico sta correndo
         */
        public static final int RUNNING = 0;
        /**
         * Identificativo dell'azione quando il nemico e' arrabbiato
         */
        public static final int ANGRY = 1;
        /**
         * Identificativo dell'azione quando il nemico e' blu
         */
        public static final int BLUE = 2;
        /**
         * Identificativo dell'azione quando il nemico e' intrappolato nella bolla
         */
        public static final int BOXED_GREEN = 3;
        /**
         * Identificativo dell'azione quando il nemico e' intrappolato nella bolla ma e'
         * blu
         */
        public static final int BOXED_BLUE = 4;
        /**
         * Identificativo dell'azione quando il nemico e' intrappolato nella bolla ma e'
         * rosso
         */
        public static final int BOXED_RED = 5;
        /**
         * Identificativo dell'azione quando il nemico e' stato ucciso
         */
        public static final int DEAD = 6;

        /**
         * Metodo statico che restituisce il numero di sprite in base allo stato del
         * nemico
         *
         * @param enemyState lo stato attuale del nemico
         * @return il numero di sprite corrispondenti allo stato
         */
        public static int getSprites(int enemyState) {
            return enemyState == DEAD ? 4 : 2;
        }

        /**
         * Metodo statico che restituisce lo stato corrente del nemico
         *
         * @return lo stato corrente del nemico
         */
        public static int[] getEnemyStates() {
            return new int[] { RUNNING, ANGRY, BLUE, BOXED_GREEN, BOXED_RED, DEAD };
        }
    }

    /**
     * Classe statica che racchiude le costanti relativi agli item che vengono
     * generati durante il gioco
     */
    public static class Item {
        /**
         * Larghezza dell'item
         */
        public static final int WIDTH = (int) (32 * Tiles.SCALE);
        /**
         * Altezza dell'item
         */
        public static final int HEIGHT = (int) (32 * Tiles.SCALE);

        /**
         * Costanti che identificano i vari item e power up nel gioco
         */
        public final static int BANANA = 0;
        public final static int PEACH = 1;
        public final static int PINEAPPLE = 2;
        public final static int CANDY_PINK = 3;
        public final static int CANDY_BLUE = 4;
        public final static int CANDY_YELLOW = 5;
        public final static int RING_CYAN = 6;
        public final static int RING_PINK = 7;
        public final static int RING_RED = 8;
        public final static int CROSS_RED = 9;
        public final static int CLOCK = 10;
        public final static int BOMB = 11;
        public final static int SHOE = 12;

        /**
         * Soglia di generazione per un potenziamento
         */
        public final static int GENERATE_POWER_UP_THRESHOLD = 60;

        /**
         * Mappa che assegna ad ogni item la rispettiva probabilita' di generazione
         * durante il gioco
         */
        public final static Map<Integer, Integer> powerUpChances = Map.of(CANDY_PINK, 19, CANDY_BLUE, 17, CANDY_YELLOW,
                15, SHOE, 13, RING_CYAN, 11, RING_PINK, 9, RING_RED, 7, CROSS_RED, 5, CLOCK, 3, BOMB, 1);
    }

}
