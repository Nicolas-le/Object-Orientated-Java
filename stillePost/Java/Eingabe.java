/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;
import java.util.Scanner;

/**
 * Die Klasse regelt die Usereingabe. 
 * Statische Klassen sind nur nested moeglich. 
 * Deswegen ist hier die Klasse final, hat also nur eine Entitaet.
 * Die Methoden sind statisch, benoetigen also keine Initialsierung in einem Objekt.
 * @author Nicolas & Martin
 */
public final class Eingabe {
    
    /**
     * Der Parameter Frage wird ausgegeben und die Eingabe als String zurueckgegeben.
     * @param frage Gibt aus was gefragt werden soll.
     * @return Usereingabe
     */
    public static String eingabeString(String frage){
            
            
            System.out.println(frage);
            Scanner sc = new Scanner(System.in);
            String eingabe = sc.nextLine();
            
            return eingabe;
        }
        
    /**
     * Analog zu eingabeString()
     * @param frage Gibt aus was gefragt werden soll.
     * @return Usereingabe
     */
    public static int eingabeInt(String frage){

            int eingabe = 0;
            System.out.println(frage);
            Scanner sc = new Scanner(System.in);
            
            if(sc.hasNextInt()){
                    eingabe = sc.nextInt();  
                }
            
            return eingabe;
            
        }
}
