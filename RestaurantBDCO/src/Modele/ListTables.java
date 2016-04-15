/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Iago Felipe Trentin
 */
public class ListTables extends ArrayList<Integer> {
    
    private String Location;
    
    public String getLocation(){
        return this.Location;
    }
    
    @Override
    public String toString(){
        Iterator<Integer> it = iterator();
        if (!it.hasNext()) {
            return "[ none ]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (;;) {
            Integer e = it.next();
            sb.append(e.toString().equals(this) ? "(this Collection)" : e.toString());
            if (!it.hasNext()) {
                return sb.append(" ]").toString();
            }
            sb.append(" : ");
        }
    }
}
