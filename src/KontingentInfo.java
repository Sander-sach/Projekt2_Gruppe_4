public class KontingentInfo {
    // regiNr og kontoNr bruges til at forbinde en betaling med et medlem
    private int regiNr;
    private int kontoNr;
    double kon;
    private boolean harBetalt;

    //Constructer
    public KontingentInfo(int regiNr,int kontoNr,double kon,boolean harBetalt ){
        this.regiNr=regiNr;
        this.kontoNr=kontoNr;
        this.kon=kon;
        this.harBetalt=harBetalt;
    }

    public static double beregnKontingent(Medlem m) {
        int alder = m.getAlder();
        String type = m.getAktivitetstype();
// passivt medlem
        if (type.equalsIgnoreCase("passivt")) return 500;
// Aktivt medlem
        if (alder < 18) return 1000;

        double seniorTakst = 1600;
        if (alder >= 60) return seniorTakst * 0.75;  // 25% rabat
        return seniorTakst;
    }
    public int getReNr(){
        return regiNr;
    }
    public int getKoNr(){
        return kontoNr;
    }

    public void setHarBetalt(boolean harBetalt) {
        this.harBetalt = harBetalt;
    }
    // boolean for betalt eller restance
    public boolean getBetaling() {
        return harBetalt;
    }

    public String toString() {
        return "--Medlems Kontingent-- \n" +
                " | Kontingent: " + kon +"\n"+
                " | Betalt: " + (harBetalt ? "Ja" : "Nej")+"\n" +
                " | Registreringsnummer:"+regiNr+"\n"+
                " | Kontonummer: "+kontoNr;
    }
    public String saveKontingent(){
        return regiNr+","+kontoNr+","+kon+","+harBetalt;
    }
}
