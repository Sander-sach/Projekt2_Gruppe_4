public class Medlem {
    //personoplysninger/Medlemstype
    private PersonInfo personinfo;
    private BetalingsInfo betalingsinfo;
    String aktivitetstype;
    Boolean konkurrence;

    Medlem(PersonInfo personin, String aktivitetstype, Boolean konkurrence, BetalingsInfo betalingsin) {
        this.personinfo = personin;
        this.betalingsinfo = betalingsin;
        this.aktivitetstype = aktivitetstype;
        this.konkurrence = konkurrence;
    }

    //return
    public String getNavn() {
        return personinfo.getNavnPer();
    }

    public String getEmail() {
        return personinfo.getEmailPer();
    }

    public String getAdresse() {
        return personinfo.getAdressePer();
    }

    public int getTelefon() {
        return personinfo.getTelefonPer();
    }

    public int getAlder() {
        return personinfo.getAlderPer();
    }

    public String getAktivitetstype() {
        return aktivitetstype;
    }

    public String getKonkurrence() {
        return (konkurrence ? "Konkurrence" : "Motionist");
    }
    public int getRegiNr(){
        return betalingsinfo.getRegiNrBet();
    }
    public int getKontoNr(){
        return betalingsinfo.getKontoNrBet();
    }


    // setter til ændringer af medlemsoplysninger
    public void setNavn(String navn) {
        personinfo.setNavnPer(navn);
    }

    public void setEmail(String email) {
        personinfo.setEmailPer(email);
    }

    public void setAdresse(String adresse) {
        personinfo.setAdressePer(adresse);
    }

    public void setTelefon(int telefon) {
        personinfo.setTelefonPer(telefon);
    }

    public void setRegiNr(Integer regiNr) {
        betalingsinfo.setRegiBet(regiNr);
    }

    public void setKontoNr(Integer kontoNr) {
        betalingsinfo.setKontoBet(kontoNr);
    }

    public void setAktivitetstype(String akt) {
        this.aktivitetstype = akt;
    }

    public void setKonkurrence(Boolean kon) {
         this.konkurrence = kon;
    }

    //toString metode til udprintning af medlemmer
    public String toString() {
        return personinfo.getPersonInfo() +
                "\nAktivitetstype:\t" + aktivitetstype +
                "\nMedlemstype:\t" + (konkurrence == null ? "N/A" : (konkurrence ? "Konkurrence" : "Montionist")) +
                "\n--Betalingsoplysninger--\n" + betalingsinfo.getBetalingsInfo();
    }

    // ToString metode til at gemme medlemmers Booleans som True eller False i .txt dokumenter
    public String saveMedlem() {
        return personinfo.savePersonInfo() +
                "," + aktivitetstype +
                "," + (konkurrence != null ? String.valueOf(konkurrence) : "#") +
                "," + betalingsinfo.saveBetalingsInfo();
    }

    // main til test
    public static void main(String[] args) {

    }//main
}//Medlem

