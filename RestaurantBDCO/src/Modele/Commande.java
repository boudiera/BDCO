package Modele;

import java.util.*;

public class Commande {

    private int codeReservation;
    private float price;
    private final ArrayList<Article> listArticles;
    // Hasmap qui fait le lien entre un nom d'article et sa quantité
    private final HashMap<String, Integer> regroupeArticle = new HashMap<>();

    public HashMap<String, Integer> getRegroupeArticle() {
        return regroupeArticle;
    }

    private String identifier;

    public Commande(int codeReservation, String identifier, ArrayList<Article> list,int prix) {
        this.identifier = identifier;
        this.codeReservation = codeReservation;
        this.price = prix;
        this.listArticles = list;
        regroupe();
        
    }
    
    // Fonction qui permet de regrouper tous les articles de meme types d'une commande en vue de les afficher
    private void regroupe() {

        for (Article a : this.listArticles) {
            if (!regroupeArticle.containsKey(a.getName())) {
                regroupeArticle.put(a.getName(), 1);
            } else {
                regroupeArticle.put(a.getName(), regroupeArticle.get(a.getName()) + 1);
            }

        }
    }
    // Ajoute l'article a à la liste d'article et met à jour le regroupe

    public void addArticle(Article a) {
        listArticles.add(a);
        regroupeArticle.clear();
        regroupe();
        this.price += a.getPrice();
    }
    
    public void removeArticle(Article a){
        listArticles.remove(a);
        regroupeArticle.clear();
        regroupe();
        this.price -= a.getPrice();
    }

    // Permet d'afficher les articles d'une commandes sous la forme nom x Quantité 
    public void printArticle() {
        Set<String> s = regroupeArticle.keySet();
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            String nomArticle = it.next();
            System.out.println(nomArticle + " x" + regroupeArticle.get(nomArticle));

        }

    }

    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String s){
        this.identifier = s;
    }

    public float getPrice() {
        return this.price;
    }

    public ArrayList<Article> getListArticles() {
        return listArticles;
    }

    public int getCodeReservation() {
        return codeReservation;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
