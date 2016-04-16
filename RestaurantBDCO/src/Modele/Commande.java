package Modele;

import java.util.List;

public class Commande {
    private int codeReservation;
    private float price;
    private final List<Article> listArticles;
    
    private String identifier;

    public Commande(int codeReservation, String identifier, List<Article> list){
        this.identifier = identifier;
        this.codeReservation = codeReservation;
        this.price=0;
        this.listArticles=list;
        for(Article art : this.listArticles){
            this.price += art.getPrice();
        }
    }

    public String getIdentifier() {
        return identifier;
    }
    
    public float getPrice(){
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
