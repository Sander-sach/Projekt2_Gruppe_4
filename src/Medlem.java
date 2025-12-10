public class Medlem {
    // personoplysninger/Medlemstype
    public PersonInfo personinfo;
    public BetalingsInfo betalingsinfo;
    String aktivitetstype;
    Boolean konkurrence;

    // CONSTRUCTOR brugt af FilAdministering
    public Medlem(PersonInfo personin, String aktivitetstype, Boolean konkurrence, BetalingsInfo betalingsin) {
        this.personinfo = personin;
        this.betalingsinfo = betalingsin;
        this.aktivitetstype = aktivitetstype;
        this.konkurrence = konkurrence;
    }

    // NY CONSTRUCTOR brugt i Main:
    // Medlem nyt = new Medlem(navn, telefon, email, adresse, aktivitetstype, konkurrence);
    public Medlem(String navn, int telefon, String email, String adresse,
                  String aktivitetstype, Boolean konkurrence) {

        this.personinfo = new PersonInfo();
        this.personinfo.setNavnPer(navn);
        this.personinfo.setTelefonPer(telefon);
        this.personinfo.setEmailPer(email);
        this.personinfo.setAdressePer(adresse);
        // Alder kan sættes senere evt.

        this.betalingsinfo = new BetalingsInfo(); // standard tom betalingsinfo
        this.aktivitetstype = aktivitetstype;
        this.konkurrence = konkurrence;
    }

    // return
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

    String getAktivitetstype() {
        return aktivitetstype;
    }

    String getKonkurrence() {
        return (konkurrence ? "Konkurrence" : "Motionist");
    }

    // setters
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

    public String toString() {
        return personinfo.getPersonInfo() +
                "," + aktivitetstype +
                "," + (konkurrence == null ? "#" : (konkurrence ? "Konkurrence" : "Motionist")) +
                "," + betalingsinfo.getBetalingsInfo();
    }

    public String saveMedlem() {
        return personinfo.getPersonInfo() +
                "," + aktivitetstype +
                "," + (konkurrence != null ? String.valueOf(konkurrence) : "#") +
                "," + betalingsinfo.getBetalingsInfo();
    }

    public String verificerMedlem() {
        return getTelefon() + getEmail();
    }
}

