
package oop1;

/**
 *  Die Klasse Computer des Nim-Spiels. 
 *  @author Nicolas & Martin
 */
public class Computer{
    
    /**
     *  Konstruktor ohne Parameter. 
     */
    public Computer(){
        
        
    }
    
    /**
     * Die Funktion nimmt die optimale Menge an Steinen vom Stapel.
     * Es werden die vorhandenen Steine im Haufen uebergeben. Wenn diese bereits 1 betragen, wird 0 zurueckgegeben.
     * Die Funktion versucht immer genau einen Stein zurueckzulassen. Dies Funktioniert ueber eine mod 4 Rechnung.
     * @param steine
     * @return Neue Anzahl an Steinen
     */
    public int optimaleSteine(int steine){
        
       if(steine == 1){
           
            return 0;
        
       }
       int wegnehmen = 0;
        switch(steine %4){
            
            case 0:
                wegnehmen = 3;
                break;
            case 1:
                wegnehmen =  1+ (int)(Math.random()* ((3 - 1) + 1));
                break;
            case 2:
                wegnehmen = 1;
                break;
            case 3:
                wegnehmen = 2;
                break;
        }
        
        System.out.println("Der Computer hat " + wegnehmen + " Steine genommen.");
        System.out.println("Es sind noch " + (steine - wegnehmen) + " Steine im Spiel");
        return steine - wegnehmen;
        
    }
}

