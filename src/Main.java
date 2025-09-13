import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        WriteFIle writeFIle = new WriteFIle();

        httpClient.connectToPage();
        httpClient.getTable();
        ArrayList<ArrayList<String>> teamsInfo = httpClient.getTeamsInfo();
        httpClient.getAllPlayers();
        ArrayList<ArrayList<String>> pitchersInfo = httpClient.getPitchersInfo();
        ArrayList<ArrayList<String>> battersInfo = httpClient.getBatterInfo();
        try {
            writeFIle.createCSV("teamsData", teamsInfo, "Franchise, From, To, G, W, L, W-L%, G>.500, Divs, Pnnts, WS, Playoff, Players, HOF#, R, AB, H, HR, BA, RA, ERA");
            writeFIle.createCSV("battersData", battersInfo, "Player, Age, Pos, WAR, G, PA, AB, R, H, 2B, 3B, HR, RBI, SB, CS, BB, SO, BA, OBP, SLG, OPS, OPS+, rOBA, Rbat+, TB, GIDP, HBP, SH, SF, IBB, Pos, Awards");
            writeFIle.createCSV("pitchersData", pitchersInfo, "Player, Age, Pos, WAR, W, L, W-L%, ERA,G, GS, GF, CG, SHO, SV, IP, H, R, ER, HR, BB, IBB, SO, HBP, BK, WP, BF, ERA+, FIP, WHIP, H9, HR9, BB9, SO9, SO/BB, Awards");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}