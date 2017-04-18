import java.util.Scanner;

/**
 * Created by matthias on 4/18/17.
 */
public class HelloWorld {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for(int i = 1; i <= numCases; i++) {
            String name = scanner.nextLine();
            System.out.println("Case #" + i + ": Hello " + name + "!");
        }
    }
}
