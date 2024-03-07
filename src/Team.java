public class Team {


        private String Lakers;
        private int wins;
        private int losses;

        public Team(String teamName) {
            this.Lakers = teamName;
            this.wins = 0;
            this.losses = 0;
        }

        public void recordWin() {
            wins++;
        }

        public void recordLoss() {
            losses++;
        }

        public String getLakers() {
            return Lakers;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }
    }


