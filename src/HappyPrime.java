import java.util.Scanner;

/**
 * Created by matthias on 5/16/17.
 */
public class HappyPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            String[] input = scanner.nextLine().split(" ");
            if (isHappy(input[1]) && isPrime(input[1])) {
                System.out.println(input[0] + " " + input[1] + " YES");
            } else {
                System.out.println(input[0] + " " + input[1] + " NO");
            }
        }
    }

    private static boolean isHappy(String s) {
        long next = 0;
        for (int i = 0; i < s.length(); i++) {
            next += Math.pow(Integer.parseInt(""+s.charAt(i)), 2);
        }
        if (next == 1) {
            return true;
        }
        if (next == 4 || next == 16 || next == 145 || next == 20) {
            return false;
        }
        return isHappy(Long.toString(next));
    }

    private static boolean isPrime(String s) {
        long number = Integer.parseInt(s);
        if (number == 0 || number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        for (long d = 2; d < Math.sqrt(number)+1; d++) {
            if (number%d == 0) {
                return false;
            }
        }
        return true;
    }
}
