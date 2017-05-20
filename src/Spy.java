import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by matthias on 5/20/17.
 */
public class Spy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            String[] input = scanner.nextLine().split(" ");
            int[] digits = toIntArray(input[1].toCharArray());
            Arrays.sort(digits);

            int max = toDecimal(digits);

            boolean[] primes = sieb(max);

            int numPrimes = 0;
            for (int i = 2; i < primes.length; i++) {
                if (primes[i] && buildableFrom(i, digits)) {
                    numPrimes++;
                }
            }

            System.out.println("Case #" + currCase + ": " + numPrimes);
        }
    }

    static boolean buildableFrom(int number, int[] digits) {
        int[] in = toIntArray(Integer.toString(number).toCharArray());
        boolean[] used = new boolean[digits.length];

        for (int i = 0; i < in.length; i++) {
            boolean ok = false;
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == in[i] && !used[j]) {
                    used[j] = true;
                    ok = true;
                    break;
                }
            }
            if (!ok)
                return false;
        }
        return true;
    }

    static boolean[] sieb(int max) {
        boolean[] res = new boolean[max+1];
        Arrays.fill(res, true);

        res[0] = false; if (res.length >1) res[1] = false;

        for (int i = 2; i < res.length; i++) {
            if (res[i]) {
                for (int j = 2*i; j < res.length; j += i) {
                    res[j] = false;
                }
            }
        }

        return res;
    }

    static boolean arrayContains(int[] arr, int a) {
        for (int i : arr) {
            if (i == a)
                return true;
        }
        return false;
    }

    private static int toDecimal(int[] digits) {
        int res = 0;
        for (int i = 0; i < digits.length; i++) {
            res += digits[i] * Math.pow(10, i);
        }
        return res;
    }

    static int[] toIntArray(char[] in) {
        int[] out = new int[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] = in[i] - '0';
        }

        return out;
    }
}
