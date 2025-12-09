import java.util.*;
import java.io.*;
public class MedlemsAdministrering {
    ArrayList<Medlem> medlemmer=new ArrayList<>();
    FilAdministering fil=new FilAdministering();

    void hentMedlemsListe()throws IOException{
        fil.hentMedlemsData(medlemmer);
    }

        void registrerMedlem(Medlem m)throws IOException {
            medlemmer.add(m);
            fil.saveMedlemsData(m);
        }

        Medlem findMedlem(int telefon)throws IOException {
            for (Medlem m : medlemmer) {
                if (m.getTelefon()==telefon) {
                    return m;
                }
            }
            return null;
        }

        void sletMedlem(int telefon)throws IOException {
            Medlem m = findMedlem(telefon);
            if (m != null) {
                medlemmer.remove(m);
            }
            fil.opdaterMedlemsData(medlemmer);
        }
        Medlem tjekNyMedlemsData(Medlem nytMedlem) {
            for (Medlem m : medlemmer) {
                if ((nytMedlem.verificerMedlem().equals(m.verificerMedlem()))) {
                    System.out.println("Medlems Telefonnummer er allerede i systemet!");
                    return null;
                }else return nytMedlem;
            }return null;
        }

        void redigerMedlem(Medlem m,Scanner input)throws IOException{
        //SubMenu udskrives
        System.out.println("Rediger Personoplysninger tast 1:\n" +
                        "Rediger Betalingsoplysninger tast 2:\n" +
                        "Rediger Aktivitets eller Medlemstype tast 3:\n" +
                "Tast: 'Enter' for at skippe\n" +
                "Valg:");
            int valg=input.nextInt();
            input.nextLine();
        switch(valg){
            //Personoplsyning redigeres
            case 1: MedlemsAdministrering.redigerPersonOp(m,input);
            break;
            //Betalingsoplysninger redigeres
            case 2: MedlemsAdministrering.redigerBetalingOp(m,input);
            break;
            //Medlemstype redigeres
            case 3: MedlemsAdministrering.redigerMedlemsType(m,input);
            break;
            default: System.out.println("Ugyldigt valg");
        }
        //ændringer gemmes
            fil.opdaterMedlemsData(medlemmer);
            System.out.println("Ændringe Gemt!");
    }

    static void redigerPersonOp(Medlem m,Scanner input){
        String stast;
        Integer itast;
        //Static ScannerHelp metode bliver kaldt på alle oplsyninger bruge vil ændre
        //ScannerHelp metode gør det muligt at skippe hvis man ikke vil ændre noget.
        System.out.println("Tast navn ændring: ");
        stast=ScannerHelp.skipString(input);
        if(stast==null) {System.out.println("Skipped");
        }else m.setNavn(stast);

        System.out.println("Tast telefon ændring: ");
        itast=ScannerHelp.skipInteger(input);
        if(itast==null) {System.out.println("Skipped");
        }else m.setTelefon(itast);

        System.out.println("Tast Email ændring: ");
        stast=ScannerHelp.skipString(input);
        if(stast==null) {System.out.println("Skipped");
        }else m.setEmail(stast);

        System.out.println("Tast adresse ændring: ");
        stast=ScannerHelp.skipString(input);
        if(stast==null) {System.out.println("Skipped");
        }else m.setAdresse(stast);
    }

    static void redigerBetalingOp(Medlem m,Scanner input){
        int regiNr;
        int kontoNr;

        System.out.println("Tast Registeringsnummer: ");
        regiNr=input.nextInt();
        m.setRegiNr(regiNr);
        System.out.println("Tast Kontonummer: ");
        kontoNr=input.nextInt();
        m.setKontoNr(kontoNr);
        input.nextLine();
    }

    static void redigerMedlemsType(Medlem m,Scanner input){
        Boolean konkurrence;
        String aktivitetstype;

        System.out.println("Tast Aktivitetstype(Aktivt/Passivt): ");
        aktivitetstype=input.next();
        m.setAktivitetstype(aktivitetstype);
        if(aktivitetstype.equalsIgnoreCase("aktivt")){
            System.out.println("Vælg Konkurrence eller Motionistmedlem(true/false): ");
            konkurrence=input.nextBoolean();
            m.setKonkurrence(konkurrence);
        }else {
            konkurrence=null;
            m.setKonkurrence(konkurrence);}
        input.nextLine();
    }
}


