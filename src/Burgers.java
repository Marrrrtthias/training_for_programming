import java.util.Scanner;

/**
 * Created by matthias on 5/23/17.
 */
public class Burgers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            String[] input = scanner.nextLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int numBurgers = Integer.parseInt(input[1]);

            double alpha = (2*Math.PI) / (numBurgers);
            double R = r / Math.sin(alpha/2);
            R += r;

            if (numBurgers == 1)
                R = r;

            System.out.println("Case #" + currCase + ": " + R);
        }
    }
}
