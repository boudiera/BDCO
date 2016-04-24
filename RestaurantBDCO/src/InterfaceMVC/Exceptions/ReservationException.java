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
public abstract class ReservationException extends Exception {
    
    protected String message;
    
    /**
     * Creates a new instance of <code>AddReservationException</code> without
     * detail message.
     */
    public ReservationException() {
    }

    /**
     * Constructs an instance of <code>AddReservationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReservationException(String msg) {
        super(msg);
    }

  
    @Override
    public String getMessage() {
        return message; //To change body of generated methods, choose Tools | Templates.
    }
}
