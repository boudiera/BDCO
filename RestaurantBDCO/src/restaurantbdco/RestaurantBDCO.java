/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantbdco;

import InterfaceMVC.RunGraphicMVC;
import Modele.Permutation.*;
import TextualInterface.TestTextuel;
import java.util.Arrays;
import java.util.Iterator;
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
        // TODO code application logic here
                
        ///////////// EXEMPLE DE UTILISATION
        
        Iterator<int[]> i;       
        i = new SepaPnkIterator(4, 3);      // 4 tables combiné 3 par 3 (on teste 2 par 2, 3 par 3, 4 par 4, jusqu'au maximum de table de la localisation)
        
        int count=0;
        while(i.hasNext()){
            System.out.print(Arrays.toString(i.next()) + ", ");
            count++;
        }
        
        System.out.println("\n\nTotal: " + count);
        
        //////////////
        
        
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
