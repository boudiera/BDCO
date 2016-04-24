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
public class ExistReservationException extends ReservationException {

    public ExistReservationException(String nomClient) {
        message="Une réservation au nom de " + nomClient + " pour le même jour et le même service a déjà été enregistrée, voulez-vous valider (v) quand même ou quitter (q)?";
    }
}
