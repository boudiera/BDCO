package Modele;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int codeReservation;
    private float price;
    private final List<Article> listArticles;

    public Commande(int codeReservation, List<Article> list){
        this.codeReservation = codeReservation;
        this.price=0;
        this.listArticles=list;
        for(Article art : this.listArticles){
            this.price += art.getPrice();
        }
    }

    public float getPrice(){
        return this.price;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
