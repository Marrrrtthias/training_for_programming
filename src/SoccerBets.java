import java.util.*;

/**
 * Created by matthias on 4/18/17.
 */
public class SoccerBets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currentCase = 1; currentCase <= numCases; currentCase++) {
            Set<String> knownTeams = new HashSet<>();       // all teams the program has come across so far
            Set<String> winCandidates = new HashSet<>();    // all teams where no lost game has been found so far
            scanner.nextLine();

            for (int i = 0; i < 16; i++) {
                String[] match = scanner.nextLine().split(" ");
                if (Integer.parseInt(match[2]) > Integer.parseInt(match[3])) {
                    // Team a hat gewonnen
                    winCandidates.remove(match[1]);     // team b can not win anymore; remove it from winCandidates
                    if(!knownTeams.contains(match[0])) {
                        // only add team a to wincandidates if it was previously unknown, otherwhise it has already lost a game before
                        winCandidates.add(match[0]);
                    }
                    knownTeams.add(match[0]);
                    knownTeams.add(match[1]);
                } else if (Integer.parseInt(match[2]) < Integer.parseInt(match[3])) {
                    // Team b hat gewonnen
                    winCandidates.remove(match[0]);     // team a can not win anymore; remove it from winCandidates
                    if(!knownTeams.contains(match[1])) {
                        winCandidates.add(match[1]);
                    }
                    knownTeams.add(match[0]);
                    knownTeams.add(match[1]);
                }
            }

            if(winCandidates.size() != 1) {     // sanity check
                System.err.println("Something went wrong");
                System.exit(1);
            }
            System.out.println("Case #" + currentCase + ": " + winCandidates.toArray()[0]);
        }
    }
}
