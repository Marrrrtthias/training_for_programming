import java.util.*;

/**
 * Created by matthias on 4/25/17.
 */
public class PizzaToppings {
    static List<Integer>[] graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            String[] line = scanner.nextLine().split(" ");
            int numToppings = Integer.parseInt(line[0]);
            int numPairs = Integer.parseInt(line[1]);
            graph = new List[numToppings];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<>();
            }

            for (int i = 0; i < numPairs; i++) {
                // read pairs that dont taste well and add to graph
                line = scanner.nextLine().split(" ");
                graph[Integer.parseInt(line[0])].add(Integer.parseInt(line[1]));
                graph[Integer.parseInt(line[1])].add(Integer.parseInt(line[0]));
            }

            System.out.println("Case #" + currCase + ": " + (isBipartite()?"yes":"no"));
        }
    }

    static boolean isBipartite() {
        Collection<Integer> toColor = new HashSet<>(),
                PizzaA = new HashSet<>(),
                PizzaB = new HashSet<>();
        // TODO
    }
}
