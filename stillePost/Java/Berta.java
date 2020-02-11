/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;

/**
 * Abgeleitete Klasse von Mensch, spezifiziert speziellen Menschen 'Berta'. 
 * @author Nicolas & Martin
 */
public class Berta extends Mensch{
    
    private String name;
    
    /**
     * Konstruktor initiliasisert mit Dementi-Behauptung true, einer ID und dem Namen.
     * @param id
     */
    public Berta(long id){
        
        super(false,true,id);
        this.name = "Berta Bluemnchen";
        
    }
    

}
