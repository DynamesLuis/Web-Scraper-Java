import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HttpClient {
    Document html;
    Elements teamsTable;

    public void connectToPage() {
        try {
            html = Jsoup.connect("https://www.baseball-reference.com/teams/").get();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void getTeamsInfo() {
        getTable();
        Elements teamsRow = teamsTable.select("tbody tr:not(.partial_table)");
        for (Element teamRow : teamsRow) {
            Elements teamThs = teamRow.select("td");
            for (Element th : teamThs) {
                String thText = th.text();
                System.out.println(thText);
            }
        }
    }

    public void getTable() {
        teamsTable = html.select("#teams_active");
    }



}


