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
public class ParseNbPersonneException extends ReservationException{
    public ParseNbPersonneException() {
            message = "Le format du nombre de personne doit être un entier";
        } 
}
