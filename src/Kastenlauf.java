import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by matthias on 4/27/17.
 */
public class Kastenlauf {
    static int[][] city;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            int numStores = scanner.nextInt();
            scanner.nextLine();
            city = new int[numStores+2][2];
            for (int i = 0; i < numStores+2; i++) {
                String[] coord = scanner.nextLine().split(" ");
                city[i][0] = Integer.parseInt(coord[0]);
                city[i][1] = Integer.parseInt(coord[1]);
            }

            LinkedList<Integer> toVisit = new LinkedList<>();
            toVisit.add(0);
            boolean[] visited = new boolean[city.length];

            while (!visited[visited.length-1] && toVisit.size() > 0) {
                int pos = toVisit.remove();
                visited[pos] = true;
                for (int i = 1; i < city.length; i++) {
                    if (distanceInBeers(city[pos], city[i]) <= 20) {
                        if (!visited[i]) {
                            toVisit.add(i);
                        }
                    }
                }
            }

            System.out.println("Case #" + currCase + ": " + (visited[visited.length-1] ? "happy" : "sad"));
        }
    }

    int[] storeCoord(int i) {
        return city[i+1];
    }

    int[] houseCoord() {
        return city[0];
    }

    int[] carnivalCoord() {
        return city[city.length-1];
    }

    static int distanceInBeers(int[] a, int[] b) {
        return (Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1])) / 50;
    }
}
