import java.util.*;

/**
 * Created by matthias on 5/9/17.
 */
public class CallingCircles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("0 0")) {
            int numPeople = Integer.parseInt(input.split(" ")[0]);
            int numCalls = Integer.parseInt(input.split(" ")[1]);

            Digraph callGraph = new Digraph(numPeople);
            String[] names = new String[numPeople];
            int namesFilled = 0;

            Map<String, Integer> namesToIndex = new HashMap<>();

            for (int i = 0; i < numCalls; i++) {
                input = scanner.nextLine();
                String[] call = input.split(" ");
                if (!Arrays.asList(names).contains(call[0])) {
                    names[namesFilled] = call[0];
                    namesToIndex.put(call[0], namesFilled);
                    namesFilled++;
                }
                if (!Arrays.asList(names).contains(call[1])) {
                    names[namesFilled] = call[1];
                    namesToIndex.put(call[1], namesFilled);
                    namesFilled++;
                }
                callGraph.addEdge(namesToIndex.get(call[0]), namesToIndex.get(call[1]));
            }
            namesToIndex = null;

            strongComponents(callGraph);
            Map<Integer, List<String>> cycles = new TreeMap<>();
            for (int i = 0; i < sccId.length; i++) {
                cycles.putIfAbsent(sccId[i], new LinkedList<>());
                cycles.get(sccId[i]).add(names[i]);
            }

            for (int i : cycles.keySet()) {
                System.out.println(cycles.get(i).toString());
            }
            System.out.println("--------------------");
        }
    }

    static int[] sccId;

    static void strongComponents(Digraph G) {
        int n = G.n;
        sccId = new int[n];
        // Phase 1
        DFSGoneWild dfsRev = new DFSGoneWild(n);
        dfsRev.dfsAllNodes(reverseGraph(G));
        Collections.reverse(dfsRev.dfsPostorder);
        // Phase 2
        DFSGoneWild dfs = new DFSGoneWild(n);
        int id = 0;
        for (int s : dfsRev.dfsPostorder) {
            if (dfs.visited[s]) {
                continue;
            }
            dfs.dfsPostorder.clear();
            dfs.dfs(G, s);
            for (int v : dfs.dfsPostorder) {
                sccId[v] = id;
            }
            id++;
        }
    }

    static Digraph reverseGraph(Digraph G) {
        int n = G.n;
        Digraph result = new Digraph(n);
        for (int v = 0; v < n; v++) {
            for (int w : G.adj[v]) {
                result.addEdge(w, v);
            }
        }
        return result;
    }
}

class DFSGoneWild {
    boolean[] visited;
    int[] father;
    List<Integer> dfsPostorder = new LinkedList<>();
    Deque<Integer> dicycle = null;

    DFSGoneWild(int n) {
        visited = new boolean[n];
        father = new int[n];
    }

    void dfsAllNodes(Digraph G) {
        int n = G.n;
        visited = new boolean[n];
        father = new int[n];
        for (int s = 0; s < n; s++) {
            if (!visited[s]) {
                dfs(G, s);
            }
        }
    }

    void dfs(Digraph G, int s) {
        int n = G.n;
        Deque<Integer> todo = new LinkedList<>();
        boolean[] onPathFromS = new boolean[n];
        visited[s] = true;
        father[s] = -1;
        onPathFromS[s] = true;
        todo.push(s);
        while (!todo.isEmpty()) {
            int entry = todo.pop();
            if (entry >= 0) {
                int v = entry;
                onPathFromS[v] = true;
                todo.push(v - (n+1));
                for (int w : G.adj[v]) {
                    if (!visited[w]) {
                        visited[w] = true;
                        father[w] = v;
                        todo.push(w);
                    } else if (onPathFromS[w] && dicycle == null) {
                        dicycle = new LinkedList<>();
                        dicycle.add(v);
                        do {
                            v = father[v];
                            dicycle.addFirst(v);
                        } while (v != w);
                        return;
                    }
                }
            } else {
                int v = entry + (n+1);
                onPathFromS[v] = false;
                dfsPostorder.add(v);
            }
        }
    }
}

class Digraph {
    List<Integer>[] adj;
    int n;

    Digraph(int n) {
        this.n = n;
        this.adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }
}