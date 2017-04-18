import java.util.*;

/**
 * Created by matthias on 4/18/17.
 */
public class SoccerBets {
    public static void main(String[] args) {
        Map<String, Boolean> teams = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < 16; i++) {
            String[] match = scanner.nextLine().split(" ");
            if(Integer.parseInt(match[2])>Integer.parseInt(match[3]) && teams.get(match[0]) == null||teams.get(match[0])) {
                // Team a hat gewonnen und ist noch unbekannt
                // TODO
            } else if(Integer.parseInt(match[2])<Integer.parseInt(match[3]) && (teams.get(match[1]) == null||teams.get(match[1]))) {
                // Team b hat gewonnen und ist noch unbekannt oder nicht ausgeschieden
                // TODO
            }
            // TODO
        }
    }
}
