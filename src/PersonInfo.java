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

    public String getPersonInfo(){
        return navn+","+alder+","+telefon+","+email+","+adresse;
    }
}
