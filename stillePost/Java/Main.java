/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;

/**
 * Main Methode initialisiert die Welt und startet das rundenbasierte Leben.
 * @author Martin & Nicolas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        Welt welt = new Welt();
        welt.leben();
        
    }
    
}
