/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Iago Felipe Trentin
 */
public class ReservationDate {

    private int year = 0;
    private int month = 0;
    private int day = 0;
    
    private int hour = 0;
    private int min = 0;
    
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }
    
    /*public boolean isAfter(ReservationDate rd){
        if(this.year < rd.getYear()){
            return true;
        }
    }
    
    public boolean isBefore(ReservationDate rd){
        if(this.year )
    }*/
    
    public boolean isEquals(ReservationDate rd){
        if(     this.year  == rd.getYear() &&
                this.month == rd.getMonth() &&
                this.day   == rd.getDay() &&
                this.hour  == rd.getHour() &&
                this.min   == rd.getMin() ){
            return true;
        }else{
            return false;
        }
    }
    
    public ReservationDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public ReservationDate(int year, int month, int day, int hour, int min){
        this.year = year;
        this.month = month;
        this.day = day;
        
        this.hour = hour;
        this.min = min;
    }
    
    public String writeDateSortable(){
        String s = "";
        
        s += this.writeYear();
        s += " / ";
        s += this.writeMonth();
        s += " / ";
        s += this.writeDay();
        
        s += " : ";
        
        s += this.writeHour();
        s += ":";
        s += this.writeMin();
        
        return s;
    }
    
    public String writeDateComplete(){
        String s = "";
        
        s += this.writeDayMonthName();
        s += " / ";
        s += this.writeYear();
        s += ", ";
        s += this.writeHourMin();
        
        return s;
    }
    
    public String writeDayMonthNameHourMin(){
        String s = "";
        
        s += this.writeDayMonthName();
        s += ", ";
        s+= this.writeHourMin();
        
        return s;
    }
    
    public String writeHourMin(){
        String s = "";
        
        s += this.writeHour();
        s += " : ";
        s += this.writeMin();
        
        return s;
    }
    
    public String writeMin(){
        String s = "";
        
        if(this.getMin()>= 10){
            s = Integer.toString(this.getMin());
        }else{
            s = "0" + Integer.toString(this.getMin());
        }

        return s;
    }

    public String writeHour(){
        String s = "";
        
        if(this.getHour() >= 10){
            s = Integer.toString(this.getHour());
        }else{
            s = "0" + Integer.toString(this.getHour());
        }

        return s;
    }
    
    public String writeDayMonthName(){
        String s = "";
        
        s += this.writeDay();
        s += " / ";
        s += this.writeMonthName();
        
        return s;
    }
    
    public String writeDayMonth(){
        String s = "";
        
        s += this.writeDay();
        s += " / ";
        s += this.writeMonth();
        
        return s;
    }
    
    public String writeDay(){
        String s = "";
        
        if(this.getDay() >= 10){
            s = Integer.toString(this.getDay());
        }else{
            s = "0" + Integer.toString(this.getDay());
        }

        return s;
    }
    
    public String writeMonth(){
        String s = "";
        
        if((this.getMonth()) >= 10){
            s = Integer.toString(this.getMonth());
        }else{
            s = "0" + Integer.toString(this.getMonth());
        }
        
        return s;
    }
    
    public String writeMonthName(){
        String s = "";
        
        switch (this.getMonth()){
            case 0 : s = "Jan"; break;
            case 1 : s = "Fev"; break;
            case 2 : s = "Mar"; break;
            case 3 : s = "Avr"; break;
            case 4 : s = "Mai"; break;
            case 5 : s = "Jun"; break;
            case 6 : s = "Jul"; break;
            case 7 : s = "Aou"; break;
            case 8 : s = "Sep"; break;
            case 9 : s = "Oct"; break;
            case 10: s = "Nov"; break;
            case 11: s = "Dec"; break;
            default: s = "Unknown"; break;
        }
        
        return s;
    }
    
    public String writeYear(){
        return Integer.toString(this.getYear());
    }
    
    @Override
    public String toString(){
        return this.writeDateSortable();
    }
}
