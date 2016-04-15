package Modele;

import java.util.ArrayList;
import java.util.List;

public class Commande {
	private float price;
	private List<Article> listArticles;
        
        public Commande(List<Article> list){
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
