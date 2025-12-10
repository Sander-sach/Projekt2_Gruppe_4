import java.util.*;
import java.io.*;
public class MainMenu {
    Scanner input = new Scanner(System.in);
    MedlemsAdministrering admin = new MedlemsAdministrering();
    KontingentAdministrering kon = new KontingentAdministrering();
    boolean run = true;

void Menu()throws IOException{
    admin.hentMedlemsListe();
        while (run) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Tilføj medlem");
            System.out.println("2. Søg efter og rediger medlemmer");
            System.out.println("3. Slet medlem (kræver kode)");
            System.out.println("4. Afslut");
            System.out.print("Vælg: ");


            int valg = ScannerHelp.checkInputInt(input);
            input.nextLine(); // fjerner newline

            switch (valg) {

                case 1: // Tilføj medlem
                    System.out.print("Navn: ");
                    String navn = input.next();
                    input.nextLine();

                    System.out.print("Alder: ");
                    int alder = ScannerHelp.checkInputInt(input);
                    input.nextLine();

                    System.out.print("Telefon nummer: ");
                    int telefon = ScannerHelp.checkInputInt(input);
                    input.nextLine();

                    System.out.print("Email: ");
                    String email = input.next();
                    input.nextLine();

                    System.out.print("Adresse: ");
                    String adresse = input.next();
                    input.nextLine();

                    System.out.print("Passivt eller Aktivt?: ");
                    String aktivtype=ScannerHelp.checkInputType(input);
                    input.nextLine();
                    Boolean konk = null;

                    if(aktivtype.equalsIgnoreCase("aktivt")){
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
                    Medlem m = new Medlem(p, aktivtype,konk, b);

                    if(admin.tjekNyMedlemsData(m)){
                        admin.registrerMedlem(m);
                        System.out.println("Medlem tilføjet!");
                    }else System.out.println("Medlems telefonnummer er allerede registeret");
                    break;

                case 2: // Søg medlem og rediger medlem
                    System.out.print("Indtast Telefonnummer: ");
                    int rediger;
                    int søgMedlem = ScannerHelp.checkInputInt(input);
                    Medlem fundet = admin.findMedlem(søgMedlem);

                    if (fundet != null) {
                        System.out.println(fundet);
                        System.out.println("\nRediger medlemsoplysninger tast 1:\n" +
                                "Tilbage til Hovedmenu tast 2");
                        rediger=ScannerHelp.checkInputInt(input);
                        if(rediger==1) admin.redigerMedlem(fundet,input);

                    } else System.out.println("Ikke fundet.");
                    break;

                case 3: // Slet medlem med kode
                    System.out.print("Indtast kode: ");
                    String kode = input.next();

                    if (!kode.equals("1234")) {
                        System.out.println("Forkert kode!");
                        break;
                    }

                    System.out.print("Telefonnummer på medlem der skal slettes: ");
                    int sletTlf = ScannerHelp.checkInputInt(input);

                    admin.sletMedlem(sletTlf);

                    System.out.println("Sletning forsøgt (hvis medlemmet fandtes, er det nu slettet).");
                    break;

                case 4: // Afslut
                    run = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg.");
            }
        }
    }
    public static void main(String[] args)throws IOException {
    MainMenu m=new MainMenu();
    m.Menu();


    }
}


