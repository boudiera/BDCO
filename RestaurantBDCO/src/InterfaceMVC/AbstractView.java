/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import InterfaceMVC.Controller;

/**
 *
 * @author Iago Felipe Trentin
 */
public abstract class AbstractView {
    
    Controller controller;
    
    public void setController(Controller c){
        this.controller = c;
    }
    
    public Controller getController(){
        return this.controller;
    }
    
    public abstract void showView(boolean b);
    

}
