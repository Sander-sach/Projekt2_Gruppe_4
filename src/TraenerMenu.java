import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TraenerMenu {

    // KODE TIL TRÆNER
    private static final String KODE_TRAENER = "Traener1";

    // Simpelt kode-tjek (min 8 tegn, stor/lille/tal – som i dit andet kode)
    private static boolean erGyldigKode(String kode) {
        if (kode.length() < 8) return false;
        boolean harStor = false, harLille = false, harTal = false;
        for (int i = 0; i < kode.length(); i++) {
            char c = kode.charAt(i);
            if (Character.isUpperCase(c)) harStor = true;
            if (Character.isLowerCase(c)) harLille = true;
            if (Character.isDigit(c))     harTal = true;
        }
        return harStor && harLille && harTal;
    }

    private static boolean loginTraener(Scanner input) {
        System.out.println("--- TRÆNER LOGIN ---");
        System.out.print("Indtast trænerkode: ");
        String kode = input.nextLine();

        if (!erGyldigKode(kode)) {
            System.out.println("Koden opfylder ikke kravene (min 8 tegn, stor/lille/tal).");
            return false;
        }

        if (!kode.equals(KODE_TRAENER)) {
            System.out.println("Forkert trænerkode.");
            return false;
        }

        System.out.println("Login som TRÆNER godkendt.\n");
        return true;
    }

    public static void main(String[] args) {

    }
}

