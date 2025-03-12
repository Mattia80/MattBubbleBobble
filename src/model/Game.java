package model;

public class Game {
    /**
     * Identifica il livello attuale del gioco
     */
    private int level;
    /**
     * Rappresenta il punteggio raggiunto dal giocatore
     */
    private long points;
    /**
     * Rappresenta i punti necessari per ottenere una nuova vita
     */
    private long pointsToNextLife;
    /**
     * Indica il numero di vite rimaste per il giocatore
     */
    private int lives;
    /**
     * Rappresenta il profilo del giocatore
     */
    private Profile profile;

    /**
     * Campo statico che identifica l'istanza della classe. Implementato per l'uso
     * del Singleton
     */
    private static Game instance = null;

    /**
     * Costruttore privato dell'oggetto Game. Implementato come costruttore privato
     * per l'uso del singleton
     */
    private Game() {
        this.level = 1;
        this.points = 0;
        this.pointsToNextLife = 0;
        this.lives = 3;
        this.profile = new Profile();
    }

    /**
     * Metodo statico che recupera il valore del campo statico instance. Se la
     * classe non e' mai stata istanziata (instance == null) crea una nuova istanza
     * della classe. Implementato per l'uso del singleton.
     *
     * @return istanza della classe
     */
    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * Metodo statico che crea una nuova istanza di Gioco
     *
     * @return una nuova istanza del gioco
     */
    public static Game newInstance() {
        instance = new Game();
        return instance;
    }

    /**
     * Restituisce il livello corrente
     *
     * @return il livello corrente
     */
    public int getLevel() {
        return level;
    }

    /**
     * Imposta il livello corrente
     *
     * @param level il nuovo livello
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Restituisce il punteggio corrente
     *
     * @return il punteggio corrente
     */
    public long getPoints() {
        return points;
    }

    /**
     * Imposta il nuovo punteggio
     *
     * @param points il nuovo punteggio
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * Restituisce il numero di vite del giocatore
     *
     * @return il numero di vite del giocatore
     */
    public int getLives() {
        return lives;
    }

    /**
     * Imposta il numero di vite del giocatore
     *
     * @param lives il numero di vite del giocatore
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Restituisce il profilo del giocatore
     *
     * @return il profilo del giocatore
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Imposta il profilo del giopcatore
     *
     * @param profile profilo giocatore da impostare
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Aggiorna il punteggio del giocatore. Se il giocatore raccoglie piu' di 100000
     * punti, ottiene una vita aggiuntiva
     *
     * @param points i punti da aggiungere al punteggio corrente
     */
    public void updatePoints(int points) {
        this.points += points;
        this.pointsToNextLife += points;

        if (this.pointsToNextLife > 100000) {
            this.lives++;
            this.pointsToNextLife = 0;
        }
    }

}
