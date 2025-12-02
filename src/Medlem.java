public class Medlem {
    //personoplysninger/Medlemstype
    private PersonInfo personinfo;
    private BetalingsInfo betalingsinfo;
    String aktivitetstype;
    boolean konkurrence;

    Medlem(PersonInfo personin, String aktivitetstype, boolean konkurrence,BetalingsInfo betalingsin) {
        this.personinfo = personin;
        this.betalingsinfo=betalingsin;
        this.aktivitetstype = aktivitetstype;
        this.konkurrence = konkurrence;

    }

    //return
   public String getNavn() {
        return personinfo.getNavnPer();
    }
    public String getEmail(){
        return personinfo.getEmailPer();
    }
    public String getAdresse(){
        return personinfo.getAdressePer();
    }
    public int getTelefon(){
        return personinfo.getTelefonPer();
    }
    public int getAlder(){
        return personinfo.getAlderPer();
    }

    String getAktivitetstype() {
        return aktivitetstype;
    }

    boolean getKonkurrence() {
        return konkurrence;
    }
    //toString metode til udprintning af medlems liste
    public String toString(){
        return personinfo.getPersonInfo()+
                ","+ aktivitetstype+
                ","+(konkurrence ? "Konkurrence" : "Motionist")+
                ","+betalingsinfo.getBetalingsInfo();
    }
    // ToString metode til at gemme medlemmers Booleans som True eller False i .txt dokumenter
    public String saveMedlem(){
        return personinfo.getPersonInfo()+
                ","+aktivitetstype+
                ","+konkurrence+
                ","+betalingsinfo.getBetalingsInfo();
    }

    public String verificerMedlem(){
        return getTelefon()+getEmail();
    }
}
//Under klasse
class svømmeMedlem extends Medlem{
    public svømmeMedlem(PersonInfo personinfo, boolean konkurrence,BetalingsInfo betalingsinfo ){
        super(personinfo,"svømning",konkurrence,betalingsinfo);

    }
}

//main der printer ud for at prøve at se hvordan det ville se ud
class main{
    public static void main(String[] args){


    }
}

