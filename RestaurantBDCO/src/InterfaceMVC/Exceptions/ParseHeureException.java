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
public class ParseHeureException extends ReservationException{
     
        public ParseHeureException() {
            message = "Le format de l'horaire est d'un entier pour les minutes et l'heure ";
        }     
}
