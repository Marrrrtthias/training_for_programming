import java.util.Scanner;

/**
 * Created by matthias on 4/22/17.
 */
public class Watson {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            String equation = scanner.nextLine();

            System.out.println("Case #" + currCase + ": " + calcEquation(equation));
        }
    }

    public static int calcEquation(String equation) throws NoNumberException {
        int result = parseNumber(equation, 0);
        int i = Integer.toString(result).length();
        while (i < equation.length()) {
            if (equation.charAt(i) == 'p') {
                // addition (plus)
                i += 4; // "plus" hat 4 buchstaben
                int toAdd = parseNumber(equation, i);
                result = add(result, toAdd);
                i += Integer.toString(toAdd).length(); // laenge der zu addierenden zahl auf index addieren
            } else if (equation.charAt(i) == 'm') {
                // subtraktion (minus)
                i += 5;
                int toSub = parseNumber(equation, i);
                result = sub(result, toSub);
                i += Integer.toString(toSub).length();
            } else if (equation.charAt(i+1) == 'i') {
                // multiplikation (times)
                i += 5;
                int mult = parseNumber(equation, i);
                result = mult(result, mult);
                i += Integer.toString(mult).length();
            } else if (equation.charAt(i+1) == 'o') {
                // potenz (tothepowerof)
                i += 12;
                int exp = parseNumber(equation, i);
                result = (int) Math.pow(result, exp);
                i += Integer.toString(exp).length();
            }
        }

        return result;
    }

    private static int mult(int a, int b) {
        return a*b;
    }

    private static int add(int a, int b) {
        return a+b;
    }

    public static int sub(int a, int b) {
        return a-b;
    }

    private static int parseNumber(String toParse, int start) throws NoNumberException {
        int result = 0, numDigits = 0;
        for (int i = start; i<toParse.length() && Character.isDigit(toParse.charAt(i)); i++) {
            numDigits++;
        }
        if (numDigits == 0) {
            throw new NoNumberException();
        }
        int currPow = numDigits;
        for (int i = start; i < numDigits+start; i++) {
            currPow--;
            result += Integer.parseInt(""+toParse.charAt(i)) * Math.pow(10, currPow);
        }
        return result;
    }

    private static class NoNumberException extends Exception {
    }
}
