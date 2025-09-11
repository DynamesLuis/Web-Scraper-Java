import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        WriteFIle writeFIle = new WriteFIle();

        httpClient.connectToPage();
        httpClient.getTable();
        ArrayList<ArrayList<String>> teamsInfo = httpClient.getTeamsInfo();
        try {
            writeFIle.createCSV("teamsData", teamsInfo);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}