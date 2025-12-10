public class BetalingsInfo {

    private int regiBet;
    private int kontoBet;

    // Tom constructor (bruges i Medlem)
    public BetalingsInfo() {
    }

    // Constructor der matcher FilAdministering:
    // new BetalingsInfo(regiNr, kontoNr)
    public BetalingsInfo(int regiBet, int kontoBet) {
        this.regiBet = regiBet;
        this.kontoBet = kontoBet;
    }

    public int getRegiNrBet(){
        return regiBet;
    }
    public int getKontoNrBet(){
        return kontoBet;
    }
    //setter metoder til ændring
    public void setRegiBet(Integer regiNr){
        if(regiNr!=null)this.regiBet=regiNr;
    }
    public void setKontoBet(Integer kontoNr){
        if(kontoNr!=null)this.kontoBet=kontoNr;
    }
    // Bruges i Medlem.saveMedlem()/toString()
    public String saveBetalingsInfo(){
        // Samme rækkefølge som i FilAdministering: regiNr, kontoNr
        return regiBet+","+kontoBet;
    }
    public String getBetalingsInfo(){
        return " | Registreringsnummer: "+regiBet+"\n" +
                " | Kontonummer:"+kontoBet;
    }
}
