package model;

import java.util.Observable;
/**
 * Rappresenta un generico modello. Estende la classe Observable e fornisce i
 * metodi necessari per notificare un aggiornamento ai vari observers
 */
public abstract class AbstractModel extends Observable {

    /**
     * Metodo che permette di notificare gli observer quando avviene un
     * aggiornamento
     */
    public void notifyUpdate() {
        setChanged();
        notifyObservers();
    }
}
