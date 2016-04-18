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
public class RestaurantCompletException extends ReservationException {

    public RestaurantCompletException() {
        message = " Restaurant complet pour cette date là ";
    }
    
    
}
