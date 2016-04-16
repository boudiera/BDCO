/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import InterfaceMVC.AbstractView;
import InterfaceMVC.Controller;
import Modele.Article;
import Modele.Commande;
import java.util.ArrayList;

/**
 *
 * @author Arnaud
 */
public class TextualFacture extends AbstractView{

    
    int codeReservation;
    float prix = 0;
    public TextualFacture(Controller controller, int codeReservation) {
        setController(controller);
        this.codeReservation = codeReservation;
    }

    
    @Override
    public void showView(boolean b) {
        System.out.println("----------------------- FACTURE--------------------");
        ArrayList<Commande> listCommande = this.getController().getCommande(codeReservation);
       
        for (Commande c : listCommande){
            for(Article art : c.getListArticles()){
                System.out.println(art.getName());
            }
            prix = prix + c.getPrice();
        }
        
        System.out.println("Prix total : " + prix);
      
        System.exit(0);
    }
    
}
