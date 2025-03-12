package model;

public class Profile extends AbstractModel {
    /**
     * Nickname del giocatore
     */
    private String nickname;
    /**
     * Avatar del giocatore
     */
    private String avatar;
    /**
     * Rappresenta il punteggio massimo ottenuto dal giocatore
     */
    private int maxScore;
    /**
     * Rappresenta il livello massimo raggiunto dal giocatore
     */
    private int maxLevel;

    /**
     * Costruttore del profilo giocatore
     */
    public Profile() {
        this.nickname = "";
        this.avatar = "Avatar1";
        this.maxScore = 0;
        this.maxLevel = 0;
    }

    /**
     * Restituisce il Nickname del giocatore
     *
     * @return il Nickname del giocatore
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Imposta il Nickname del giocatore
     *
     * @param nickname il Nickname del giocatore
     */
    @SuppressWarnings("deprecation")
    public void setNickname(String nickname) {
        this.nickname = nickname;
        setChanged();
        notifyObservers();
    }

    /**
     * Restituisce l'avatar selezionato dal giocatore
     *
     * @return l'avatar selezionato dal giocatore
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Imposta l'avatar selezionato dal giocatore
     *
     * @param avatar l'avatar selezionato dal giocatore
     */
    @SuppressWarnings("deprecation")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
        setChanged();
        notifyObservers();
    }

    /**
     * Restituisce il punteggio massimo raggiunto dal giocatore
     *
     * @return il punteggio massimo raggiunto dal giocatore
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Imposta il punteggio massimo raggiunto dal giocatore
     *
     * @param maxScore il punteggio massimo raggiunto dal giocatore
     */
    @SuppressWarnings("deprecation")
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
        setChanged();
        notifyObservers();
    }

    /**
     * Restituisce il livello massimo raggiunto dal giocatore
     *
     * @return il livello massimo raggiunto dal giocatore
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Imposta il livello massimo raggiunto dal giocatore
     *
     * @param maxLevel il livello massimo raggiunto dal giocatore
     */
    @SuppressWarnings("deprecation")
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        setChanged();
        notifyObservers();
    }

}
