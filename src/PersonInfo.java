public class PersonInfo {

    // Felter (samme rækkefølge som i filen: navn, alder, telefon, email, adresse)
    private String navnPer;
    private int alderPer;
    private int telefonPer;
    private String emailPer;
    private String adressePer;

    // Tom constructor (bruges i Medlem)
    public PersonInfo() {
    }

    // Constructor der matcher FilAdministering:
    // new PersonInfo(navn, alder, telefon, email, adresse)
    public PersonInfo(String navnPer, int alderPer, int telefonPer,
                      String emailPer, String adressePer) {
        this.navnPer = navnPer;
        this.alderPer = alderPer;
        this.telefonPer = telefonPer;
        this.emailPer = emailPer;
        this.adressePer = adressePer;
    }

    // Getters
    public String getNavnPer() {
        return navnPer;
    }

    public int getAlderPer() {
        return alderPer;
    }

    public int getTelefonPer() {
        return telefonPer;
    }

    public String getEmailPer() {
        return emailPer;
    }

    public String getAdressePer() {
        return adressePer;
    }

    // Setters
    public void setNavnPer(String navnPer) {
        this.navnPer = navnPer;
    }

    public void setAlderPer(int alderPer) {
        this.alderPer = alderPer;
    }

    public void setTelefonPer(int telefonPer) {
        this.telefonPer = telefonPer;
    }

    public void setEmailPer(String emailPer) {
        this.emailPer = emailPer;
    }

    public void setAdressePer(String adressePer) {
        this.adressePer = adressePer;
    }
    // Bruges i Medlem.saveMedlem()/toString()
    public String savePersonInfo() {
        // Samme rækkefølge som i FilAdministering: navn, alder, telefon, email, adresse
        return navnPer + "," + alderPer + "," + telefonPer + "," + emailPer + "," + adressePer;
    }
    public String getPersonInfo(){
        return " | Navn: "+navnPer+"\n"+
                " | Alder: "+ alderPer+"\n"+
                " | Telefon: "+telefonPer+"\n" +
                " | Email: "+emailPer+"\n"+
                " | Adresse: "+adressePer;
    }
}
