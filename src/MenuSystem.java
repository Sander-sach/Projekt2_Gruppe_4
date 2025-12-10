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

    static final String KODE_TRAENER  = "Traener1";
    static final String KODE_FORMAND  = "Formand1";
    static final String KODE_KASSERER = "Kassere1";

    // til trænerdelen
    static TrainingResult trainingResult = new TrainingResult();
    static ArrayList<TrainingResult.KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();

    // til kasserer-delen (kontingenter)
    static KontingentAdministrering kontAdmin = new KontingentAdministrering();

    // ====================
    // LOGIN
    // ====================
    static boolean erGyldigKode(String kode) {
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

    static Rolle fåRolleFraKode(String kode) {
        if (!erGyldigKode(kode)) return null;
        if (kode.equals(KODE_TRAENER))  return Rolle.TRAENER;
        if (kode.equals(KODE_FORMAND))  return Rolle.FORMAND;
        if (kode.equals(KODE_KASSERER)) return Rolle.KASSERER;
        return null;
    }

    static Rolle loginMenu(Scanner input) {
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
    static void traenerMenu(Scanner input, Rolle rolle) {

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
    // KASSERER-MENU (kontingent)
    // ====================
    static void kassererMenu(Scanner input, Rolle rolle) {

        if (!(rolle == Rolle.KASSERER || rolle == Rolle.FORMAND)) {
            System.out.println("Kun KASSERER eller FORMAND har adgang til kasserer-menuen.");
            return;
        }

        boolean kør = true;

        while (kør) {
            System.out.println("\n--- KASSERER MENU ---");
            System.out.println("1. Hent kontingentliste fra fil");
            System.out.println("2. Vis alle kontingenter");
            System.out.println("3. Rediger betaling for medlem");
            System.out.println("4. Tilbage");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {
                case 1: {
                    try {
                        kontAdmin.hentKontingentListe();
                        System.out.println("Kontingentliste hentet fra fil.");
                    } catch (IOException e) {
                        System.out.println("Fejl ved indlæsning af kontingentfil: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    System.out.println("\n--- Kontingentliste ---");
                    kontAdmin.printKontigentListe();
                    break;
                }

                case 3: {
                    try {
                        System.out.print("Registreringsnummer: ");
                        int regiNr = Integer.parseInt(input.nextLine());

                        System.out.print("Kontonummer: ");
                        int kontoNr = Integer.parseInt(input.nextLine());

                        KontingentInfo k = kontAdmin.findKontingent(regiNr, kontoNr);

                        if (k == null) {
                            System.out.println("Ingen kontingent fundet for den konto.");
                            break;
                        }

                        System.out.println("Fundet kontingent:");
                        System.out.println(k);

                        kontAdmin.redigerBetaling(k, input);

                    } catch (NumberFormatException e) {
                        System.out.println("Du skal indtaste tal for regi- og kontonummer.");
                    } catch (IOException e) {
                        System.out.println("Fejl ved opdatering af kontingentfil: " + e.getMessage());
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

        Scanner input = new Scanner(System.in);
        MedlemsAdministrering admin = new MedlemsAdministrering();

        try {
            admin.hentMedlemsListe();
        } catch (IOException e) {
            System.out.println("Kunne ikke hente medlemsdata: " + e.getMessage());
        }

        Rolle rolle = loginMenu(input);
        if (rolle == null) return;

        boolean kør = true;

        while (kør) {
            System.out.println("\n--- HOVEDMENU ---");
            System.out.println("1. Registrer medlem");
            System.out.println("2. Søg medlem (telefon)");
            System.out.println("3. Slet medlem (telefon) [kun FORMAND]");
            System.out.println("4. Rediger medlem");
            System.out.println("5. Træner-menu (konkurrencesvømmere)");
            System.out.println("6. Kasserer-menu (kontingenter)");
            System.out.println("7. Afslut");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine();

            try {
                switch (valg) {

                    case 1: // registrer
                        System.out.print("Navn: ");
                        String navn = input.nextLine();

                        System.out.print("Telefon: ");
                        int telefon = input.nextInt();
                        input.nextLine();

                        System.out.print("Email: ");
                        String email = input.nextLine();

                        System.out.print("Adresse: ");
                        String adresse = input.nextLine();

                        System.out.print("Aktivitetstype (Aktivt/Passivt): ");
                        String aktivitetstype = input.nextLine();

                        Boolean konkurrence = null;
                        if (aktivitetstype.equalsIgnoreCase("aktivt")) {
                            System.out.print("Konkurrencemedlem? (true/false): ");
                            konkurrence = input.nextBoolean();
                            input.nextLine();
                        }

                        Medlem nyt = new Medlem(navn, telefon, email, adresse, aktivitetstype, konkurrence);
                        boolean tjek = admin.tjekNyMedlemsData(nyt);
                        if (tjek != false) {
                            admin.registrerMedlem(nyt);
                            System.out.println("Medlem registreret!");
                        }
                        break;

                    case 2: // find
                        System.out.print("Indtast telefonnummer: ");
                        int søgTlf = input.nextInt();
                        input.nextLine();

                        Medlem fundet = admin.findMedlem(søgTlf);
                        if (fundet != null) {
                            System.out.println(fundet);
                        } else {
                            System.out.println("Ingen medlem med det telefonnummer.");
                        }
                        break;

                    case 3: // slet (kun formand)
                        if (rolle != Rolle.FORMAND) {
                            System.out.println("Kun FORMAND må slette medlemmer.");
                            break;
                        }
                        System.out.print("Telefonnummer på medlem der skal slettes: ");
                        int sletTlf = input.nextInt();
                        input.nextLine();

                        admin.sletMedlem(sletTlf);
                        System.out.println("Sletning forsøgt (hvis medlemmet fandtes, er det nu slettet).");
                        break;

                    case 4: // rediger
                        System.out.print("Telefonnummer på medlem der skal redigeres: ");
                        int redigTlf = input.nextInt();
                        input.nextLine();

                        Medlem redigMedlem = admin.findMedlem(redigTlf);
                        if (redigMedlem != null) {
                            admin.redigerMedlem(redigMedlem, input);
                        } else {
                            System.out.println("Ingen medlem med det telefonnummer.");
                        }
                        break;

                    case 5: // TRÆNER-MENU
                        traenerMenu(input, rolle);
                        break;

                    case 6: // KASSERER-MENU
                        kassererMenu(input, rolle);
                        break;

                    case 7:
                        kør = false;
                        System.out.println("Programmet afsluttes.");
                        break;

                    default:
                        System.out.println("Ugyldigt valg.");
                }

            } catch (IOException e) {
                System.out.println("Fejl ved filhåndtering: " + e.getMessage());
            }
        }
    }
}

