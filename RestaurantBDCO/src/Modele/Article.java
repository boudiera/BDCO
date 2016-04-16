package Modele;

public  class Article {
        private TypeArticle type;
	private final float price;
	private final String name;
        private final String speciality;

	public Article(String name, TypeArticle type, float price, String speciality){
		this.name=name;
                this.price=price;
                this.speciality=speciality;
                this.type=type;
	}
        
        public float getPrice(){
            return this.price;
        }
        
        public TypeArticle getType(){
            return this.type;
        }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setType(TypeArticle type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String res = "NomArticle : " + name + " Prix : " + price + " Spécialité : " + speciality;
        return res;
    }
    
    
        
       
}
