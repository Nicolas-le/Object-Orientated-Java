/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;

/**
 * Abgeleitete Klasse von Mensch, spezifiziert speziellen Menschen 'Anton'. 
 * @author Martin & Nicolas
 */
public class Anton extends Mensch{
    
    private String name;
    
    /**
     * Konstruktor initiliasisert mit Heirats-Behauptung true, einer ID und dem Namen.
     * @param id
     */
    public Anton(long id){
        
        super(true,false,id);
        this.name = "Anton Angeber";
        
    }
    

}
