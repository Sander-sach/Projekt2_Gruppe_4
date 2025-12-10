import java.util.*;
import java.io.*;

public class KontingentAdministrering {
    ArrayList<KontingentInfo> kontingent = new ArrayList<>();
    FilAdministering fil = new FilAdministering();

    // opretter et nyt medlems Kontingent og tilføjer dem til betalings listen
    public KontingentInfo opretKontingent(Medlem m){
        double kon = KontingentInfo.beregnKontingent(m);
        int regiNr = m.getRegiNr();
        int kontoNr = m.getKontoNr();
        boolean harBetalt = true;
        KontingentInfo k = new KontingentInfo(regiNr, kontoNr, kon, harBetalt);
        kontingent.add(k);
        return k;
    }

    public KontingentInfo findKontingent(int regiNr, int kontoNr){
        for (KontingentInfo k : kontingent){
            if (k.getReNr() == regiNr && k.getKoNr() == kontoNr){
                return k;
            }
        }
        return null;
    }

    public KontingentInfo findKontingent(Medlem m){
        return findKontingent(m.getRegiNr(), m.getKontoNr());
    }

    public void hentKontingentListe() throws IOException {
        fil.hentKonFil(kontingent);
    }

    public void printKontigentListe(){
        for (KontingentInfo k : kontingent){
            System.out.println(k);
        }
    }

    public void redigerBetaling(KontingentInfo k, Scanner input) throws IOException {
        boolean harBetalt;
        System.out.println("Er Kontingent betalt: true/false \nValg:");
        harBetalt = input.nextBoolean();
        k.setHarBetalt(harBetalt);
        input.nextLine();
        System.out.println("Ændring gemt!");
        fil.opdaterKonFil(kontingent);
    }
}

