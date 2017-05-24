
import java.util.*;

public class Railroad {
		  public static void main(String[] args) {

			  Scanner sc = new Scanner(System.in);

	    	  int n = sc.nextInt();
	    	  int m = sc.nextInt();
	    	  int l = sc.nextInt();

		      sc.nextLine();

		    	  ArrayList<LinkedList<Entry>> adj = new ArrayList<LinkedList<Entry>>();

		    	  /*
		    	   * initialize n nodes
		    	   */
		    	  for(int f=0; f<n; f++) {
		    		  adj.add(new LinkedList<Entry>());
		    	  }

		    	  int actWeight = 0;

		    	  /*
		    	   * initialize l established connections
		    	   */
		    	  for(int j=0;j<l;j++){
			    		  int stat1 = sc.nextInt()-1;
			    		  int stat2 = sc.nextInt()-1;
			    		  double weight = sc.nextInt();

		    		  //addEdges
		    		  adj.get(stat1).add(new Entry(stat2,weight));
		    		  adj.get(stat2).add(new Entry(stat1,weight));

		    		  /*
		    		   * keep track of the total weight
		    		   */
		    		  actWeight+=weight;
		    	  }

		    	  /*
		    	   * initialize m-l possible but not established connections
		    	   */
		    	  for(int j=l;j<(m);j++){
		    		  int stat1 = sc.nextInt()-1;
		    		  int stat2 = sc.nextInt()-1;
		    		  double weight = sc.nextInt();

	    		  //addEdges
	    		  adj.get(stat1).add(new Entry(stat2,weight));
	    		  adj.get(stat2).add(new Entry(stat1,weight));
		    	  }



		    	  /*
		    	   * Construct MST using some algo ;)
		    	   */
		    	  int s = 0;
		    	  int[] father = new int[n];
		    	  double[] dist = new double[n];
		    	  boolean[] visited = new boolean[n];
		    	  Arrays.fill(dist,Double.POSITIVE_INFINITY);
		    	  Arrays.fill(father,  -1);
		    	  TreeSet<PQEntry> pq = new TreeSet<>();
		    	  dist[s]=0;
		    	  pq.add(new PQEntry(0,s));
		    	  while(!pq.isEmpty()) {
		    		  PQEntry closest = pq.pollFirst();
		    		  int v = closest.item; //clostest.node
		    		  visited[v]=true;
		    		  for (Entry entry : (adj.get(v))) {
		    			  Integer w = entry.node;
		    			  if (visited[w]) continue;
		    			  double d = entry.weight;
		    			  if(dist[w]>d) {
		    				  pq.remove(new PQEntry(dist[w],w));
		    				  dist[w] = d;
		    				  father[w]=v;
		    				  pq.add(new PQEntry(dist[w],w));
		    			  }

		    		  }

		    	  }


		    	  /*
		    	   * calc sum of the weights of the edges of the mst
		    	   */
		    	  int MSTweight = 0;
		    	  for(int k = 0; k<dist.length; k++) {
		    		  MSTweight+=dist[k];
		    	  }


		    	  /*
		    	   * Check, if the sum of all weights of the MST are smaller or equal the sum of the rails you have (actWeight)
		    	   */
		    	  if(!((actWeight)<(MSTweight))) {
		    		  System.out.println("possible");
		    	  } else {
		    		  System.out.println("impossible");
		    	  }



		     sc.close();
		    }

}


/*
 * Node with edge weight
 */
class Entry {
	int node;
	double weight;

	Entry(int n, double w) {
		this.node = n;
		this.weight = w;
	}
}


/*
 * Modeling PriorityQueue in Java
 */
class PQEntry implements Comparable<PQEntry> {

	double priority;
	Integer item;

	PQEntry(double p, int i) {
		this.priority=p;
		this.item=i;
	}

	@Override
	public int compareTo(PQEntry o) {
		final int cmp = Double.compare(this.priority, o.priority);
		return (cmp != 0 ? cmp : this.item.compareTo(o.item));
	}
}
