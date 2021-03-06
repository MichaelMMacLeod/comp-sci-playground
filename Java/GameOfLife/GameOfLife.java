import java.util.Scanner;
import java.util.Arrays;

public class GameOfLife {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {

        int size;
        boolean[][] map;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter size of the grid");
        size = scan.nextInt();
        map = new boolean[size][size];
        System.out.println("Enter speed (#/sec)");
        int interval = scan.nextInt();
        map = seed(map);
        while (!Arrays.deepEquals(map, step(map))) {
            map = step(map);
            printMap(map);
            try {
                Thread.sleep(1000 / interval);
            } catch (Exception e) {}
        }
        System.out.println("Done!");
    }

    public static boolean[][] seed(boolean[][] map) {

        boolean selecting = true;
        Scanner scan = new Scanner(System.in);

        while (selecting) {

            String[] textInput;
            int[] input;

            System.out.println("Enter a coordinate x,y");
            textInput = scan.nextLine().replaceAll("\\s", "").split(",");
            input = new int[textInput.length];
            for (int i = 0; i < input.length; i++) {
                input[i] = Integer.parseInt(textInput[i]);
            } 
            try {
                map[input[1]][input[0]] = true;
            } catch (Exception e) {
                selecting = false;
            }
            printMap(map);
        }
        return map;
    }

    public static boolean[][] step(boolean[][] oldMap) {

        boolean[][] map = new boolean[oldMap.length][oldMap.length];

        for (int i = 0; i < oldMap.length; i++) {
            for (int j = 0; j < oldMap.length; j++) {

                int neighbours = 0;
                // Make the game board wrap along edges
                // left, up, right, down
                int l = i == 0 ? oldMap.length - 1 : i - 1;
                int u = j == 0 ? oldMap.length - 1 : j - 1;
                int r = i == oldMap.length - 1 ? 0 : i + 1;
                int d = j == oldMap.length - 1 ? 0 : j + 1;

                if (oldMap[l][u]) neighbours++;
                if (oldMap[l][j]) neighbours++;
                if (oldMap[l][d]) neighbours++;
                if (oldMap[i][d]) neighbours++;
                if (oldMap[r][d]) neighbours++;
                if (oldMap[r][j]) neighbours++;
                if (oldMap[r][u]) neighbours++;
                if (oldMap[i][u]) neighbours++;
                if (neighbours < 2) 
                    map[i][j] = false;
                if ((neighbours == 2 || neighbours == 3) && oldMap[i][j] == true)
                    map[i][j] = true;
                if (neighbours > 3) 
                    map[i][j] = false;
                if (neighbours == 3 && oldMap[i][j] == false)
                    map[i][j] = true;
            }
        }
        return map;
    }

    public static void printMap(boolean[][] map) {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j]) {
                    System.out.print(ANSI_BLUE + "O");
                } else {
                    System.out.print(ANSI_WHITE + "O");
                }
            }
            System.out.println(ANSI_WHITE);
        }
    }
}