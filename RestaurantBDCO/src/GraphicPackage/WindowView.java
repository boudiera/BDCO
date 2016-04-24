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

    public void setEnabled(boolean b);  //makes the window enabled or not; if false, the window cannot be edited/touched
    public void setVisible(boolean b);  //makes the window visible or not; if false, the window will still exists, but will be hidden
    
    public boolean isSingleton();   //tells if a window is singleton, it means we can only have one instance; it is good for the main window of the application

}
