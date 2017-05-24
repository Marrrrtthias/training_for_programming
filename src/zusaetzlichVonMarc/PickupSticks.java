import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.*;


public class PickupSticks {

	public static int[]  values;

	public static void main(String[] args) {

		  Scanner sc = new Scanner(System.in);

	      int t = 0;
	      t = sc.nextInt();
	      sc.nextLine();

	      for(int i=1; i<=t; i++) {
	    	  /*
	    	   * #Sticks
	    	   */
	    	  int n = sc.nextInt();
	    	  /*
	    	   * #intersections
	    	   */
	    	  int m = sc.nextInt();

	    	  /*
	    	   * Graph with edge from a to b, if a lies over b
	    	   */
	    	  Graph conflicts = new Graph(n);

	    	  /*
	    	   * values[n] = value of the nth stick
	    	   */
	    	  values = new int[n];
    		  for(int k =0; k<n; k++){
    			  values[k]=sc.nextInt();
    		  }

    		  /*
    		   * add edges as described above
    		   */
	    	  for(int j=0 ; j<m ; j++){
	    		  int upper = sc.nextInt();
	    		  int lower = sc.nextInt();
	    		  conflicts.addDirectedEdge(upper,lower);
	    	  }

	    	  /*
	    	   * topological sort using the DFSGoneWild provided in the lecture
	    	   */
	    	  DFSGoneWild toposort = new DFSGoneWild(n);
	    	  List<Integer> dfsTop = toposort.topoSort(conflicts);

	    	  /*
	    	   * output
	    	   * values keeps track of the values used
	    	   */
	    	  System.out.println("Case #"+i+": "+toposort.value);



	    	  /*
	    	   * you can't pick up a stick, if it is not part of the (so far until a cycle) topological sort
	    	   */

	      }
	}


}

/*
 * the DFSGoneWild as provided in the lecture
 * changes to the template are marked as "task-specific TODO"
 */
class DFSGoneWild{
    boolean[] visited ;
    int[] father;
    List<Integer> dfsPostorder = new LinkedList<>();
    Deque<Integer> dicycle = null;

    public int value; //task-specific TODO

    DFSGoneWild(int n){
        visited = new boolean[n];
        father = new int[n];
    }

    public List<Integer> topoSort(Graph g){
    	dfsAllNodes(g);
    	LinkedList<Integer> topo = new LinkedList<Integer>(dfsPostorder);
    	Collections.reverse(topo);
    	return topo;
    }

    public boolean hasDicycle(Graph g){
    	return dicycle.equals(null);
    }

    public void dfsAllNodes(Graph g){
    	int n = g.getNodeCount();
    	visited = new boolean[n];
    	father = new int[n];
    	for(int s=0; s<n; s++){
    		if(!visited[s]){
    			dfs(g,s);
    		}
    	}
    }

    public void dfs(Graph g, int s){
    	int n = g.getNodeCount();
    	Deque<Integer> todo = new LinkedList<>();
    	boolean[] onPathFromS = new boolean[n];

    	visited[s] = true;
    	father[s] = -1;
    	onPathFromS[s] = true;
    	todo.push(s);

    	while(!todo.isEmpty()){
    		int entry = todo.pop();
    		if(entry >= 0) {
    			int v = entry;
    			onPathFromS[v] = true;
    			todo.push(v-(n+1));
    			for(int w : g.getNeighbours(v)){
    				if(!visited[w]) {
    					visited[w] = true;
    					father[w] = v;
    					todo.push(w);
    				} else if(onPathFromS[w] && dicycle == null) {
    					dicycle = new LinkedList<>();
    					dicycle.add(v);
    					do {
    						v = father[v];
    						dicycle.addFirst(v);
    					} while (v!=w);
    					return;
    					}

    				}

    			} else {
    				int v = entry+(n+1);
    				onPathFromS[v]=false;
    				dfsPostorder.add(v);
    				value+=PickupSticks.values[v]; //task-specific TODO
    		}

    	}
    }

    public List<Integer> getPostorder(){
    	return dfsPostorder;
    }
}



/*
 * my Graph-structure
 */
class Graph{
    public ArrayList<LinkedList<Integer>> adj = new ArrayList<LinkedList<Integer>>();

    Graph(int n){
    	for(int i=0; i<n; i++) {
    		adj.add(new LinkedList<Integer>());
    	}
    }

    /*
     * -1, because first stick should have index 0, but is called "1" in the inout
     */
    public void addUndirectedEdge(int a, int b){
    	adj.get(a-1).add(b-1);
    	adj.get(b-1).add(a-1);
    }

    public void addDirectedEdge(int a, int b){
    	adj.get(a-1).add(b-1);
    }

    public LinkedList<Integer> getNeighbours(int v){
        return adj.get(v);
    }

    public ArrayList<LinkedList<Integer>> getGraph(){
    	return adj;
    }

    public int getNodeCount(){
    	return adj.size();
    }

}
