public class Medlem {
    String navn;
    String adresse;
    String alder;

    Medlem(String na, String ad, String al){
        this.navn=na;
        this.adresse=ad;
        this.alder=al;
    }
    @Override
    public String toString(){
        return "\n"+navn+"\n"+adresse+"\n"+alder;
    }

    public static void main(String[]arg){
            Medlem m1=new Medlem("kasper","allegade45","67");
        Medlem m2=new Medlem("kasper","allegade45","67");
        Medlem m3=new Medlem("peter","allegade56","67");
            if((m2.toString().equals(m1.toString())))System.out.println("samme");else{System.out.println("wrong");}

    }
}
