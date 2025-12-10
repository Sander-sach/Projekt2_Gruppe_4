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

    // Getters
    public int getRegiBet() {
        return regiBet;
    }

    public int getKontoBet() {
        return kontoBet;
    }

    // Setters
    public void setRegiBet(int regiBet) {
        this.regiBet = regiBet;
    }

    public void setKontoBet(int kontoBet) {
        this.kontoBet = kontoBet;
    }

    // Bruges i Medlem.saveMedlem()/toString()
    public String getBetalingsInfo() {
        // Samme rækkefølge som i FilAdministering: regiNr, kontoNr
        return regiBet + "," + kontoBet;
    }
}


