import java.util.*;

/**
 * Created by matthias on 4/25/17.
 */
public class PizzaToppings {
    static List<Integer>[] graph;
    static int numPairs;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] line = scanner.nextLine().split(" ");
            int numToppings = Integer.parseInt(line[0]);
            numPairs = Integer.parseInt(line[1]);
            graph = new List[numToppings];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<>();
            }

            for (int i = 0; i < numPairs; i++) {
                // read pairs that dont taste well and add to graph
                line = scanner.nextLine().split(" ");
                graph[Integer.parseInt(line[0])-1].add(Integer.parseInt(line[1])-1);
                graph[Integer.parseInt(line[1])-1].add(Integer.parseInt(line[0])-1);
            }
            //printgraph();

            System.out.println("Case #" + currCase + ": " + (isBipartite()?"yes":"no"));
        }
    }

    static void printgraph() {
        for (int i = 0; i < graph.length; i++) {
            System.out.println(i + " -> " + graph[i].toString());
        }
    }

    static boolean isBipartite() {
        if (graph.length == 2 || numPairs == 0) {
            return true;
        }

        Queue<Integer> toColor = new LinkedList<>();
        boolean[] isColored = new boolean[graph.length], color = new boolean[graph.length];
        toColor.add(0);
        boolean currentColor;
        int[] parent = new int[graph.length];

        while (true) {
            while (!toColor.isEmpty()) {
                //System.out.println("nodes to color " + toColor.toString());
                int nodeToColor = toColor.remove();
                if (isColored[nodeToColor]) {
                    continue;
                }
                currentColor = !color[parent[nodeToColor]];
                //System.out.println("trying to color node " + nodeToColor + " in " + currentColor);
                for (int neighbor : graph[nodeToColor]) {
                    if (isColored[neighbor] && color[neighbor] == currentColor) {
                        //System.out.println("cancelling, because node " + neighbor + " is " + color[neighbor]);
                        return false;
                    }
                    if (!isColored[neighbor]) {
                        toColor.add(neighbor);
                        parent[neighbor] = nodeToColor;
                    }
                }
                color[nodeToColor] = currentColor;
                isColored[nodeToColor] = true;
                //System.out.println("Node " + nodeToColor + " colored " + currentColor);
            }
            boolean allColored = true;
            for (int i = 0; i < isColored.length; i++) {
                if (!isColored[i]) {
                    toColor.add(i);
                    allColored = false;
                    break;
                }
            }
            if (allColored) {
                return true;
            }
        }
    }
}
