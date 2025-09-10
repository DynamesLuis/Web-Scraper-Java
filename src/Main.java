import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        httpClient.connectToPage();
        ArrayList<ArrayList<String>> teamsInfo = httpClient.getTeamsInfo();

    }
}