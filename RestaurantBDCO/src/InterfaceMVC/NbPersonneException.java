/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

/**
 *
 * @author mourinf
 */
public class NbPersonneException extends AddReservationException {

    /**
     * Creates a new instance of <code>NbPersonneException</code> without detail
     * message.
     */
    public NbPersonneException() {
        message="Vous devez rentrer un nombre de personnes positif";
    }

    /**
     * Constructs an instance of <code>NbPersonneException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NbPersonneException(String msg) {
        super(msg);
    }
}
