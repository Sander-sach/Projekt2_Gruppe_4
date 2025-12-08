import java.util.*;
import java.io.*;
public class MainMenu {
    Scanner input = new Scanner(System.in);
    MedlemsAdministrering admin = new MedlemsAdministrering();
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

            int valg = input.nextInt();
            input.nextLine(); // fjerner newline

            switch (valg) {

                case 1: // Tilføj medlem
                    System.out.print("Navn: ");
                    String navn = input.nextLine();

                    System.out.print("Alder: ");
                    int alder = input.nextInt();
                    input.nextLine();

                    System.out.print("Telefon nummer: ");
                    int telefon = input.nextInt();

                    input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();

                    System.out.print("Adresse: ");
                    String adresse = input.nextLine();

                    System.out.print("Passivt eller Aktivt?: ");
                    String aktivtype = input.nextLine();
                    Boolean konk = null;

                    if(aktivtype.equalsIgnoreCase("aktivt")){
                    System.out.print("Er konkurrence (true/false): ");
                        konk = input.nextBoolean();
                        input.nextLine();
                    }

                    System.out.print("Betalings information:\n" +
                            "Registreringsnummer:");
                    int regiNr = input.nextInt();
                    input.nextLine();

                    System.out.print("Kontonummer: ");
                    int kontoNr = input.nextInt();
                    input.nextLine();

                    PersonInfo p = new PersonInfo(navn, alder, telefon, email, adresse);
                    BetalingsInfo b = new BetalingsInfo(regiNr, kontoNr);
                    Medlem m = new Medlem(p, aktivtype,konk, b);

                    if(m.equals(admin.tjekNyMedlemsData(m))){
                        admin.registrerMedlem(m);
                        System.out.println("Medlem tilføjet!");
                    }else System.out.println("Medlems telefonnummer er allerede registeret");
                    break;

                case 2: // Søg medlem og rediger medlem
                    System.out.print("Indtast Telefonnummer: ");
                    int rediger;
                    int søgMedlem = input.nextInt();
                    Medlem fundet = admin.findMedlem(søgMedlem);

                    if (fundet != null) {
                        System.out.println(fundet);
                        System.out.println("Vil du gerne redigere medlemsoplysninger tast 1:");
                        rediger=input.nextInt();
                        if(rediger==1) admin.redigerMedlem(fundet,input);

                    } else System.out.println("Ikke fundet.");
                    break;

                case 3: // Slet medlem med kode
                    System.out.print("Indtast kode: ");
                    String kode = input.nextLine();

                    if (!kode.equals("1234")) {
                        System.out.println("Forkert kode!");
                        break;
                    }

                    System.out.print("Telefonnummer på medlem der skal slettes: ");
                    int sletTlf = input.nextInt();

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


