import javax.xml.transform.Result;
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
    KonkurrenceAdministrering ka=new KonkurrenceAdministrering();
    TrainingResult Result = new TrainingResult();
     ArrayList<KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();
    // til kasserer-delen (kontingenter)
     KontingentAdministrering kontAdmin = new KontingentAdministrering();
    // til Medlemmer
    ArrayList<Medlem> medlemmer = new ArrayList<>();
    MedlemsAdministrering admin = new MedlemsAdministrering();


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
     boolean traenerMenu(Scanner input)throws IOException {
         admin.hentMedlemsListe();
         boolean kør = true;
        while (kør) {
            System.out.println("\n--- TRÆNER MENU ---");
            System.out.println("1. Vis Konkurrence Hold");
            System.out.println("2. Tilføj svømmeresultat");
            System.out.println("3. Vis svømmere + bedste svømmeresultater");
            System.out.println("4. Afslut");
            System.out.print("Vælg: ");


            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {

                case 1: ka.hentKonkurrenceMedlemmer(admin.ExportMedlemsListe());
                    ka.sorterKonkurrenceHold();


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

                    KonkurrenceSvømmer ks = konkurrenceSvømmere.get(sValg - 1);

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

                    TrainingResult tr = new TrainingResult();
                    TrainingResult.Result r = tr.new Result(d, tid, dato);
                    ks.addTrainingResult(r);

                    System.out.println("Svømmeresultat tilføjet.");
                    break;
                }

                case 3: { // vis bedste tider
                    if (konkurrenceSvømmere.isEmpty()) {
                        System.out.println("Ingen konkurrencesvømmere endnu.");
                        break;
                    }

                    System.out.println("--- Oversigt ---");
                    for (KonkurrenceSvømmer ks : konkurrenceSvømmere) {
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
        } return false;
    }

    // ====================
    // KASSERER-MENU (kontingent)
    // ====================
     boolean kassererMenu(Scanner input) {

        // Hent kontingenter automatisk én gang, når man går ind i menuen
        try {
            kontAdmin.hentKontingentListe();
            System.out.println("Kontingentliste hentet fra fil.");
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af kontingentfil: " + e.getMessage());
            return false;// kan ikke lave kasserer-menu uden data
        }

        boolean kør = true;

        while (kør) {
            System.out.println("\n--- KASSERER MENU ---");
            System.out.println("1. Vis alle kontingenter");
            System.out.println("2. Rediger betaling for medlem");
            System.out.println("3. Afslut");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {
                case 1: {
                    System.out.println("\n--- Kontingentliste ---");
                    kontAdmin.printKontigentListe();
                    break;
                }

                case 2: {
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

                case 3:
                    kør = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg.");
            }
        }return false;
    }
    boolean formandMenu(Scanner input) {
        try {
            admin.hentMedlemsListe();
        } catch (IOException e) {
            System.out.println("Kunne ikke hente medlemsdata: " + e.getMessage());
        }
        boolean kør = true;

        while (kør) {
            System.out.println("\n--- FORMANDS MENU ---");
            System.out.println("1. Registrer medlem");
            System.out.println("2. Søg og rediger medlem (telefon)");
            System.out.println("3. Slet medlem (telefon)");
            System.out.println("4. Afslut");
            System.out.print("Vælg: ");

            int valg = input.nextInt();
            input.nextLine();

            try {
                switch (valg) {

                    case 1: // registrer
                        System.out.print("Navn: ");
                        String navn = input.nextLine();

                        System.out.print("Alder: ");
                        int alder = ScannerHelp.checkInputInt(input);
                        input.nextLine();

                        System.out.print("Telefon nummer: ");
                        int telefon = ScannerHelp.checkInputInt(input);
                        input.nextLine();

                        System.out.print("Email: ");
                        String email = input.nextLine();


                        System.out.print("Adresse: ");
                        String adresse = input.nextLine();


                        System.out.print("Passivt eller Aktivt?: ");
                        String aktivtype = ScannerHelp.checkInputType(input);
                        input.nextLine();
                        Boolean konk = null;

                        if (aktivtype.equalsIgnoreCase("aktivt")) {
                            System.out.print("Er konkurrence (true/false): ");
                            konk = input.nextBoolean();
                            input.nextLine();
                        }

                        System.out.print("\n\t---Betalings Oplysninger---\n\n" +
                                "Registreringsnummer:");
                        int regiNr = ScannerHelp.checkInputInt(input);
                        input.nextLine();

                        System.out.print("Kontonummer: ");
                        int kontoNr = ScannerHelp.checkInputInt(input);
                        input.nextLine();

                        PersonInfo p = new PersonInfo(navn, alder, telefon, email, adresse);
                        BetalingsInfo b = new BetalingsInfo(regiNr, kontoNr);
                        Medlem m = new Medlem(p, aktivtype, konk, b);

                        if (admin.tjekNyMedlemsData(m)) {
                            admin.registrerMedlem(m);
                            System.out.println("Medlem tilføjet!");
                        } else System.out.println("Medlems telefonnummer er allerede registeret");
                        break;

                    case 2: // find
                        System.out.print("Indtast Telefonnummer: ");
                        int rediger;
                        int søgMedlem = ScannerHelp.checkInputInt(input);
                        Medlem fundet = admin.findMedlem(søgMedlem);

                        if (fundet != null) {
                            System.out.println(fundet);
                            System.out.println("\nRediger medlemsoplysninger tast 1:\n" +
                                    "Tilbage til Hovedmenu tast 2");
                            rediger = ScannerHelp.checkInputInt(input);
                            if (rediger == 1) admin.redigerMedlem(fundet, input);

                        } else System.out.println("Ikke fundet.");
                        break;

                    case 3: // slet (kun formand)
                        System.out.print("Telefonnummer på medlem der skal slettes: ");
                        int sletTlf = input.nextInt();
                        input.nextLine();

                        admin.sletMedlem(sletTlf);
                        System.out.println("Sletning forsøgt (hvis medlemmet fandtes, er det nu slettet).");
                        break;

                    case 4:
                        kør = false;
                        System.out.println("Programmet afsluttes.");
                        break;

                    default:
                        System.out.println("Ugyldigt valg.");
                }

            } catch (IOException e) {
                System.out.println("Fejl ved filhåndtering: " + e.getMessage());
            }
        }return false;
    }



    // ====================
    // HOVEDMENU (medlemmer)
    // ====================
    public static void main(String[] args)throws IOException {

    }
}

