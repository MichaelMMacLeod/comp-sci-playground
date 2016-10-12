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

		Scanner scan = new Scanner(System.in);
		boolean[][] map = new boolean[20][20];
		boolean selecting = true;

		System.out.println("Enter speed (#/sec)");
		int interval = scan.nextInt();
		while (selecting) {
			System.out.println("Enter seed (x coordinate)");
			int x = scan.nextInt();
			System.out.println("Enter seed (y coordinate)");
			int y = scan.nextInt();
			try {
				map[y][x] = true;
			} catch (Exception e) {
				selecting = false;
			}
			printMap(map);
		}
		while (!Arrays.deepEquals(map, step(map))) {
			map = step(map);
			printMap(map);
			try {
				Thread.sleep(1000 / interval);
			} catch (Exception e) {}
		}
		System.out.println("Done!");
	}

	public static boolean[][] step(boolean[][] oldMap) {

		boolean[][] map = new boolean[oldMap.length][oldMap.length];

		for (int i = 0; i < oldMap.length; i++) {
			for (int j = 0; j < oldMap.length; j++) {

				int neighbours = 0;
				// Make the game board wrap along edges
				// left, up, right, down
				int l = i == 0 ? oldMap.length - 1: i - 1;
				int u = j == 0 ? oldMap.length - 1: j - 1;
				int r = i == oldMap.length - 1? 0 : i + 1;
				int d = j == oldMap.length - 1? 0 : j + 1;

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