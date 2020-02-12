/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

/**
 * Die Main Klasse startet das Nim-Spiel.
 * @author Martin & Nicolas
 */
public class Main {
    
    /**
     * Die Main Methode erzeugt eine Instanz der Klasse Spiel.
     * Dann wird die Funktion run() ausgelöst und das Spiel beginnt.
     * @param args
     */
    public static void main(String[] args){
    
 
    Spiel spiel = new Spiel();
    spiel.run();
    
    }
}
