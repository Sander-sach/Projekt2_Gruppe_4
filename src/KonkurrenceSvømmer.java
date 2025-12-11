import java.util.ArrayList;
import java.util.HashMap;

public class KonkurrenceSvømmer {
    private int alder;
    private Medlem medlem;
    private HashMap<TrainingResult.Discipline, TrainingResult.Result> bestTrainingResults = new HashMap<>();
    private ArrayList<TrainingResult.CompetitionResult> competitionResults = new ArrayList<>();

    public KonkurrenceSvømmer(Medlem medlem,int alder) {
        this.medlem = medlem;
        this.alder=alder;
    }
    public Medlem getMedlem() {
        return medlem;
    }
    public int getAlder() {
        return alder;
    }
    public String toString() {
        return medlem.getNavn()+"| Alder: "+alder;
    }


    public void addTrainingResult(TrainingResult.Result r) {
        TrainingResult.Discipline d = r.getDiscipline();

        if (!bestTrainingResults.containsKey(d) ||
                r.getTime() < bestTrainingResults.get(d).getTime()) {
            bestTrainingResults.put(d, r);
        }
    }

    public void addCompetitionResult(TrainingResult.CompetitionResult r) {
        competitionResults.add(r);
    }

    public TrainingResult.Result getBestTraining(TrainingResult.Discipline d) {
        return bestTrainingResults.get(d);
    }

    public ArrayList<TrainingResult.CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public class ResultatAdministrering {

        private ArrayList<KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();

        public void addKonkurrenceSvømmer(KonkurrenceSvømmer ks) {
            konkurrenceSvømmere.add(ks);
        }

    }
}