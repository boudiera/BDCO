/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.AbstractController;
import InterfaceMVC.AbstractView;

/**
 *
 * @author trentini
 */
public class GraphicViews extends AbstractView {

    private ViewReservationList frameReservationList = ViewReservationList.singletonFrameReservationList();
    
    @Override
    public void addController(AbstractController c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVisible(boolean b) {
        this.frameReservationList.setVisible(b);
    }
}
