import java.util.*;
import java.io.*;
public class FilAdministering {
   private FileReader filr;
   private BufferedReader ind;
   private FileWriter filw;
   private BufferedWriter ud;

    //Henter listen af medlemmer til en ArrayList af dem
    void hentMedlemsData(ArrayList<Medlem> medlemmer) throws IOException {
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
            Boolean konkurrence = medlem[6].equals("#")? null: Boolean.parseBoolean(medlem[6]);

            // til sidste indlæses betalingsoplysninger:
            int regiNr = Integer.parseInt(medlem[7]);
            int kontoNr = Integer.parseInt(medlem[8]);

            // oprettels af objekt til betalingsinfo og personinfo:
            BetalingsInfo bet=new BetalingsInfo(regiNr,kontoNr);
            PersonInfo per=new PersonInfo(navn,alder,telefon,email,adresse);
           //Medlem bliver oprettet som objekt og tilføjet en ArrayList
            Medlem m = new Medlem(per,aktivitetstype, konkurrence,bet);
            medlemmer.add(m);
        }
    }

    // Gemmer nye medlemmer i filen
    public void saveMedlemsData(Medlem sm) throws IOException {
        //Tilføjer nye medlem til filen
        filw = new FileWriter("src//Medlemmer.txt", true);
        ud = new BufferedWriter(filw);
        ud.write(sm.saveMedlem());
        ud.close();
    }

    // håndtere redigering af medlemmer og opdatere txt fil med ændringer:
    public void opdaterMedlemsData(ArrayList<Medlem> list)throws IOException{
        filw = new FileWriter("src//Medlemmer.txt");
        ud = new BufferedWriter(filw);
        for(Medlem m:list){
            ud.write(m.saveMedlem()+"\n");
        }
        ud.close();
    }


    //For Testing
    public static void main(String[] arg) throws IOException {
        PersonInfo p1=new PersonInfo("Caper",45,23884738,"sander@castagna.dk","allegade27");
        BetalingsInfo b1=new BetalingsInfo(4567,2345765);
        Medlem m1=new Medlem(p1,"passivt",true,b1);
        FilAdministering fa1=new FilAdministering();
        //fa1.getMedlemsData();
        //fa1.saveMedlemsData(m1);
        //fa1.opdaterMedlemsData();



        }//main()
    }//Class FilAdministering
