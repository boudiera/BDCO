/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import InterfaceMVC.AbstractController;

/**
 *
 * @author Iago Felipe Trentin
 */
public abstract class AbstractView {
    
    public abstract void addController(AbstractController c);
    
    public abstract void setVisible(boolean b);
    
}
