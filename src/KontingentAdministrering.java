import java.util.*;
import java.io.*;
public class KontingentAdministrering {
    ArrayList<KontingentInfo> kontingent =new ArrayList();
    FilAdministering fil = new FilAdministering();

    //opretter et nyt medlems Kontingent og tilføjer dem til betalings listen
    public KontingentInfo opretKontingent(Medlem m){
        double kon=KontingentInfo.beregnKontingent(m);
         int regiNr=m.getRegiNr();
         int kontoNr=m.getKontoNr();
         boolean harBetalt=true;
         KontingentInfo k=new KontingentInfo(regiNr,kontoNr,kon,harBetalt);
         return k;
    }
    KontingentInfo findKontingent(Medlem m){
        int regiNr=m.getRegiNr();
        int kontoNr=m.getKontoNr();
        for(KontingentInfo k:kontingent){
            if(k.getReNr()==regiNr && k.getKoNr()==kontoNr){
            }return k;
        }return null;
    }
    void hentKontingentListe() throws IOException {
        fil.hentKonFil(kontingent);
    }
    void printKontigentListe(ArrayList<KontingentInfo> kontingent){
        for(KontingentInfo k:kontingent){
            System.out.println(k);
        }
    }
    void redigerBetaling(KontingentInfo k,Scanner input)throws IOException{
        boolean harBetalt;
        System.out.println("Er Kontingent betalt: true/false \n" +
                "Valg:");
        harBetalt=input.nextBoolean();
        k.setHarBetalt(harBetalt);
        input.nextLine();
        System.out.println("Ændring gemt!");
        fil.opdaterKonFil(kontingent);
    }


}
