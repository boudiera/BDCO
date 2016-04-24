/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC.Exceptions;

import InterfaceMVC.Exceptions.ReservationException;

/**
 *
 * @author mourinf
 */
public class JourException extends ReservationException {

    /**
     * Creates a new instance of <code>JourException</code> without detail
     * message.
     */
    public JourException() {
       message="La saisie du jour est incorrecte";
    }

    /**
     * Constructs an instance of <code>JourException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public JourException(String msg) {
        super(msg);
    }
}
