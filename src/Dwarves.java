import java.util.*;

/**
 * Created by matthias on 5/2/17.
 */
public class Dwarves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numDwarves = scanner.nextInt();
        scanner.nextLine();

        Map<String, LinkedList<String>> dwarfGraph = new HashMap<>();
        String currentDwarf;

        // gerichteten graph aufbauen
        for (int currCase = 1; currCase <= numDwarves; currCase++) {
            String[] line = scanner.nextLine().split(" ");
            if (line[1].equals(">")) {
                if (!dwarfGraph.keySet().contains(line[0])) {
                    dwarfGraph.put(line[0], new LinkedList<>());
                }
                dwarfGraph.get(line[0]).add(line[2]);
            } else if (line[1].equals("<")) {
                if (!dwarfGraph.keySet().contains(line[2])) {
                    dwarfGraph.put(line[2], new LinkedList<>());
                }
                dwarfGraph.get(line[2]).add(line[0]);
            }
        }

        // Zykel suchen
        boolean hasCycle = false;
        Set<String> visited = new HashSet<>();
        LinkedList<String> remaining = new LinkedList<>();
        remaining.addAll(dwarfGraph.keySet());
        while (!remaining.isEmpty()) {
            String current = remaining.remove();
            // TODO
        }

        System.out.println(/*TODO print something*/);
    }
}
