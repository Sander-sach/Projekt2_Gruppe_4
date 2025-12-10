import java.util.*;

public class ScannerHelp {
    // Hjælpe metoder til at kunne skippe indtastninger hvis der ikke skal laves ændringer i console
    public static String skipString(Scanner sc){
        String input=sc.nextLine();
        return input.isEmpty() ? null:input;
    }
    // til integers
    public static Integer skipInteger(Scanner sc){
         String input=sc.nextLine();
        if(input.isEmpty()){
            return null;
        }else return Integer.parseInt(input);
    }
    //Hjælpe metode til forkert input i console for Integers
    public static Integer checkInputInt(Scanner sc){
        while(true){
            if(sc.hasNextInt()){
                return sc.nextInt();
            }else{System.out.println("Ugyldigt input!\n" +
                    "Prøv igen:");
                sc.nextLine();
            }
        }
    }
    //Hjælpe metode til forkert input  for aktivitetstype af medlemmer
    public static String checkInputType(Scanner sc) {
        while (true) {
            String input=sc.next().trim();
            if (input.equalsIgnoreCase("passivt") || input.equalsIgnoreCase("aktivt")) {
                return input;
            } else {
                System.out.println("Ugyldigt input!\n" +
                        "Format skal være:(Aktivt eller Passivt) Prøv igen:");
                sc.nextLine();
            }
        }
    }

// til test
    public static void main(String[]arg){
    }
}
