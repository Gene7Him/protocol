import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            SportsScoreServer server = new SportsScoreServer(8080);

            // Start the server to listen for connections
            server.start();

            SportsScoreClient client = new SportsScoreClient("localhost", 8080);
            client.sendRequest("GET_SCORE Lakers");
            System.out.println("Response: " + client.receiveResponse());
            // Close the connection
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
