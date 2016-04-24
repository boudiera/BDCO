/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

/**
 *
 * @author Iago Felipe Trentin
 */
public abstract class AbstractView{
    
    private Controller controller;      // The View knows its Controller
    
    public void setController(Controller c){
        this.controller = c;
    }
    
    public Controller getController(){
        return this.controller;
    }

    public abstract void showView(boolean b);   // A View can be shown

}
