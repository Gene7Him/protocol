import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SportsScoreServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private SportsScoreData sportsScoreData;

    public SportsScoreServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        sportsScoreData = new SportsScoreData();
    }

    public void start() throws IOException {
        System.out.println("Server is running...");
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String request;
        while ((request = in.readLine()) != null) {
            String response = handleRequest(request);
            out.println(response);
        }

        close();
    }

    private String handleRequest(String request) {
        String[] tokens = request.split(" ");
        String command = tokens[0];

        switch (command) {
            case "GET_SCORE":
                String teamName = tokens[1];
                Team team = sportsScoreData.getTeamByName(teamName);
                if (team != null) {
                    return teamName + " " + team.getWins() + " " + team.getLosses();
                } else {
                    return "Team not found";
                }
            case "RECORD_WIN":
                teamName = tokens[1];
                team = sportsScoreData.getTeamByName(teamName);
                if (team != null) {
                    team.recordWin();
                    return teamName + " " + team.getWins() + " " + team.getLosses();
                } else {
                    return "Team not found";
                }
            case "RECORD_LOSS":
                teamName = tokens[1];
                team = sportsScoreData.getTeamByName(teamName);
                if (team != null) {
                    team.recordLoss();
                    return teamName + " " + team.getWins() + " " + team.getLosses();
                } else {
                    return "Team not found";
                }
            case "QUIT":
                close();
                return null;
            default:
                return "Invalid command";
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

