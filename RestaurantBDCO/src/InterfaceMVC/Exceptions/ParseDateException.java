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
public class ParseDateException extends ReservationException {
     
        public ParseDateException() {
            message = "Le format pour la date est de 3 entiers pour l'année, le mois et le jour ";
        }     
}
