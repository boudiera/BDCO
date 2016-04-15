/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

/**
 *
 * @author mourinf
 */
public class HeureException extends Exception {

    /**
     * Creates a new instance of <code>HeureException</code> without detail
     * message.
     */
    public HeureException() {
        System.out.println("La saisie de l'heure est incorrecte");
    }

    /**
     * Constructs an instance of <code>HeureException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public HeureException(String msg) {
        super(msg);
    }
}
