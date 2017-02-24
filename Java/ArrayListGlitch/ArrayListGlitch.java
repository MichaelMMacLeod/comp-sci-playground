import java.util.Arrays;
import java.util.ArrayList;

class ArrayListGlitch {
    public static void main(String[] args) {
        new ArrayList<Integer>(Arrays.asList(new Integer[] {9, 6, 7, 4, 2, 3, 1, 5, 8}))
            .stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);
    }
}