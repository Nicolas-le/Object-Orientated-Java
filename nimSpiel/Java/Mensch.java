/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1;

/**
 * Die Klasse Mensch des Nim-Spiels.
 * Sie ist der vom menschlichen User gesteuerte Spieler.
 * @author Martin & Nicolas
 */
public class Mensch{
    
    private String name;

    /**
     * Der Konstruktor der Klasse Mensch legt den String 'name' für das Objekt fest.
     * @param name
     */
    public Mensch(String name) {
        
        this.name = name;
        
    }

    /**
     * Setter für das Attribut 'name'.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter fuer den Namen.
     * @return name
     */
    public String getName() {
        return name;
    }
    
       
    /**
     * Die Funktion steineNehmen(int steine) bekommt die Anzahl der im Steinhaufen vorhandenen
     * Steine übergeben und fragt dann ab, wie viele durch den Spieler entnommen werden sollen.
     * Wenn die Eingabe nicht zwischen 1 und 3 liegt wird eine Aufforderung zurueckgegeben einen neuen Wert einzugeben.
     * Wenn die Eingabe mit den noch vorhanden Steinen uebereinstimmt, oder nurnoch ein Stein vorhanden ist, wird 0
     * zurueckgegeben.
     * @param steine
     * @return Gibt die neue Anzahl der Steine zurueck.
     */
    public int steineNehmen(int steine){
      

      int eingabe = Eingabe.eingabeInt("Wie viele Steine wollen Sie nehmen?");
           
      int rueckgabe = 0;  
      
      while(eingabe  > 3 || eingabe < 1){
          
          eingabe = Eingabe.eingabeInt("Die Eingabe erfuellt nicht die Bedingungen. Waehle 1-3 Steine!");
          
      }   
          
      if(eingabe >= steine || steine == 1){
          
          rueckgabe = 0;
          
      }else{
          
          rueckgabe =  steine - eingabe;
          
      }
      
      System.out.println("Es sind noch " + rueckgabe + " Steine im Spiel.");
      return rueckgabe;
  
   }

}
