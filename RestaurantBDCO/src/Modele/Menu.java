package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Menu extends Article {

    private List<Article> list;
    private HashMap<TypeArticle, Article> contientType;

    public Menu(String name, String speciality, float price) {
        super(name, TypeArticle.MENU, price, speciality);
        list = new ArrayList<Article>();
        contientType = new HashMap<>();

    }

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }

    public void ajoutArticle(Article article) {
        // Si le list d'article contient deja un article du meme type, on le supprime et on ajoute le nouvel article
        if (contientType.containsKey(article.getType())) {
            list.remove(contientType.get(article.getType()));

        }
        contientType.put(article.getType(), article);
        list.add(article);
    }

    
    public boolean contientPlat(){
        return contientType.containsKey(TypeArticle.PLAT);
    }
            
    public boolean contientAutreQuePlat(){
        return contientType.containsKey(TypeArticle.BOISSON) || contientType.containsKey(TypeArticle.DESSERT) ||  contientType.containsKey(TypeArticle.ENTREE);
    }
    // affiche le menu
    public void printMenu() {

        for (Article a : list) {
            System.out.println("- "+a.getName());
        }

    }

}
