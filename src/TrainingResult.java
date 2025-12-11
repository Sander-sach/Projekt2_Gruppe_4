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

    // træningsresultat

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

}




