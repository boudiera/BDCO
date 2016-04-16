/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoriesLayer;

import Modele.Article;
import Modele.TypeArticle;

/**
 *
 * @author flore
 */
public class ConcreteArticle extends Article{
    
    public ConcreteArticle(String name, TypeArticle type, float price, String speciality) {
        super(name, type, price, speciality);
    }
    
}
