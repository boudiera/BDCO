/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantbdco;

import InterfaceMVC.RunGraphicMVC;
import TextualInterface.TestTextuel;
import java.util.Scanner;

/**
 *
 * @author trentini
 */
public class RestaurantBDCO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Type 't' for TEXT interface or 'g' for GRAPHIC interface:");
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        
        if(choix.equalsIgnoreCase("t")){
            TestTextuel.main(args);
        }else{
            RunGraphicMVC.run(args);
        }
    }
    
}
