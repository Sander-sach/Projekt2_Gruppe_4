import java.util.*;
import java.io.*;
public class FilAdministering {
   private FileReader filr;
   private BufferedReader ind;
   ArrayList<Medlem> mList = new ArrayList<>();
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
            String navn = medlem[0];
            int alder = Integer.parseInt(medlem[1]);
            String aktivitetstype = medlem[2];
            boolean konkurrence = Boolean.parseBoolean(medlem[3]);
           //Medlem bliver oprettet som objekt og tilføjet en ArrayList
            Medlem m = new Medlem(navn, alder, aktivitetstype, konkurrence);
            System.out.println(m);
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
            if ((sm.saveMedlem().equals(m.saveMedlem()))) {
                System.out.println("Medlem eksistere allerede!");
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
        Medlem m1=new Medlem("Caper",45,"Passivt",true);
        FilAdministering fa1=new FilAdministering();
        fa1.getMedlemsData();
        fa1.saveMedlemsData(m1);

        }//main()
    }//Class FilAdministering
