import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

enum Rolle {
    TRAENER,
    FORMAND,
    KASSERER
}

public class MenuSystem {
    Scanner input = new Scanner(System.in);
    static final String KODE_TRAENER  = "Traener1";
    static final String KODE_FORMAND  = "Formand1";
    static final String KODE_KASSERER = "Kassere1";

    // til trænerdelen
     TrainingResult trainingResult = new TrainingResult();
     ArrayList<TrainingResult.KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();

    // ====================
    // LOGIN
    // ====================
     boolean erGyldigKode(String kode) {
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

     Rolle fåRolleFraKode(String kode) {
        if (!erGyldigKode(kode)) return null;
        if (kode.equals(KODE_TRAENER))  return Rolle.TRAENER;
        if (kode.equals(KODE_FORMAND))  return Rolle.FORMAND;
        if (kode.equals(KODE_KASSERER)) return Rolle.KASSERER;
        return null;
    }

     Rolle loginMenu(Scanner input) {
        System.out.println("--- LOGIN ---");
        System.out.print("Indtast rolle-kode: ");
        String kode = input.nextLine();

        Rolle rolle = fåRolleFraKode(kode);
        if (rolle == null) {
            System.out.println("Login fejlede.");
            return null;
        }
        System.out.println("Logget ind som: " + rolle);
        return rolle;
    }

    // ====================
    // TRÆNER-MENU (simpel)
    // ====================
     void traenerMenu(Scanner input, Rolle rolle) {

        if (!(rolle == Rolle.TRAENER || rolle == Rolle.FORMAND)) {
            System.out.println("Kun TRÆNER eller FORMAND har adgang til træner-menuen.");
            return;
        }

        boolean kør = true;
        while (kør) {
            System.out.println("\n--- TRÆNER MENU ---");
            System.out.println("1. Opret konkurrencesvømmer");
            System.out.println("2. Tilføj træningsresultat");
            System.out.println("3. Vis svømmere + bedste træningstider");
            System.out.println("4. Tilbage");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {
                case 1: { // opret konkurrencesvømmer
                    System.out.print("Navn: ");
                    String navn = input.nextLine();

                    System.out.print("Telefon: ");
                    int telefon = input.nextInt();
                    input.nextLine();

                    System.out.print("Email: ");
                    String email = input.nextLine();

                    System.out.print("Adresse: ");
                    String adresse = input.nextLine();

                    String aktivitetstype = "aktivt";
                    Boolean konkurrence = true;

                    Medlem medlem = new Medlem(navn, telefon, email, adresse, aktivitetstype, konkurrence);
                    TrainingResult.KonkurrenceSvømmer ks = trainingResult.new KonkurrenceSvømmer(medlem);
                    konkurrenceSvømmere.add(ks);

                    System.out.println("Konkurrencesvømmer oprettet.");
                    break;
                }

                case 2: { // tilføj træningsresultat
                    if (konkurrenceSvømmere.isEmpty()) {
                        System.out.println("Ingen konkurrencesvømmere endnu.");
                        break;
                    }

                    System.out.println("Vælg svømmer:");
                    for (int i = 0; i < konkurrenceSvømmere.size(); i++) {
                        Medlem m = konkurrenceSvømmere.get(i).getMedlem();
                        System.out.println((i + 1) + ". " + m.getNavn() + " (tlf: " + m.getTelefon() + ")");
                    }
                    System.out.print("Valg: ");
                    int sValg = input.nextInt();
                    input.nextLine();

                    if (sValg < 1 || sValg > konkurrenceSvømmere.size()) {
                        System.out.println("Ugyldigt valg.");
                        break;
                    }

                    TrainingResult.KonkurrenceSvømmer ks = konkurrenceSvømmere.get(sValg - 1);

                    // vælg disciplin
                    System.out.println("Vælg disciplin:");
                    TrainingResult.Discipline[] discipliner = TrainingResult.Discipline.values();
                    for (int i = 0; i < discipliner.length; i++) {
                        System.out.println((i + 1) + ". " + discipliner[i]);
                    }
                    System.out.print("Valg: ");
                    int dValg = input.nextInt();
                    input.nextLine();
                    if (dValg < 1 || dValg > discipliner.length) {
                        System.out.println("Ugyldigt valg.");
                        break;
                    }
                    TrainingResult.Discipline d = discipliner[dValg - 1];

                    System.out.print("Tid (sekunder, fx 58.23): ");
                    double tid = input.nextDouble();
                    input.nextLine();

                    System.out.print("Dato (YYYY-MM-DD): ");
                    String datoStr = input.nextLine();
                    LocalDate dato = LocalDate.parse(datoStr);

                    TrainingResult.Result r = trainingResult.new Result(d, tid, dato);
                    ks.addTrainingResult(r);

                    System.out.println("Træningsresultat tilføjet.");
                    break;
                }

                case 3: { // vis bedste tider
                    if (konkurrenceSvømmere.isEmpty()) {
                        System.out.println("Ingen konkurrencesvømmere endnu.");
                        break;
                    }

                    System.out.println("--- Oversigt ---");
                    for (TrainingResult.KonkurrenceSvømmer ks : konkurrenceSvømmere) {
                        Medlem m = ks.getMedlem();
                        System.out.println("Svømmer: " + m.getNavn() + " (tlf: " + m.getTelefon() + ")");
                        for (TrainingResult.Discipline d : TrainingResult.Discipline.values()) {
                            TrainingResult.Result br = ks.getBestTraining(d);
                            if (br != null) {
                                System.out.println("  " + d + " -> " + br.getTime() + " sek, " + br.getDate());
                            }
                        }
                        System.out.println();
                    }
                    break;
                }

                case 4:
                    kør = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }

    // ====================
    // HOVEDMENU (medlemmer)
    // ====================
    public static void main(String[] args) {

    }
}
