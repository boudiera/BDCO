package Modele;

import java.util.ArrayList;
import java.util.List;

public class Command {
	private float price;
	private final List<Article> listArticles;
        
        public Command(List<Article> list){
            this.price=0;
            this.listArticles=list;
            for(Article art : this.listArticles){
                this.price += art.getPrice();
            }
        }
        
        public float getPrice(){
            return this.price;
        }
}
