import java.util.*;

/**
 * Created by matthias on 5/2/17.
 */
public class Dwarves {
    static Digraph dwarfGraph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLines = scanner.nextInt();
        dwarfGraph = new Digraph((2*numLines > 10000) ? 10000 : 2 * numLines);     // max number of dwarves
        scanner.nextLine();

        Map<String, Integer> namesToIndex = new TreeMap<>();
        for (int currLine = 1; currLine <= numLines; currLine++) {
            String[] line = scanner.nextLine().split(" ");
            String[] relation = new String[2];
            if (line[1].equals("<")) {
                relation[0] = line[2];
                relation[1] = line[0];
            } else if (line[1].equals(">")) {
                relation[0] = line[0];
                relation[1] = line[2];
            } else {
                throw new InputMismatchException();
            }
            namesToIndex.putIfAbsent(relation[0], namesToIndex.size());
            namesToIndex.putIfAbsent(relation[1], namesToIndex.size());
            dwarfGraph.addEdge(namesToIndex.get(relation[0]), namesToIndex.get(relation[1]));
        }

        dwarfGraph.n = namesToIndex.size();
        DFSGoneWild dfs = new DFSGoneWild(namesToIndex.size());
        dfs.dfsAllNodes(dwarfGraph);

        if (dfs.dicycle != null) {
            System.out.println("impossible");
        } else {
            System.out.println("possible");
        }
    }

    static class DFSGoneWild {
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

    static class Digraph {
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
}
