/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.*;

/**
 *
 * @author trentini
 */
public class GraphicViews extends AbstractView {

    private ViewReservationList frameReservationList = ViewReservationList.singletonFrameReservationList();
    
    @Override
    public void showView(boolean b) {
        this.frameReservationList.setVisible(b);
    }
}
