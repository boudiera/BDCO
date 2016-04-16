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
public class ParseNomClientException extends ReservationException{

    public ParseNomClientException() {
        message = " Le format du nom du client ne doit pas contenir de chiffres "; 
    }
    
}
