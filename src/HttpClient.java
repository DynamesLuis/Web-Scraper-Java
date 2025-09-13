import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class HttpClient {
    Document html;
    Elements teamsTable;
    ArrayList<ArrayList<String>> teamsInfo = new ArrayList<>();
    ArrayList<ArrayList<String>> batterInfo = new ArrayList<>();
    ArrayList<ArrayList<String>> pitchersInfo = new ArrayList<>();
    String pageUrl = "https://www.baseball-reference.com/teams/";

    public void connectToPage() {
        try {
            html = Jsoup.connect("https://www.baseball-reference.com/teams/").get();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void getAllPlayers() {
        //otener todos los links
        Elements teamsRow = teamsTable.select("tbody tr:not(.partial_table)");
        Elements aTags = teamsRow.select("a[href]");
        for (Element a : aTags) {
            //extraer el "link"
            String link = a.attr("href");
            String formattedLink = pageUrl + link.substring(7, 11) + "2025.shtml";
            //request página
            try {
                Document teamHtml = Jsoup.connect(formattedLink).get();
                Elements battingTable = teamHtml.select("#players_standard_batting");
                Elements pitchingTable = teamHtml.select("#players_standard_pitching");
                Elements battingRows = battingTable.select("tbody tr:not(.thead)");
                Elements pitchingRows = pitchingTable.select("tbody tr:not(.thead)");
                for (Element batterRow : battingRows) {
                    Elements batterTds = batterRow.select("td");
                    ArrayList<String> playerInfo = new ArrayList<>();
                    for (Element th : batterTds) {
                        String thText = th.text();
                        playerInfo.add(thText);
                    }
                    batterInfo.add(playerInfo);
                }
                for (Element pitcherRow : pitchingRows) {
                    Elements batterTds = pitcherRow.select("td");
                    ArrayList<String> playerInfo = new ArrayList<>();
                    for (Element th : batterTds) {
                        String thText = th.text();
                        playerInfo.add(thText);
                    }
                    pitchersInfo.add(playerInfo);
                }
                Thread.sleep(5000);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        }
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

    public ArrayList<ArrayList<String>> getBatterInfo() {
        return batterInfo;
    }

    public ArrayList<ArrayList<String>> getPitchersInfo() {
        return pitchersInfo;
    }
}


