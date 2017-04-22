import java.util.Scanner;

/**
 * Created by matthias on 4/22/17.
 */
public class SureBet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();

            int numComps = scanner.nextInt();
            int minBetValue = scanner.nextInt();
            scanner.nextLine();
            String bestBetName = null;
            int bestBetValue = Integer.MAX_VALUE;

            for (int i = 0; i < numComps; i++) {
                String[] bet = scanner.nextLine().split(" ");
                int betValue = Integer.parseInt(bet[1]);
                if (betValue < bestBetValue && betValue >= minBetValue) {
                    bestBetValue = betValue;
                    bestBetName = bet[0];
                }
            }

            System.out.println("Case #" + currCase + ": " + (bestBetName==null ? "impossible" : bestBetName));
        }
    }
}
