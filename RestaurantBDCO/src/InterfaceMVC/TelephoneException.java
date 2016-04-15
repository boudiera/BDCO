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
public class TelephoneException extends AddReservationException {
    /**
     * Creates a new instance of <code>TelephoneException</code> without detail
     * message.
     */
    public TelephoneException() {
        message="La saisie du numéro de téléphone est incorrecte: veuillez saisir 10 chiffres";
    }

    /**
     * Constructs an instance of <code>TelephoneException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TelephoneException(String msg) {
        super(msg);
    }
}
