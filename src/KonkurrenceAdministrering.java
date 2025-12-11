import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class KonkurrenceAdministrering {

    static private ArrayList<KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();
    static public void addKonkurrenceSvømmer(KonkurrenceSvømmer ks) {
            konkurrenceSvømmere.add(ks);
        }
        void sorterKonkurrenceHold(){
            ArrayList<KonkurrenceSvømmer> holdj = new ArrayList<>();
            ArrayList<KonkurrenceSvømmer> holds = new ArrayList<>();
            for (KonkurrenceSvømmer k:konkurrenceSvømmere) {
                if (k.getAlder() < 18) {
                    holdj.add(k);
                } else holds.add(k);
            }
            System.out.println("---Junior Hold---");
            for(KonkurrenceSvømmer k:holdj){
                System.out.println(k);
            }
            System.out.println("---Senior Hold---");
            for(KonkurrenceSvømmer k:holds){
                System.out.println(k);
            }
        }
        void hentKonkurrenceMedlemmer(ArrayList<Medlem> medlemmer){
            for (Medlem m : medlemmer) {
                if (m.konkurrence != null && m.konkurrence != false) {
                    KonkurrenceSvømmer k=new KonkurrenceSvømmer(m,m.getAlder());
                    konkurrenceSvømmere.add(k);
                }
            }
        }

    }
