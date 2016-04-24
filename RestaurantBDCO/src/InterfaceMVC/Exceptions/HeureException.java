/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC.Exceptions;

/**
 *
 * @author trentini
 */
public class HeureException extends ReservationException{
    /**
     * Creates a new instance of <code>HeureException</code> without detail
     * message.
     */
    public HeureException() {
        message = "La saisie de l'heure est incorrecte";
    }

    /**
     * Constructs an instance of <code>HeureException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HeureException(String msg) {
        super(msg);
    }
}
