import java.util.*;

/**
 * Created by matthias on 5/2/17.
 */
public class Dwarves {
    static Map<String, Collection<String>> dwarfMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();


        for (int currCase = 1; currCase <= numCases; currCase++) {
            String[] line = scanner.nextLine().split(" ");
            String[] relation;
            if (line[1].equals("<")) {
                relation = new String[2];
                relation[0] = line[2];
                relation[1] = line[0];
            } else if (line[1].equals(">")) {
                relation = new String[2];
                relation[0] = line[0];
                relation[1] = line[2];
            } else {
                throw new InputMismatchException();
            }
            dwarfMap.putIfAbsent(relation[0], new HashSet<>());
            dwarfMap.putIfAbsent(relation[1], new HashSet<>());
            if (dwarfMap.get(relation[1]).contains(relation[0]) || relation[0].equals(relation[1])) {
                System.out.println("impossible");
                System.exit(0);
            }
            dwarfMap.get(relation[0]).add(relation[1]);
        }

        for (String root : dwarfMap.keySet()) {
            Map<String, Boolean> visited = new HashMap<>();
            Map<String, Boolean> finished = new HashMap<>();
            Map<String, List<String>> backStack = new HashMap<>();
            String current = null;
            Stack<String> todo = new Stack<>();
            todo.push(root);

            while (!todo.isEmpty()) {
                if (current != null) {
                    backStack.putIfAbsent(todo.peek(), new LinkedList<>());
                    backStack.get(todo.peek()).add(current);
                }
                current = todo.pop();
                finished.putIfAbsent(current, false);

                if (finished.get(current)) {
                    continue;
                }
                backStack.putIfAbsent(current, new LinkedList<>());
                if (backStack.get(current).contains(current)) {
                    System.out.println("impossible");
                    System.exit(0);
                }
                for (String neighbor : dwarfMap.get(current)) {
                    todo.push(neighbor);
                }
                finished.put(current, true);
            }
            // TODO
        }

        System.out.println("possible");
    }
}
