public class PersonInfo {
   private String navn;
    private int alder;
    private String adresse;
    private int telefon;
    private String email;

    PersonInfo(String navn, int alder,int telefon,String email, String adresse){
        this.navn=navn;
        this.alder=alder;
        this.adresse=adresse;
        this.email=email;
        this.telefon=telefon;
    }
    public String getNavnPer(){
        return navn;
    }
    public String getEmailPer(){
        return email;
    }
    public String getAdressePer(){
        return adresse;
    }
    public int getTelefonPer(){
        return telefon;
    }
    public int getAlderPer(){
        return alder;
    }
    //setter til Personoplysnings ændringer
    public void setNavnPer(String navn){
        if(navn!=null)this.navn=navn;
    }
    public void setEmailPer(String email){
        if(email!=null)this.email=email;
    }
    public void setAdressePer(String adresse){
        if(adresse!=null)this.adresse=adresse;
    }
    public void setTelefonPer(Integer telefon){
        if(telefon!=null)this.telefon=telefon;
    }

    public String getPersonInfo(){
        return navn+","+alder+","+telefon+","+email+","+adresse;
    }
}
