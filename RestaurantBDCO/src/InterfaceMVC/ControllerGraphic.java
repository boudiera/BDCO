/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

/**
 *
 * @author trentini
 */
public class ControllerGraphic extends Controller{
   
    public void startView(){
        this.getView().showView(true);
    };

    @Override
    public void setView(AbstractView v){
        
    }
}
