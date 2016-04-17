package Modele;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Commande {

    private int codeReservation;
    private float price;
    private final List<Article> listArticles;

    private final HashMap<String, Integer> regroupe = new HashMap<>();
    private String identifier;

    public Commande(int codeReservation, String identifier, List<Article> list) {
        this.identifier = identifier;
        this.codeReservation = codeReservation;
        this.price = 0;
        this.listArticles = list;
        for (Article art : this.listArticles) {
            this.price += art.getPrice();
        }
    }

    // Fonction qui permet de regrouper tous les articles de meme types d'une commande en vue de les afficher
    private Set<String> regroupeArticle() {

        for (Article a : this.listArticles) {
            if (!regroupe.containsKey(a.getName())) {
                regroupe.put(a.getName(), 1);
            } else {
                regroupe.put(a.getName(), regroupe.get(a.getName()) + 1);
            }
        }

        return regroupe.keySet();

    }


    // Permet d'afficher les articles d'une commandes sous la forme nom x Quantité 
    public void printArticle() {
        Set<String> s = regroupeArticle();
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            String nomArticle = it.next();
            System.out.println(nomArticle + " x" + regroupe.get(nomArticle));

        }

    }

    public String getIdentifier() {
        return identifier;
    }

    public float getPrice() {
        return this.price;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    public int getCodeReservation() {
        return codeReservation;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
