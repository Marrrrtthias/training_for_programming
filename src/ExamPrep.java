import java.util.*;

/**
 * Created by matthias on 6/6/17.
 */
public class ExamPrep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");
            int numTopics = Integer.parseInt(input[1]);
            int sheetLength = Integer.parseInt(input[0]);

            int[] lengths = new int[numTopics];
            int[] values = new int[numTopics];

            SortedMap<Float, Integer> scores = new TreeMap<>(new Comparator<Float>() {
                @Override
                public int compare(Float o1, Float o2) {
                    return -1 * o1.compareTo(o2);
                }
            });

            for (int currTopic = 0; currTopic < numTopics; currTopic++) {
                input = scanner.nextLine().split(" ");
                lengths[currTopic] = Integer.parseInt(input[0]);
                values[currTopic] = Integer.parseInt(input[1]);
                scores.put((float)values[currTopic]/lengths[currTopic], currTopic);
            }

            int remainingSpace = sheetLength;
            List<Integer> sheet = new LinkedList<>();
            for (float score : scores.keySet()) {
                int length;
                while ((length = lengths[scores.get(score)]) <= remainingSpace) {
                    sheet.add(scores.get(score));
                    remainingSpace -= length;
                }
            }

            System.out.println("Case #" + currCase + ":" + toString(sheet));
        }
    }

    private static String toString(List<Integer> sheet) {
        StringBuilder out = new StringBuilder();
        for (int i : sheet) {
            out.append(" ");
            out.append(i+1);
        }
        return out.toString();
    }
}
