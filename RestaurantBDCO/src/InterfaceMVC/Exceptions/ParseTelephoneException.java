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
public class ParseTelephoneException extends ReservationException{

    public ParseTelephoneException() {
        message = "le numero de telephone doit contenir 10 caractères qui sont des entiers";
    }
    
}
