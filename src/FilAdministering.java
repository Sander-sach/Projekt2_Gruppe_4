import java.util.*;
import java.io.*;
public class FilAdministering {
   private FileReader filr;
   private BufferedReader ind;
   static ArrayList<Medlem> mList = new ArrayList<>();
   private FileWriter filw;
   private BufferedWriter ud;

    //Henter listen af medlemmer til en ArrayList af dem
    public void getMedlemsData() throws IOException {
        filr = new FileReader("src//Medlemmer.txt");
        ind = new BufferedReader(filr);
        String linje;

        //Looper igennem medlemmer i Filen og opretter et objekt til hvert medlem med deres info
        while ((linje = ind.readLine()) != null) {
            String[] medlem = linje.split(",");

            // første inlæses Person info på kunden
            String navn = medlem[0];
            int alder = Integer.parseInt(medlem[1]);
            int telefon = Integer.parseInt(medlem[2]);
            String email = medlem[3];
            String adresse = medlem[4];

            //Derefter indlæses medlemstypen
            String aktivitetstype = medlem[5];
            boolean konkurrence = Boolean.parseBoolean(medlem[6]);

            // til sidste indlæses betalingsoplysninger:
            int regiNr = Integer.parseInt(medlem[7]);
            int kontoNr = Integer.parseInt(medlem[8]);

            // oprettels af objekt til betalingsinfo og personinfo:
            BetalingsInfo bet=new BetalingsInfo(regiNr,kontoNr);
            PersonInfo per=new PersonInfo(navn,alder,telefon,email,adresse);
           //Medlem bliver oprettet som objekt og tilføjet en ArrayList
            Medlem m = new Medlem(per,aktivitetstype, konkurrence,bet);
            mList.add(m);
        }
    }

    // Gemmer nye medlemmer i filen
    public void saveMedlemsData(Medlem sm) throws IOException {
        //Henter Data på alle eksiterende medlemmer
        FilAdministering f1 = new FilAdministering();
        f1.getMedlemsData();
        boolean noMatch=true;
        //Loope Kontrollere om nye medlems data allerede eksitere
        for (Medlem m : mList) {
            if ((sm.verificerMedlem().equals(m.verificerMedlem()))) {
                System.out.println("Medlems info eksistere allerede!");
                noMatch = false;
                break;
            }
        }
        //Tilføjer nye medlem til filen hvis de ikke allerede er der
        if (noMatch) {
            filw = new FileWriter("src//Medlemmer.txt", true);
            ud = new BufferedWriter(filw);
            ud.write(sm.saveMedlem());
            ud.close();
        }
    }


    //For Testing
    public static void main(String[] arg) throws IOException {
        PersonInfo p1=new PersonInfo("Caper",45,23884738,"sander@castagna.dk","allegade27");
        BetalingsInfo b1=new BetalingsInfo(4567,2345765);
        Medlem m1=new Medlem(p1,"passivt",true,b1);
        FilAdministering fa1=new FilAdministering();
        //fa1.getMedlemsData();
        fa1.saveMedlemsData(m1);



        }//main()
    }//Class FilAdministering
