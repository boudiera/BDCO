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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Arnaud
 */
public class TextualFacture extends AbstractView {

    int codeReservation;
    float prix = 0;

    public TextualFacture(Controller controller, int codeReservation) {
        setController(controller);
        this.codeReservation = codeReservation;
    }

    @Override
    public void showView(boolean b) {
        System.out.println("----------------------- FACTURE --------------------");
        ArrayList<Commande> listCommande = this.getController().getCommande(codeReservation);

        HashMap<String, Integer> regroupeQuantite = new HashMap<>();
        HashMap<String, Float> regroupePrix = new HashMap<>();
        for (Commande c : listCommande) {

            for (Article a : c.getListArticles()) {
                if (!regroupeQuantite.containsKey(a.getName())) {
                    regroupeQuantite.put(a.getName(), 1);
                    regroupePrix.put(a.getName(), a.getPrice());
                } else {
                    regroupeQuantite.put(a.getName(), regroupeQuantite.get(a.getName()) + 1);
                }
            }

            prix = prix + c.getPrice();
        }

        Set<String> s = regroupeQuantite.keySet();
        Iterator<String> it = s.iterator();

        while (it.hasNext()) {
            String nomArticle = it.next();
            System.out.println(nomArticle + " x" + regroupeQuantite.get(nomArticle) + " : " + (regroupePrix.get(nomArticle) * regroupeQuantite.get(nomArticle)));

        }

        System.out.println("Prix total : " + prix);

        System.exit(0);
    }

}
