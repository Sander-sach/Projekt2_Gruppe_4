import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class TrainingResult {
    public enum Discipline {
        BUTTERFLY_100,
        CRAWL_100,
        CRAWL_200,
        BACKSTROKE_100,
        BREASTSTROKE_100,
        MEDLEY_400
    }

    // træningsresultat
    public class Result {
        private Discipline discipline;
        private double time;
        private LocalDate date;

        public Result(Discipline discipline, double time, LocalDate date) {
            this.discipline = discipline;
            this.time = time;
            this.date = date;
        }

        public Discipline getDiscipline() {
            return discipline;
        }

        public double getTime() {
            return time;
        }

        public LocalDate getDate() {
            return date;
        }

        public String toString() {
            return discipline + "," + time + "," + date;
        }
    }

    public class CompetitionResult {
        private String eventName;
        private int placement;
        private double time;
        private Discipline discipline;
        private LocalDate date;

        public CompetitionResult(String eventName, int placement, double time, Discipline discipline, LocalDate date) {
            this.eventName = eventName;
            this.placement = placement;
            this.time = time;
            this.discipline = discipline;
            this.date = date;
        }

        public Discipline getDiscipline() {
            return discipline;
        }

        public double getTime() {
            return time;
        }

        public String toString() {
            return eventName + "," + placement + "," + time + "," + discipline + "," + date;
        }

    }

    public class KonkurrenceSvømmer {

        private Medlem medlem;
        private HashMap<Discipline, Result> bestTrainingResults = new HashMap<>();
        private ArrayList<CompetitionResult> competitionResults = new ArrayList<>();

        public KonkurrenceSvømmer(Medlem medlem){
            this.medlem = medlem;
        }

        public Medlem getMedlem() {
            return medlem;
        }

        public void addTrainingResult(Result r) {
            Discipline d = r.getDiscipline();

            if (!bestTrainingResults.containsKey(d) ||
                    r.getTime() < bestTrainingResults.get(d).getTime()) {
                bestTrainingResults.put(d, r);
            }
        }

        public void addCompetitionResult(CompetitionResult r) {
            competitionResults.add(r);
        }

        public Result getBestTraining(Discipline d) {
            return bestTrainingResults.get(d);
        }

        public ArrayList<CompetitionResult> getCompetitionResults() {
            return competitionResults;
        }

        public class ResultatAdministrering {

            private ArrayList<KonkurrenceSvømmer> konkurrenceSvømmere = new ArrayList<>();

            public void addKonkurrenceSvømmer(KonkurrenceSvømmer ks) {
                konkurrenceSvømmere.add(ks);
            }

            public ArrayList<KonkurrenceSvømmer> top5(Discipline discipline) {
                return konkurrenceSvømmere.stream()
                        .filter(s -> s.getBestTraining(discipline) != null)
                        .sorted(Comparator.comparingDouble(
                                s -> s.getBestTraining(discipline).getTime()))
                        .limit(5)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }
    }
}
