public class BetalingsInfo {
    private int regiNr;
    private int kontoNr;
    BetalingsInfo(int regiNr,int kontoNr){
        this.regiNr=regiNr;
        this.kontoNr=kontoNr;
    }
    //setter metoder til ændring
    public void setRegiBet(Integer regiNr){
        if(regiNr!=null)this.regiNr=regiNr;
    }
    public void setKontoBet(Integer kontoNr){
        if(kontoNr!=null)this.kontoNr=kontoNr;
    }
    public String getBetalingsInfo(){
        return +regiNr+","+kontoNr;
    }
}
