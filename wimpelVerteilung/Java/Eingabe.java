package oop4;
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
     * Bestimmt zusaetzlich, dass nur eine positive Zahl verwendet werden kann.
     * @param frage Gibt aus was gefragt werden soll.
     * @return Usereingabe
     */
    public static int eingabeInt(String frage){

        System.out.println(frage);

        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Bitte gib eine positive Zahl ein!");

            while (!sc.hasNextInt()) {
                System.out.println("Leider keine Zahl.");
                sc.next();
            }
            number = sc.nextInt();
        } while (number <= 0);

        return number;
    }
}