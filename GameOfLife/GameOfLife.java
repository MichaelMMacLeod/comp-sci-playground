import java.util.Scanner;

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

        Scanner scan = new Scanner(System.in);
        boolean[][] map = new boolean[20][20];
        boolean selecting = true;

        while (selecting) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            try {
                map[x][y] = true;
            } catch (Exception e) {
                selecting = false;
            }
            printMap(map);
        }
        while (true) {
            map = step(map);
            printMap(map);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }

    public static boolean[][] step(boolean[][] oldMap) {

        boolean[][] map = new boolean[oldMap.length][oldMap.length];
        
        for (int i = 0; i < oldMap.length; i++) {
            for (int j = 0; j < oldMap.length; j++) {

                int neighbours = 0;

                // there has to be a better way
                try {
                    if (oldMap[i - 1][j - 1]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i - 1][j + 0]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i - 1][j + 1]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i + 0][j + 1]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i + 1][j + 1]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i + 1][j + 0]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i + 1][j - 1]) neighbours++;
                } catch (Exception e) {};
                try {
                    if (oldMap[i + 0][j - 1]) neighbours++;
                } catch (Exception e) {};
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
                    System.out.print(ANSI_GREEN + "1");
                } else {
                    System.out.print(ANSI_WHITE + "0");
                }
            }
            System.out.println(ANSI_WHITE);
        }
    }
}