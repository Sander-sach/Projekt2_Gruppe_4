public class BetalingsInfo {
    private int regiNr;
    private int kontoNr;
    BetalingsInfo(int regiNr,int kontoNr){
        this.regiNr=regiNr;
        this.kontoNr=kontoNr;
    }
    public String getBetalingsInfo(){
        return +regiNr+","+kontoNr;
    }
}
