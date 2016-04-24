/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC.Exceptions;

/**
 *
 * @author Iago Felipe Trentin
 */
public class CommandesNotSaved extends ReservationException {
    public CommandesNotSaved() {
        message = "Vous avez des Commandes a facturer aujourd'hui ";
    }
}
