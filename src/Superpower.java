import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by matthias on 6/13/17.
 */
public class Superpower {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();

            String dnaString = scanner.nextLine();
            String sequenceString = scanner.nextLine();

            int i = 0;
            Set<Integer> occurences = new TreeSet<>();
            while (i < dnaString.length()) {
                int index = dnaString.indexOf(sequenceString, i);//firstIndexOf(dnaString, sequenceString, i);
                if (index == -1)
                    break;
                occurences.add(index);
                i++;
            }

            System.out.println("Case #" + currCase + ": " + occurences.size());
        }
    }
}
