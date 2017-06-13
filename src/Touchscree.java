import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by matthias on 6/13/17.
 */
public class Touchscree {
    static Map<Character, int[]> keyboard = new TreeMap<>();

    public static void main(String[] args) {
        keyboard.put('q', new int[]{0,0});
        keyboard.put('w', new int[]{1,0});
        keyboard.put('e', new int[]{2,0});
        keyboard.put('r', new int[]{3,0});
        keyboard.put('t', new int[]{4,0});
        keyboard.put('y', new int[]{5,0});
        keyboard.put('u', new int[]{6,0});
        keyboard.put('i', new int[]{7,0});
        keyboard.put('o', new int[]{8,0});
        keyboard.put('p', new int[]{9,0});

        keyboard.put('a', new int[]{0,1});
        keyboard.put('s', new int[]{1,1});
        keyboard.put('d', new int[]{2,1});
        keyboard.put('f', new int[]{3,1});
        keyboard.put('g', new int[]{4,1});
        keyboard.put('h', new int[]{5,1});
        keyboard.put('j', new int[]{6,1});
        keyboard.put('k', new int[]{7,1});
        keyboard.put('l', new int[]{8,1});

        keyboard.put('z', new int[]{0,2});
        keyboard.put('x', new int[]{1,2});
        keyboard.put('c', new int[]{2,2});
        keyboard.put('v', new int[]{3,2});
        keyboard.put('b', new int[]{4,2});
        keyboard.put('n', new int[]{5,2});
        keyboard.put('m', new int[]{6,2});

        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");
            String typed = input[0];
            Entry[] dict = new Entry[Integer.parseInt(input[1])];

            for (int i = 0; i < dict.length; i++) {
                dict[i] = new Entry(scanner.nextLine());
                dict[i].dist = dist(typed, dict[i].s);
            }

            Arrays.sort(dict);

            System.out.println("Case #" + currCase + ":");
            for (Entry e : dict) {
                System.out.println(e);
            }
        }
    }

    private static int dist(String a, String b) {
        int res = 0;
        for (int i = 0; i < a.length(); i++)
            res += dist(a.charAt(i), b.charAt(i));
        return res;
    }

    private static int dist(char c, char c1) {
        int[] a = keyboard.get(c);
        int[] b = keyboard.get(c1);

        return (Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]));
    }
}

class Entry implements Comparable {
    String s;
    int dist;

    public Entry(String s) {
        this.s = s;
        this.dist = 0;
    }

    @Override
    public String toString() {
        return s + " " + dist;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o)
            return 0;

        Entry b = (Entry) o;

        if (this.dist != b.dist)
            return Integer.compare(this.dist, b.dist);

        return this.s.compareTo(b.s);
    }
}
