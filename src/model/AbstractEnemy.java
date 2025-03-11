package model;

import java.util.Objects;

public abstract class AbstractEnemy extends AbstractEntity {

    /**
     * Indica se il nemico e' infuriato
     */
    protected boolean angry;
    /**
     * Indica se il nemico è intrappolato in una bolla
     */
    protected boolean bubbled;
    /**
     * Indica se il nemico e' morto
     */
    protected boolean dead;
    /**
     * Specifica il tipo di nemico
     */
    protected int type;
    /**
     * Rappresenta il punteggio che il giocatore riceve in caso di uccisione del
     * nemico
     */
    protected int points;
    /**
     * Indica quale oggetto droppa il nemico quando viene ucciso
     */
    protected int itemToDrop;
    /**
     * Indica la direzione in cui sta camminando il nemico
     */
    protected int walkDirection;
    /**
     * Rappresenta il tempo trascorso da quando il nemico è stato intrappolato
     */
    protected long bubbledAt;
    /**
     * Rappresenta un id univoco associato al nemico
     */
    protected String id;

    /**
     * Costruttore del nemico generico
     *
     * @param x      posizione x del nemico
     * @param y      posizione y del nemico
     * @param width  larghezza del nemico
     * @param height altezza del nemico
     * @param type   tipo del nemico
     */
    public AbstractEnemy(float x, float y, int width, int height, int type, String id) {
        super(x, y, width, height);
        this.type = type;
        this.id = id;
        initHitbox(32, 32);
    }

    /**
     * Restituisce true se il nemico e' infuriato, false altrimenti
     *
     * @return true se il nemico e' infuriato, false altrimenti
     */
    public boolean isAngry() {
        return angry;
    }

    /**
     * Imposta lo stato "infuriato" del nemico
     *
     * @param angry true se il nemico e' infuriato, false altrimenti
     */
    public void setAngry(boolean angry) {
        this.angry = angry;
    }

    /**
     * Restituisce true se il nemico e' intrappolato, false altrimenti
     *
     * @return true se il nemico e' intrappolato, false altrimenti
     */
    public boolean isBubbled() {
        return bubbled;
    }

    /**
     * Imposta lo stato "intrappolato" del nemico
     *
     * @param bubbled true se il nemico e' intrappolato, false altrimenti
     */
    public void setBubbled(boolean bubbled) {
        this.bubbled = bubbled;
    }

    /**
     * Restituisce true se il nemico e' stato ucciso, false altrimenti
     *
     * @return true se il nemico e' stato ucciso, false altrimenti
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Imposta lo stato "ucciso" del nemico
     *
     * @param dead true se il nemico e' stato ucciso, false altrimenti
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Restituisce il tipo di nemico
     *
     * @return il tipo di nemico
     */
    public int getType() {
        return type;
    }

    /**
     * Imposta il tipo di nemico
     *
     * @param type il tipo di nemico
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Restituisce il punteggio in caso di uccisione del nemico
     *
     * @return il punteggio in caso di uccisione del nemico
     */
    public int getPoints() {
        return points;
    }

    /**
     * Imposta il punteggio in caso di uccisione del nemico
     *
     * @param points il punteggio in caso di uccisione del nemico
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Restituisce l'oggetto che il nemico droppa quando viene ucciso
     *
     * @return oggetto che il nemico droppa quando viene ucciso
     */
    public int getItemToDrop() {
        return itemToDrop;
    }

    /**
     * Imposta l'oggetto che il nemico droppa quando viene ucciso
     *
     * @param itemToDrop l'oggetto che il nemico droppa quando viene ucciso
     */
    public void setItemToDrop(int itemToDrop) {
        this.itemToDrop = itemToDrop;
    }

    /**
     * Restituisce la direzione attuale del nemico
     *
     * @return la direzione attuale del nemico
     */
    public int getWalkDirection() {
        return walkDirection;
    }

    /**
     * Imposta la nuova direzione del nemico
     *
     * @param walkDirection la nuova direzione del nemico
     */
    public void setWalkDirection(int walkDirection) {
        this.walkDirection = walkDirection;
    }

    /**
     * Restituisce il tempo trascorso da quando il nemico e' stato 'imbollato'
     *
     * @return il tempo trascorso da quando il nemico e' stato 'imbollato'
     */
    public long getBubbledAt() {
        return bubbledAt;
    }

    /**
     * Imposta il tempo trascorso da quando il nemico e' stato 'imbollato'
     *
     * @param bubbledAt il tempo trascorso da quando il nemico e' stato 'imbollato'
     */
    public void setBubbledAt(long bubbledAt) {
        this.bubbledAt = bubbledAt;
    }

    /**
     * Restituisce l'id univoco associato al nemico
     *
     * @return l'id univoco associato al nemico
     */
    public String getId() {
        return id;
    }

    /**
     * Override del metodo hashCode: genera l'hash basandosi sul campo id.
     * Implementato per garantire l'univocita' del nemico generato durante una
     * partita, e utilizzato per il confronto dei vari nemici per recuperare l'item
     * da droppare
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Override del metodo equals: definisce il criterio di ugualianza basandosi
     * sull'id del nemico. Implementato per garantire l'univocita' del nemico
     * generato durante una partita, e utilizzato per il confronto dei vari nemici
     * per recuperare l'item da droppare
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEnemy other = (AbstractEnemy) obj;
        return Objects.equals(id, other.id);
    }


}
