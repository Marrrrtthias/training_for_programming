import java.util.*;

/**
 * Created by matthias on 6/27/17.
 */
public class PizzaHawai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            int numPizzas = Integer.parseInt(scanner.nextLine());

            String[] menuIng = new String[20];
            String[] knownIng = new String[20];
            int lenMenu = 0, lenKnown = 0;
            List<int[]> bitMaps = new LinkedList<>();
            Map<String, Integer> menuToIndex = new TreeMap<>();
            Map<String, Integer> knownToIndex = new TreeMap<>();

            for (int pizza = 0; pizza < numPizzas; pizza++) {
                int numIng = scanner.nextInt();
                for (int i = 0; i < numIng; i++) {

                }
            }

            System.out.println("Case #" + currCase + ": " /*TODO print something*/);
        }
    }
}
