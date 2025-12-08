import java.util.*;
import java.io.*;
public class ScannerHelp {
    //class med 2 metoder der retunere "Enter" som nuller i scannner
    // Hjælper med at man kan skippe indtastninger hvis der ikke skal lave ændringer
    static Scanner sc=new Scanner(System.in);
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

    public static void main(String[]arg)throws IOException{
        String input=sc.nextLine();
        if(input.isEmpty()) input=null;
    }
}
