package model;

import utility.GameConstants;

import java.util.ArrayList;

public class Player extends AbstractEntity {
    /**
     * Specifica se il giocatore sta premendo il tasto "sinistra"
     */
    private boolean left;
    /**
     * Specifica se il giocatore sta' premendo il tasto "destra"
     */
    private boolean right;
    /**
     * Indica se il giocatore e' stato colpito
     */
    private boolean isHit;
    /**
     * Indica se il giocatore sta saltando
     */
    private boolean jump;
    /**
     * Indica se il giocatore sta' attaccando
     */
    private boolean attacking;
    /**
     * Collection che rappresenta i vari oggetti collezionati dal giocatore durante
     * il gioco
     */
    private ArrayList<Integer> itemsCollected;
    /**
     * identifica il tempo trascorso dall'ultima bolla che il giocatore ha sparato
     */
    private long lastBubbleShot;

    /**
     * Costruttore della classe Player
     *
     * @param x      posizione x del giocatore
     * @param y      posizione y del giocatore
     * @param width  larghezza del giocatore
     * @param height altezza del giocatore
     */
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.setSpeed(GameConstants.Player.SPEED);
        this.itemsCollected = new ArrayList<Integer>();
        initHitbox(29,29);
    }

    /**
     * Restituisce true se il giocatore sta premendo il tasto "sinistra", false
     * altrimenti
     *
     * @return true se il giocatore sta premendo il tasto "sinistra", false
     *         altrimenti
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Imposta lo stato "sinistra" del giocatore
     *
     * @param left true se il giocatore sta premendo il tasto "sinistra", false
     *             altrimenti
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Restitruisce true se il giocatore sta premendo il tasto "destra", false
     * altrimenti
     *
     * @return true se il giocatore sta premendo il tasto "destra", false altrimenti
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Imposta lo stato "destra" del giocatore
     *
     * @param right true se il giocatore sta premendo il tasto "destra", false
     *              altrimenti
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Restituisce true se il giocatore sta saltando, false altrimenti
     *
     * @return true se il giocatore sta saltando, false altrimenti
     */
    public boolean isJump() {
        return jump;
    }

    /**
     * Imposta jump a true se il giocatore sta saltando, false altrimenti
     *
     * @param jump true se il giocatore sta saltando, false altrimentis
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * Restituisce true se il giocatore è stato colpito, false altrimenti
     *
     * @return true se il giocatore è stato colpito, false altrimenti
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Imposta lo stato "colpito" del giocatore
     *
     * @param isHit true se il giocatore è stato colpito, false altrimenti
     */
    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    /**
     * Restituisce true se il giocatore sta' attaccando, false altrimenti
     *
     * @return true se il giocatore sta' attaccando, false altrimenti
     */
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Imposta lo stato "attacco" del giocatore
     *
     * @param attacking true se il giocatore sta' attaccando, false altrimenti
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /**
     * Restituice gli item collezionati durante il gioco
     *
     * @return item collezionati durante il gioco
     */
    public ArrayList<Integer> getItemsCollected() {
        return itemsCollected;
    }

    /**
     * Permette di aggiungere un item alla collezione
     *
     * @param item item da aggiungere
     */
    public void addItem(int item) {
        itemsCollected.add(item);
    }

    /**
     * Restituisce true se l'item passato in iput e' presente nella lista
     * itemsCollected, false altrimenti
     *
     * @param item item da verificare
     * @return true se l'item passato in iput e' presente nella lista
     *         itemsCollected, false altrimenti
     */
    public boolean hasItem(int item) {
        return this.itemsCollected.contains(item);
    }

    /**
     * Restituisce il tempo trascorso dall'ultima bolla sparata
     *
     * @return il tempo trascorso dall'ultima bolla sparata
     */
    public long getLastBubbleShot() {
        return lastBubbleShot;
    }

    /**
     * Imposta il tempo trascorso dell'ultima bolla sparata
     *
     * @param lastBubbleShot il tempo trascorso dell'ultima bolla sparata
     */
    public void setLastBubbleShot(long lastBubbleShot) {
        this.lastBubbleShot = lastBubbleShot;
    }
}
