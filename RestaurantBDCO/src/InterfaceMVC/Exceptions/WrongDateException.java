/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC.Exceptions;

/**
 *
 * @author mourinf
 */
public class WrongDateException extends ReservationException {

    /**
     * Creates a new instance of <code>Exception</code> without detail message.
     */
    public WrongDateException() {
        message="La date saisie est passée";
    }

    /**
     * Constructs an instance of <code>Exception</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public WrongDateException(String msg) {
        super(msg);
    }
}
