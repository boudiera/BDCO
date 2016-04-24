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
public class MenuMustHavePlatEtDAutre extends ReservationException {
    public MenuMustHavePlatEtDAutre() {
        message = "Le menu doit avoir au moins 1 Plat et 1 autre Article ";
    }
}
