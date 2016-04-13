package Modele;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Article {
	private List<UniqueArticle> list;
	
	public Menu(String name, String speciality, float price){
		super(name, TypeArticle.Menu, price, speciality);
                list = new ArrayList<UniqueArticle>();
	}

    public List<UniqueArticle> getList() {
        return list;
    }

    public void setList(List<UniqueArticle> list) {
        this.list = list;
    }
       
	
}
