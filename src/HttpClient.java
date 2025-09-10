import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HttpClient {

    public void connectToPage() {
        try {
            Document html = Jsoup.connect("https://www.baseball-reference.com/teams/").get();
            Elements teamsTable = html.select("#teams_active caption");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


