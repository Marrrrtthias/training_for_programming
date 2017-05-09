import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by matthias on 5/9/17.
 */
public class CallingCircles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            // TODO do something

            System.out.println("Case #" + currCase + ": " /*TODO print something*/);
        }
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
}