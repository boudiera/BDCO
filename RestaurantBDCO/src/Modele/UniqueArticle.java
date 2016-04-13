package Modele;

public class UniqueArticle extends Article {
	
	private UniqueArticle(String name, TypeArticle type, float price, String speciality){
		super(name, type, price, speciality);
	}
        
        public static UniqueArticle createEntry(String name, float price, String speciality){
            return new UniqueArticle(name, TypeArticle.Entry, price, speciality);
        }
        
        public static UniqueArticle createPlate(String name, float price, String speciality){
            return new UniqueArticle(name, TypeArticle.Plate, price, speciality);
        }
        
        public static UniqueArticle createDessert(String name, float price, String speciality){
            return new UniqueArticle(name, TypeArticle.Dessert, price, speciality);
        }
        
        public static UniqueArticle createDrink(String name, float price, String speciality){
            return new UniqueArticle(name, TypeArticle.Drink, price, speciality);
        }
}
