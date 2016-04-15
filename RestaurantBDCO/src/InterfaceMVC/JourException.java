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
public class JourException extends Exception {

    /**
     * Creates a new instance of <code>JourException</code> without detail
     * message.
     */
    public JourException() {
        System.out.println("La saisie du jour est incorrecte");
    }

    /**
     * Constructs an instance of <code>JourException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public JourException(String msg) {
        super(msg);
    }
}
