package utility;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AudioManager {
    /**
     * Istanza singleton della classe AudioManager
     */
    private static AudioManager instance;
    /**
     * Stringa che identifica il percorso dove sono contenuti i file audio
     */
    private static final String PATH = "/sounds/";
    /**
     * Nome del file audio per il salto del giocatore
     */
    public static final String JUMP = "jump.wav";
    /**
     * Nome del file audio per la bolla
     */
    public static final String BUBBLE = "bubble.wav";
    /**
     * Nome del file audio per la bolla di fuoco
     */
    public static final String FIRE_BUBBLE = "fire-bubble.wav";
    /**
     * Nome del file audio per la morte di un nemico
     */
    public static final String ENEMY_DEATH = "enemy-death.wav";
    /**
     * Nome del file audio per il raccoglimento di un Item o un PowerUp
     */
    public static final String ITEM_COLLECTED = "item-collected.wav";
    /**
     * Nome del file audio per la morte del giocatore
     */
    public static final String PLAYER_DEATH = "player-death.wav";
    /**
     * Nome del file audio per la schermata di GameOver
     */
    public static final String GAME_OVER = "game-over.wav";
    /**
     * Nome del file audio per la musica di sottofondo del gioco
     */
    public static final String START = "main_theme.wav";
    /**
     * Nome del file audio per la schermata iniziale
     */
    public static final String ENTRANCE = "entrance.wav";
    /**
     * Nome del file audio per la schermata di registrazione del profilo
     */
    public static final String NAME_REGISTER = "name_register.wav";
    /**
     *
     */
    private Clip currentClip;

    /**
     * Restituisce l'instanza singleton corrente della classe AudioManager
     *
     * @return l'instanza singleton corrente della classe AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Costruttore privato della classe AudioManager come previsto dal pattern
     * Singleton
     */
    private AudioManager() {

    }

    /**
     * Metodo che riproduce l'audio che corrisponde al filename specificato
     *
     * @param filename l'audio che deve essere riprodotto
     */
    public void play(String filename) {
        try {
            InputStream in = new BufferedInputStream(AudioManager.class.getResourceAsStream(PATH + filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioIn);
            currentClip.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Metodo pubblico che ferma il clip corrente
     */
    public void stop() {
        if (currentClip != null) {
            if (currentClip.isRunning()) {
                currentClip.stop();
            }
            currentClip.flush();
            currentClip.close();
            currentClip = null;
        }
    }

}
