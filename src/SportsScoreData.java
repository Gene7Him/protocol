import java.util.ArrayList;

public class SportsScoreData {

    private ArrayList<Team> teams;

    public SportsScoreData() {
        teams = new ArrayList<Team>();
        // Initialize teams
        teams.add(new Team("Lakers"));
        teams.add(new Team("Pelicans"));

    }

    public Team getTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getLakers().equals(teamName)) {
                return team;
            }
        }
        return null; // Team not found
    }
}

