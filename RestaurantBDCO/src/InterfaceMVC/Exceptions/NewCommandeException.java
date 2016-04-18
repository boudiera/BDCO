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
public class NewCommandeException extends ReservationException {
    public NewCommandeException() {
        message = "La commande doit avoir au moins un article ";
    }
}
