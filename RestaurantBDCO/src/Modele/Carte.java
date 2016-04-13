/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leclairn
 */
public class Carte {
    private final List<List<Article>> tablist;
    
    public Carte(List<Article> list){
        this.tablist = new ArrayList<>();
        
        for(Article art : list){
            this.tablist.get(art.getType().ordinal()).add(art);
        }
    }
    
    public List<List<Article>> getCarte(){
        return this.tablist;
    }
 
}
