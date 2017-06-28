import java.util.Scanner;

/**
 * Created by matthias on 6/27/17.
 */
public class Students {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            int numLines = scanner.nextInt();
            scanner.nextLine();

            StringBuilder textBuilder = new StringBuilder();

            for (int line = 0; line < numLines; line++) {
                textBuilder.append(scanner.nextLine());
                textBuilder.append("\n");
            }

            String text = textBuilder.toString();
            text = text.replace("entin", "ierende");
            text = text.replace("enten", "ierende");
            text = text.replace("ent", "ierender");

            System.out.println("Case #" + currCase + ":\n" + text);
        }
    }
}
