import java.util.*;

/**
 * Created by matthias on 5/6/17.
 */
public class Minotaur {
    static Map<Character, List<Character>> caverns;
    static List<Character> candles;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("#")) {
            // read input
            String[] cavernsAndStartPos = input.split("\\. ");
            char minotaurPos = cavernsAndStartPos[1].charAt(0);
            char theseusPos = cavernsAndStartPos[1].charAt(2);
            int k = Integer.parseInt("" + cavernsAndStartPos[1].substring(4));

            caverns = new HashMap<>();

            String[] cavernArray = cavernsAndStartPos[0].split(";");
            for (String cavern : cavernArray) {
                String[] cavernAndNeighbours = cavern.split(":");
                caverns.put(cavernAndNeighbours[0].charAt(0), new LinkedList<>());
                List<Character> neighbours = caverns.get(cavernAndNeighbours[0].charAt(0));
                if (cavernAndNeighbours.length != 2) {
                    continue; // cavern has no neighbours -> read next cavern
                }
                for (int i = 0; i < cavernAndNeighbours[1].length(); i++) {
                    neighbours.add(cavernAndNeighbours[1].charAt(i));
                }
            }

            // run simulation
            candles = new LinkedList<>();
            int numsteps = 0;
            boolean chaseOngoing = true;
            while (chaseOngoing) {
                caverns.putIfAbsent(minotaurPos, new LinkedList<>());
                Iterator<Character> neighbours = caverns.get(minotaurPos).iterator();
                //System.out.println("neigbouring caverns " + caverns.get(minotaurPos));

                if (!neighbours.hasNext()) {
                    //System.out.println("cavern " + minotaurPos + " has no neighbours");
                    // in case there are no neighbouring caverns
                    chaseOngoing = false;
                }

                while (neighbours.hasNext()) {
                    // move minotaur and theseus if applicable
                    char current = neighbours.next();
                    //System.out.println("checking cavern " + current);
                    if (!candles.contains(current) && current!=theseusPos) {
                        theseusPos = minotaurPos;
                        //System.out.println("theseus moved to " + theseusPos);
                        minotaurPos = current;
                        //System.out.println("minotaur moved to " + minotaurPos);
                        numsteps++;
                        break;
                    }
                    if (!neighbours.hasNext()) {
                        chaseOngoing = false;
                        break;
                    }
                }

                if (numsteps == k) {
                    // light candle
                    candles.add(theseusPos);
                    numsteps = 0;
                    //System.out.println("candle lit in " + theseusPos);
                }
            }

            for (char candle : candles) {
                System.out.print(candle + " ");
            }
            System.out.println("/" + minotaurPos);
        }

    }
}
