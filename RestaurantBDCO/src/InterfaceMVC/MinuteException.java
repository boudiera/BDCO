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
public class MinuteException extends AddReservationException {
    /**
     * Creates a new instance of <code>MinuteException</code> without detail
     * message.
     */
    public MinuteException() {
        message="La saisie des minutes est incorrecte";
    }

    /**
     * Constructs an instance of <code>MinuteException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MinuteException(String msg) {
        super(msg);
    }
}
