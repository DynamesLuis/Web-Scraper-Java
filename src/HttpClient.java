import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {
    Document html;
    Elements teamsTable;
    ArrayList<ArrayList<String>> teamsInfo = new ArrayList<>();

    public void connectToPage() {
        try {
            html = Jsoup.connect("https://www.baseball-reference.com/teams/").get();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void getAllPlayers() {

    }

    public ArrayList<ArrayList<String>> getTeamsInfo() {
        Elements teamsRow = teamsTable.select("tbody tr:not(.partial_table)");
        for (Element teamRow : teamsRow) {
            Elements teamThs = teamRow.select("td");
            ArrayList<String> teamInfo = new ArrayList<>();
            for (Element th : teamThs) {
                String thText = th.text();
                teamInfo.add(thText);
            }
            teamsInfo.add(teamInfo);
        }
        return teamsInfo;
    }

    public void getTable() {
        teamsTable = html.select("#teams_active");
    }
}


