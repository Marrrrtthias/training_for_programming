import java.util.Scanner;

/**
 * Created by matthias on 5/30/17.
 */
public class Change {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");
            int numCoins = Integer.parseInt(input[0]);
            int toPay = Integer.parseInt(input[1]);

            int[] coinValues = new int[numCoins];
            for (int i = 0; i < coinValues.length; i++) {
                coinValues[i] = scanner.nextInt();
            }
            scanner.nextLine();

            int[] coinsToUse = new int[numCoins];
            int currValue = 0;
            for (int i = coinValues.length-1; i >= 0; i--) {
                while (currValue + coinValues[i] <= toPay) {
                    coinsToUse[i]++;
                    currValue += coinValues[i];
                }
                if (currValue == toPay)
                    break;
            }

            System.out.println("Case #" + currCase + ":" + arrayToString(coinsToUse));
        }
    }

    static String arrayToString(int[] arr) {
        StringBuilder res = new StringBuilder();
        for (int i : arr)
            res.append(" " + i);
        return res.toString();
    }
}
