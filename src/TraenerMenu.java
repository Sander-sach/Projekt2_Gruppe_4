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

        Scanner input = new Scanner(System.in);

        // Login kun som træner
        if (!loginTraener(input)) {
            System.out.println("Ingen adgang. Programmet afsluttes.");
            return;
        }

        // Vi bruger din TrainingResult-klasse og dens indre klasser
        TrainingResult tr = new TrainingResult();

        // Liste over konkurrencesvømmere
        ArrayList<TrainingResult.KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();

        boolean kør = true;

        while (kør) {
            System.out.println("\n--- TRÆNER MENU ---");
            System.out.println("1. Opret konkurrencesvømmer");
            System.out.println("2. Tilføj træningsresultat");
            System.out.println("3. Tilføj konkurrenceresultat");
            System.out.println("4. Vis svømmere + bedste træningstider");
            System.out.println("5. Afslut");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine(); // fjern newline

            switch (valg) {

                // 1. Opret konkurrencesvømmer
                case 1: {
                    System.out.println("\n--- Opret konkurrencesvømmer ---");
                    System.out.print("Navn: ");
                    String navn = input.nextLine();

                    System.out.print("Telefon: ");
                    int telefon = ScannerHelp.checkInputInt(input);
                    input.nextLine();

                    System.out.print("Email: ");
                    String email = input.nextLine();


                    System.out.print("Adresse: ");
                    String adresse = input.nextLine();

                    System.out.print("Aktivitetstype (fx 'aktivt'): ");
                    String aktivitetstype = ScannerHelp.checkInputType(input);

                    Boolean konkurrence = true; // konkurrencesvømmer
                    // Brug din ekstra Medlem-constructor
                    Medlem medlem = new Medlem(navn, telefon, email, adresse, aktivitetstype, konkurrence);

                    TrainingResult.KonkurrenceSvømmer ks = tr.new KonkurrenceSvømmer(medlem);
                    konkurrenceSvømmere.add(ks);

                    System.out.println("Konkurrencesvømmer oprettet.");
                    break;
                }

                // 2. Tilføj træningsresultat
                case 2: {
                    System.out.println("\n--- Tilføj træningsresultat ---");

                    TrainingResult.KonkurrenceSvømmer ks = vælgSvømmer(input, konkurrenceSvømmere);
                    if (ks == null) {
                        System.out.println("Ingen svømmer valgt.");
                        break;
                    }

                    TrainingResult.Discipline discipline = vælgDisciplin(input);

                    System.out.print("Tid (sekunder, fx 58.23): ");
                    double time = input.nextDouble();
                    input.nextLine();

                    System.out.print("Dato (YYYY-MM-DD): ");
                    String datoStr = input.nextLine();
                    LocalDate dato = LocalDate.parse(datoStr);

                    TrainingResult.Result result = tr.new Result(discipline, time, dato);
                    ks.addTrainingResult(result);

                    System.out.println("Træningsresultat tilføjet.");
                    break;
                }

                // 3. Tilføj konkurrenceresultat
                case 3: {
                    System.out.println("\n--- Tilføj konkurrenceresultat ---");

                    TrainingResult.KonkurrenceSvømmer ks = vælgSvømmer(input, konkurrenceSvømmere);
                    if (ks == null) {
                        System.out.println("Ingen svømmer valgt.");
                        break;
                    }

                    System.out.print("Stævnenavn: ");
                    String eventName = input.nextLine();

                    System.out.print("Placering (tal): ");
                    int placement = input.nextInt();
                    input.nextLine();

                    TrainingResult.Discipline discipline = vælgDisciplin(input);

                    System.out.print("Tid (sekunder, fx 58.23): ");
                    double time = input.nextDouble();
                    input.nextLine();

                    System.out.print("Dato (YYYY-MM-DD): ");
                    String datoStr = input.nextLine();
                    LocalDate dato = LocalDate.parse(datoStr);

                    TrainingResult.CompetitionResult cr =
                            tr.new CompetitionResult(eventName, placement, time, discipline, dato);

                    ks.addCompetitionResult(cr);

                    System.out.println("Konkurrenceresultat tilføjet.");
                    break;
                }

                // 4. Vis svømmere + bedste træningstider
                case 4: {
                    System.out.println("\n--- Oversigt: Konkurrencesvømmere ---");
                    if (konkurrenceSvømmere.isEmpty()) {
                        System.out.println("Ingen konkurrencesvømmere oprettet endnu.");
                        break;
                    }

                    for (TrainingResult.KonkurrenceSvømmer ks : konkurrenceSvømmere) {
                        Medlem m = ks.getMedlem();
                        System.out.println("Svømmer: " + m.getNavn() + " (tlf: " + m.getTelefon() + ")");

                        // Gennemløb alle discipliner og vis bedste tid hvis findes
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

                case 5:
                    kør = false;
                    System.out.println("Træner-menu afsluttes.");
                    break;

                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }

    // Hjælpefunktion: vælg svømmer
    private static TrainingResult.KonkurrenceSvømmer vælgSvømmer(Scanner input, ArrayList<TrainingResult.KonkurrenceSvømmer> liste) {
        if (liste.isEmpty()) { System.out.println("Der er ingen konkurrencesvømmere endnu.");
            return null;
        }
        System.out.println("Vælg svømmer:");

        for (int i = 0; i < liste.size(); i++) {
            Medlem m = liste.get(i).getMedlem();
            System.out.println((i + 1) + ". " + m.getNavn() + " (tlf: " + m.getTelefon() + ")");
        }

        System.out.print("Valg: ");
        int valg = input.nextInt();
        input.nextLine();

        if (valg < 1 || valg > liste.size()) {
            System.out.println("Ugyldigt valg.");
            return null;
        }
        return liste.get(valg - 1);
    }

    // Hjælpefunktion: vælg disciplin
    private static TrainingResult.Discipline vælgDisciplin(Scanner input) {
        System.out.println("Vælg disciplin:");

        TrainingResult.Discipline[] discipliner = TrainingResult.Discipline.values();

        for (int i = 0; i < discipliner.length; i++) {
            System.out.println((i + 1) + ". " + discipliner[i]);
        }

        System.out.print("Valg: ");
        int valg = input.nextInt();
        input.nextLine();

        if (valg < 1 || valg > discipliner.length) {
            System.out.println("Ugyldigt valg – standard CRAWL_100 vælges.");
            return TrainingResult.Discipline.CRAWL_100;
        }
        return discipliner[valg - 1];
    }
}

