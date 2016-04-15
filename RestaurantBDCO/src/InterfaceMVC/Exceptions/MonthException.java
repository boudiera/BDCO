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
public class MoisException extends AddReservationException {

    /**
     * Creates a new instance of <code>MoisException</code> without detail
     * message.
     */
    public MoisException() {
       message="La saisie du mois est incorrect";
    }

    /**
     * Constructs an instance of <code>MoisException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MoisException(String msg) {
        super(msg);
    }
}
