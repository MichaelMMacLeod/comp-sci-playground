public class Ship {

	private int[] x; // the x coordinates of the ship parts
	private int[] y; // the y coordinates of the ship parts
	private int size;
	private int max; // length of map array - 1, to prevent out of bound errors
	private boolean horizontal;
	private static Ship[] ships = new Ship[0];

	public int[] getX() { return x; }
	public int[] getY() { return y; }

	// Returns the map with ships on it
	public static int[][] anchor(int[][] map) {
		int[][] anchoredMap = new int[map.length][map[0].length];
		for (Ship ship : ships) {
			for (int i = 0; i < ship.getSize(); i++) {
				if (anchoredMap[ship.getY()[i]][ship.getX()[i]] == 1) {
					System.out.println("Conflict!");
				}
				anchoredMap[ship.getY()[i]][ship.getX()[i]] += 1;
			}
		}

		for (int i = 0; i < anchoredMap.length; i++) {
			for (int j = 0; j < anchoredMap[i].length; j++) {
				System.out.print(anchoredMap[j][i] + " ");
			}
			System.out.println();
		}
		return anchoredMap;
	}

	/**
	 * Returns true if the ship will not overlap any other ships or go off the
	 * map in the location (x,y)
	 */
	private boolean legalPos(int x, int y) {
		for (Ship otherShip : ships) {
			for (int i = 0; i < otherShip.getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					if ((horizontal 
						&& otherShip.getX()[i] == x + j 
						&& otherShip.getY()[i] == y)
						|| (x + j > max || y > max)) {
						return false;
					} else if ((!horizontal 
						&& otherShip.getX()[i] == x
						&& otherShip.getY()[i] == y + j)
						|| (x > max || y + j > max)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Ship(int size, int[][] map, GamePanel panel) {

		max = map.length - 1;

		this.size = size;
		horizontal = (int) (Math.random() * 2 + 1) % 2 == 0;

		int[] possibleXPositions = new int[0];
		int[] possibleYPositions = new int[0];

		int xSub = horizontal ? getSize() : 0;
		int ySub = horizontal ? 0 : getSize();
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {

				if (legalPos(x, y)) {

					int[] possXNew = new int[possibleXPositions.length + 1];
					for (int i = 0; i < possibleXPositions.length; i++) {
						possXNew[i] = possibleXPositions[i];
					}
					possXNew[possXNew.length - 1] = x;
					possibleXPositions = possXNew;

					int[] possYNew = new int[possibleYPositions.length + 1];
					for (int i = 0; i < possibleYPositions.length; i++) {
						possYNew[i] = possibleYPositions[i];
					}
					possYNew[possYNew.length - 1] = x;
					possibleYPositions = possYNew;
				}
			}
		}

		x = new int[getSize()];
		y = new int[getSize()];

		int rand = (int) (Math.random() * possibleXPositions.length);
		for (int i = 0; i < getSize(); i++) {
			if (horizontal) {
				x[i] = possibleXPositions[rand] + i;
				y[i] = possibleYPositions[rand];
			} else {
				x[i] = possibleXPositions[rand];
				y[i] = possibleYPositions[rand] + i;
			}
		}

		Ship[] shipsNew = new Ship[ships.length + 1];
		for (int i = 0; i < ships.length; i++) {
			shipsNew[i] = ships[i];
		}
		shipsNew[shipsNew.length - 1] = this;
		ships = shipsNew;
	}

	private int getSize() { return size; }
}