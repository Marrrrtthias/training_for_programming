import java.util.*;

/*
 * Key idea:
 * we want to find the longest increasing common subsequence between the IQ values (sorted after the weights) and the IQ values (sorted after the IQ)
 */
public class PotatoFarmers {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		/*
		 * input as always...
		 */
	      int t = 0;
	      t = sc.nextInt();
	      //sc.nextLine();


	      for(int i=1; i<=t; i++) {
	    	  /*
	    	   * bauerzW = lexicographically sorted after 1. weight, 2. iq desc
	    	   */
	    	  ArrayList<Bauer> bauerzW = new ArrayList<Bauer>();
	    	  /*
	    	   * bauerzW = sorted after weight
	    	   */
	    	  ArrayList<Bauer> bauerzIQ;

	    	  int n = sc.nextInt();

	    	  /*
	    	   * add all the farmers
	    	   */
	    	  for(int j=0; j<n ; j++) {
	    		  int iq = sc.nextInt();
	    		  int w = sc.nextInt();
	    		  Bauer temp = new Bauer((10000+j), iq, w);
	    		  bauerzW.add(temp);
	    	  }


	    	  Collections.sort(bauerzW, new WeightComparator());

	    	  /*
	    	   * Cover case that some farmers have equal weights.
	    	   * Out of all the farmers with the same weight, we keep the one with an IQ which has the highest iq out of them all but where the iq is just below the iq of the farmer with the next-smaller weight
	    	   *
	    	   * consider:
	    	   * (100,1000),(90,1100),(105,1050),(95,1050),(85,1050), we want to keep (95,1050)
	    	   */

	    	  /*
	    	   * dynamic size of Bauer-Collection
	    	   */
	    	  int s = bauerzW.size();
	    	  /*
	    	   * Index of node we look at
	    	   */
	    	  int v = 0;
	    	  while(v<s-1) {
	    		  /*
	    		   * please remember the lexicographical order of bauerzW
	    		   */
	    		 while(v<s-1 && bauerzW.get(v).w == bauerzW.get(v+1).w){
	    			 /*
	    			  * Remark to "Math.max"
	    			  * if the first Bauer has the same weight as the second, we want to keep the first one (the comparison will evaluate to false if this is the case)
	    			  */
	    			 if(bauerzW.get(v).iq > bauerzW.get(Math.max(0,v-1)).iq){
	    				 bauerzW.remove(v);
	    				 /*
	    				  * bauerzW.get(v) changed
	    				  */
	    			 } else {
	    				 bauerzW.remove(v+1);
	    				 /*
	    				  * bauerzW.get(v+1) changed
	    				  */
	    			 }
	    			 /*
	    			  * in any case, one Bauer got deleted
	    			  */
	    			 s--;
	    		 }
	    		 v++;
	    	  }


	    	  bauerzIQ = new ArrayList<Bauer>(bauerzW);
	    	  Collections.sort(bauerzIQ, new IQComparator());
	    	  Collections.reverse(bauerzIQ);

	    	  /*
	    	   * extract the value which gives us the longest increasing common subsequence between the whole Lists
	    	   */
	    	  int lics = alignmentScore(bauerzW,bauerzIQ)[bauerzW.size()-1+1][bauerzIQ.size()-1+1];

	    	  System.out.println("Case #"+i+": "+lics);
	      }
	}



	/*
	 * see handout for explanation
	 */
	public static int[][] alignmentScore(ArrayList<Bauer> x, ArrayList<Bauer> y) {
		int n = x.size();
		int m = y.size();

		int[][] S = new int[n+1][m+1];

		for(int i=0 ; i<=n ; i++) {
			S[i][0] = 0; //-i
		}
		for(int j=0 ; j<=m ; j++) {
			S[0][j] = 0; //-j
		}

		for(int i=1 ; i<=n ; i++) {
			for (int j=1 ; j<=m ; j++) {
//				System.out.println("Betrachtet: ("+i+","+j+")"); //TODO debug
				int scoreIns = S[i][j-1] +0;
//				System.out.println("ScoreIns: "+scoreIns); //TODO debug
				int scoreDel = S[i-1][j] +0;
//				System.out.println("ScoreDel: "+scoreDel); //TODO debug
				boolean isMatch = x.get(i-1).iq == y.get(j-1).iq;
//				System.out.println("Match: "+isMatch); //TODO debug
				int scoreM = S[i-1][j-1] + (isMatch ? 1 : 0);
//				System.out.println("ScoreM: "+scoreM); //TODO debug
				S[i][j] = Math.max(scoreIns, Math.max(scoreDel, scoreM));
//				System.out.println("max: "+S[i][j]); //TODO debug
			}
		}
		return S;
	}

//		/*TODO why are the two left out lines neccasary*/
//
//		int[][] S = alignmentScore(x,y);
//		int n = S.length;
//		int m = S[0].length;
//		int i = n;
//		int j = m;
//		StringBuilder res = new StringBuilder();
//
//		while( i>0 && j>0 ) {
//			int scoreIns = S[i][j-1] +0;
//			int scoreDel = S[i-1][j] +0;
//			boolean isMatch = x.charAt(i-1) == y.charAt(j-1);
//			int scoreM = S[i-1][j-1] + (isMatch ? 1 : 0);
//			if(S[i][j] == scoreM){
//				res.append(isMatch ? '|' : 'X');
//				i--;
//				j--;
//			}
//			else if(S[i][j] == scoreIns){
//				res.append('I');
//				j--;
//			}
//			else {
//				res.append('D');
//				i--;
//			}
//		}
//
//		while(i>0){
//			res.append('D');
//			i--;
//		}
//		while(j>0){
//			res.append('I');
//			j--;
//		}
//
//		return (res.reverse().toString());
//
//	}

}

/*
 * represents a farmer, but the german word sounds way cooler
 */
class Bauer{
	/*
	 * 5 digits, because n is at most 20000
	 * TODO: probably not necessary
	 */
	int id;

	/*
	 * iq
	 */
	int iq;

	/*
	 * weight
	 */
	int w;

	Bauer(int id, int iq, int w){
		this.id=id;
		this.iq=iq;
		this.w=w;
	}
}

/*
 * Comparator to lexicographically sort Collection of Bauer by 1.w 2.iq desc
 */
class WeightComparator implements Comparator<Bauer> {
	@Override
	public int compare(Bauer first, Bauer second){
		if(first.w < second.w) {
			return -1;
		} else if(first.w > second.w) {
			return 1;
		} else {
			if(first.iq<second.iq) {
				return 1;
			} else if (first.iq>second.iq) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

/*
 * Comparator to sort Collection of Bauer by iq
 */
class IQComparator implements Comparator<Bauer> {
	@Override
	public int compare(Bauer first, Bauer second){
		int c;
		if(first.iq < second.iq) {
			return -1;
		} else if(first.iq > second.iq) {
			return 1;
		} else {
			return 0;
		}
	}
}