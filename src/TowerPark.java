import java.util.*;

/**
 * Created by matthias on 6/20/17.
 */
public class TowerPark {
    static int[][] tower;
    static int h, w, time;
    static int[] pos;
    static ArrayList<Car> cars;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int currCase = 1; currCase <= numCases; currCase++) {
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");

            h = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);
            tower = new int[h][w];
            cars = new ArrayList<>();
            pos = new int[h];
            time = 0;

            for (int i = 0; i < tower.length; i++) {
                input = scanner.nextLine().split(" ");
                for (int j = 0; j < w; j++) {
                    tower[i][j] = Integer.parseInt(input[j]);
                    if (tower[i][j] > 0) {
                        cars.add(new Car(tower[i][j], i, j));
                    }
                }
            }

            cars.sort(new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    return Integer.compare(o1.index, o2.index);
                }
            });

            //System.out.println(cars);

            for (Car c : cars) {
                getCar(c);
            }

            System.out.println("Case #" + currCase + ": " + time);
        }
    }

    static void getCar(Car car) {
        //System.out.println("getting car: " + car);
        time += car.floor * 10;
        // Standardfall: Foerderband auf direktem Weg drehen
        int rotDist = Math.abs(car.convPos - pos[car.floor]);
        if (rotDist > Math.floor((double) w / 2)) {
            // Spezialfall: Foerderband über Arrayenden hinweg drehen
            rotDist = w - rotDist;
        }
        time += rotDist * 5;
        pos[car.floor] = car.convPos;
        time += car.floor * 10;
    }
}

class Car {
    @Override
    public String toString() {
        return "Car{" +
                "index=" + index +
                ", floor=" + floor +
                ", convPos=" + convPos +
                '}';
    }

    public int index, floor, convPos;

    public Car(int index, int floor, int convPos) {
        this.index = index;
        this.floor = floor;
        this.convPos = convPos;
    }
}
