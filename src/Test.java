import java.util.LinkedList;
import java.util.List;

/**
 * Created by matthias on 5/16/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(1 + "\n" + 100 + " " + 4537751);
        List<Integer> list = new LinkedList<>();
        list.add(1);

        boolean[] arr = new boolean[1000];
        arr[2] = true;
        for (int i = 3; i < arr.length; i++) {
            arr[i] = true;
        }
        for (int i = 2; i<arr.length; i++) {
            if(!arr[i]) continue;
            list.add(i);
            for (int j = 2*i; j < arr.length; j += i) {
                arr[j] = false;
            }
        }
        int i = list.get(list.size()-1);
        while (list.size() <100) {
            i++;
            list.add(i);
        }

        for (int a : list)
            System.out.print(a + " ");
    }
}
