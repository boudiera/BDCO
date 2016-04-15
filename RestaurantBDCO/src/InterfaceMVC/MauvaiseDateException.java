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
public class MauvaiseDateException extends Exception {

    /**
     * Creates a new instance of <code>Exception</code> without detail message.
     */
    public MauvaiseDateException() {
        System.out.println("La date saisie est passée!");
    }

    /**
     * Constructs an instance of <code>Exception</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MauvaiseDateException(String msg) {
        super(msg);
    }
}
