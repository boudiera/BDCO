/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import java.util.Observer;

/**
 *
 * @author Iago Felipe Trentin
 */
public interface WindowView extends Observer{

    public void setEnabled(boolean b);
    public void setVisible(boolean b);
    
    public boolean isSingleton();

}
