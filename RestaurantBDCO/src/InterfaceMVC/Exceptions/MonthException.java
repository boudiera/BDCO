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
public class MonthException extends ReservationException {

    /**
     * Creates a new instance of <code>MoisException</code> without detail
     * message.
     */
    public MonthException() {
        message="La saisie du mois est incorrect";
    }

    /**
     * Constructs an instance of <code>MoisException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MonthException(String msg) {
        super(msg);
    }
}
