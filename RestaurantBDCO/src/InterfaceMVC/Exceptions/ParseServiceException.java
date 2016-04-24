/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC.Exceptions;

/**
 *
 * @author Arnaud
 */
public class ParseServiceException extends ReservationException {

    public ParseServiceException() {
        message = " le format du service est MIDI ou SOIR ";
    }
     
}
