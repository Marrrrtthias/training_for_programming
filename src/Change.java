import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by matthias on 5/30/17.
 */
public class Change {
    static int[][] coinsToUse;
    static Map<Integer,Integer> coinToIndex;

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");
            int numCoins = Integer.parseInt(input[0]);
            int toPay = Integer.parseInt(input[1]);

            int[] coinValues = new int[numCoins];
            coinsToUse = new int[toPay+1][numCoins];
            coinToIndex = new TreeMap<>();
            int[] bestSoFar = new int[toPay+1]; for (int i = 0; i < bestSoFar.length; i++) bestSoFar[i] = Integer.MAX_VALUE;
            bestSoFar[0] = 0;
            for (int i = 0; i < coinValues.length; i++) {
                coinValues[i] = scanner.nextInt();
                coinToIndex.put(coinValues[i], i);
                if (coinValues[i] < coinsToUse.length) {
                    coinsToUse[coinValues[i]][i] = 1;
                    bestSoFar[coinValues[i]] = 1;
                }
            }

            for (int i = 1; i < coinsToUse.length; i++) {
                //System.out.println("building from value: " + i);
                for (int coinValue : coinValues) {
                    int valueBeingBuilt = i + coinValue;
                    //System.out.println("building value: " + valueBeingBuilt);
                    if (valueBeingBuilt > toPay || bestSoFar[valueBeingBuilt] < (bestSoFar[i]+1)) continue;

                    copyCoins(i, valueBeingBuilt);
                    coinsToUse[valueBeingBuilt][coinToIndex.get(coinValue)] += 1;
                    bestSoFar[valueBeingBuilt] = bestSoFar[i] + 1;
                }
            }

            System.out.println("Case #" + currCase + ":" + arrayToString(coinsToUse[toPay]));
            //System.out.println(Arrays.toString(bestSoFar));

            try {
                scanner.nextLine();
            } catch (Exception e) {
                // dont care
            }
        }
    }

    private static void copyCoins(int a, int b) {
        for (int i = 0; i < coinsToUse[a].length; i++) {
            coinsToUse[b][i] = coinsToUse[a][i];
        }
    }

    static String arrayToString(int[] arr) {
        StringBuilder res = new StringBuilder();
        for (int i : arr)
            res.append(" " + i);
        return res.toString();
    }
}
