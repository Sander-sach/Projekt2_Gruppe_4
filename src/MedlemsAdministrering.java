import java.util.*;
import java.io.*;
public class MedlemsAdministrering {
    ArrayList<Medlem> medlemmer = new ArrayList<>();
    FilAdministering fil = new FilAdministering();
    KontingentAdministrering kon = new KontingentAdministrering();

    void hentMedlemsListe() throws IOException {
        fil.hentMedlemsFil(medlemmer);
    }

    void registrerMedlem(Medlem m) throws IOException {
        medlemmer.add(m);
        fil.saveMedlemsFil(m);
        fil.saveKonFil(kon.opretKontingent(m));
    }

    Medlem findMedlem(int telefon) throws IOException {
        for (Medlem m : medlemmer) {
            if (m.getTelefon() == telefon) {
                return m;
            }
        }
        return null;
    }

    void sletMedlem(int telefon) throws IOException {
        Medlem m = findMedlem(telefon);
        if (m != null) {
            medlemmer.remove(m);
        }
        fil.opdaterMedlemsFil(medlemmer);
    }

    Boolean tjekNyMedlemsData(Medlem nytMedlem) {
        for (Medlem m : medlemmer) {
            if ((nytMedlem.getTelefon() == (m.getTelefon()))) {
                return false;
            }
        }
        return true;
    }

    void redigerMedlem(Medlem m, Scanner input) throws IOException {
        //SubMenu udskrives
        System.out.println("Rediger Personoplysninger tast 1:\n" +
                "Rediger Betalingsoplysninger tast 2:\n" +
                "Rediger Aktivitets eller Medlemstype tast 3:\n" +
                "Tast: 'Enter' for at skippe\n" +
                "Valg:");
        int valg = input.nextInt();
        input.nextLine();
        switch (valg) {
            //Personoplsyning redigeres
            case 1:
                MedlemsAdministrering.redigerPersonOp(m, input);
                break;
            //Betalingsoplysninger redigeres
            case 2:
                MedlemsAdministrering.redigerBetalingOp(m, input);
                break;
            //Medlemstype redigeres
            case 3:
                MedlemsAdministrering.redigerMedlemsType(m, input);
                break;
            default:
                System.out.println("Ugyldigt valg");
        }
        //ændringer gemmes
        fil.opdaterMedlemsFil(medlemmer);
        System.out.println("Ændringe Gemt!");
    }

// Metode til redigering af personoplysning på medlem
    static void redigerPersonOp(Medlem m, Scanner input) {
        String stast;
        Integer itast;
        //Static ScannerHelp.skipString(input) metode bruges til at modtage consol input
        //ScannerHelp.skipString(input) gør det også muligt at skippe hvis man ikke vil ændre noget.
        System.out.println("Tast navn ændring: ");
        stast = ScannerHelp.skipString(input);
        if (stast == null) {
            System.out.println("Skipped");
        } else m.setNavn(stast);

        System.out.println("Tast telefon ændring: ");
        itast = ScannerHelp.skipInteger(input);
        if (itast == null) {
            System.out.println("Skipped");
        } else m.setTelefon(itast);

        System.out.println("Tast Email ændring: ");
        stast = ScannerHelp.skipString(input);
        if (stast == null) {
            System.out.println("Skipped");
        } else m.setEmail(stast);

        System.out.println("Tast adresse ændring: ");
        stast = ScannerHelp.skipString(input);
        if (stast == null) {
            System.out.println("Skipped");
        } else m.setAdresse(stast);
    }
//Metode til redigering af Betalingsinfo
    static void redigerBetalingOp(Medlem m, Scanner input) {
        int regiNr;
        int kontoNr;

        System.out.println("Tast Registeringsnummer: ");
        regiNr = ScannerHelp.checkInputInt(input);
        m.setRegiNr(regiNr);
        System.out.println("Tast Kontonummer: ");
        kontoNr = ScannerHelp.checkInputInt(input);
        m.setKontoNr(kontoNr);
        input.nextLine();
    }
// Metode til redigering af medlemstype og aktivitetsype
    static void redigerMedlemsType(Medlem m, Scanner input) {
        Boolean konkurrence;
        String aktivitetstype;

        System.out.println("Tast Aktivitetstype(Aktivt/Passivt): ");
        aktivitetstype = input.next();
        m.setAktivitetstype(aktivitetstype);
        //If() tjekker om medlem skal være konkurrence/montionist medlem eller passivt
        if (aktivitetstype.equalsIgnoreCase("aktivt")) {
            System.out.println("Vælg Konkurrence eller Motionistmedlem(true/false): ");
            konkurrence = input.nextBoolean();
            m.setKonkurrence(konkurrence);
        } else {
            konkurrence = null;
            m.setKonkurrence(konkurrence);
        }
        input.nextLine();
    }

    public static void main(String[] arg) {
        PersonInfo p1 = new PersonInfo("Caper", 45, 23884731, "sander@castagna.dk", "allegade27");
        BetalingsInfo b1 = new BetalingsInfo(4567, 2345765);
        Medlem nytMedlem = new Medlem(p1, "akitvt", true, b1);
    }
}


