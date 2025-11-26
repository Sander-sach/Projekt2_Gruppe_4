import java.util.*;
import java.io.*;
public class FilAdministering {
FileReader filr;
BufferedReader ind;
ArrayList<Medlem> mList=new ArrayList<>();
FileWriter filw;
BufferedWriter ud;

//Henter listen af medlemmer til en ArrayList af dem
public void getMedlemsData()throws IOException{
    filr=new FileReader("src//Medlemmer.txt");
    ind=new BufferedReader(filr);
     String navn;
     //Looper igennem medlemmer i Filen og opretter et objekt til hvert medlem med deres info
     while((navn=ind.readLine())!=null){
         String adresse =ind.readLine();
         String alder =ind.readLine();
         //System.out.println er til test:
         System.out.println(navn+adresse+alder);
         Medlem m=new Medlem(navn,adresse,alder);
         mList.add(m);
    }
}

// Gemmer nye medlemmer i filen
public void saveMedlemsData(Medlem sm)throws IOException {
    //Henter Data på alle eksiterende medlemmer
    FilAdministering f1 = new FilAdministering();
    f1.getMedlemsData();
    boolean match;
    //Loope Kontrollere om nye medlems data allerede eksitere
    for (Medlem m : mList) {
        if ((sm.toString().equals(m.toString()))) {
            System.out.println("Medlem eksistere allerede!");
            match = true;
            break;}
    }
    //Tilføjer nye medlem til filen hvis de ikke allerede er der
    if(match=false){
        filw=new FileWriter("src//Medlemmer.txt",true);
        ud=new BufferedWriter(filw);
        ud.write(sm.toString());ud.close();}
}



    //For Testing
    public static void main(String[]arg)throws IOException{

    Medlem m3=new Medlem("Casper","kongegade","45");
    FilAdministering fa1=new FilAdministering();
    fa1.getMedlemsData();
    fa1.saveMedlemsData(m3);

    }//main()
}//Class FilAdministering
