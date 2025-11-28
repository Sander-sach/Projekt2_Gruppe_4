public class Medlem {
    //personoplysninger/Medlemstype
    String navn;
    int alder;
    String aktivitetstype;
    boolean kokurrence;

    Medlem(String navn, int alder, String aktivitetstype, boolean kokurrence) {
        this.navn = navn;
        this.alder = alder;
        this.aktivitetstype = aktivitetstype;
        this.kokurrence = kokurrence;
    }

    //return
    String getNavn() {
        return navn;
    }

    int getAlder() {
        return alder;
    }

    String getAktivitetstype() {
        return aktivitetstype;
    }

    boolean Kokurrence() {
        return kokurrence;
    }
    //toString metode til udprintning af medlems liste
    public String toString(){
        return navn +","+ alder +","+ aktivitetstype +","+ (kokurrence ? "Konkurrence" : "Motionist");
    }
    // ToString metode til at gemme medlemmers Booleans som True eller False i .txt dokumenter
    public String saveMedlem(){
        return navn +","+ alder +","+ aktivitetstype +","+ kokurrence;
    }
}
//Under klasse
class svømmeMedlem extends Medlem{
    public svømmeMedlem(String navn, int alder, boolean konkurrence){
        super(navn,alder,"svømning",konkurrence);

    }
}
//main der printer ud for at prøve at se hvordan det ville se ud
class main{
    public static void main(String[] args){

        svømmeMedlem medlem1=new svømmeMedlem("Anna Hansen", 25, true);

        System.out.println(medlem1);
    }
}

