public class Ship {

	private int[] x; // the x coordinates of the ship parts
	private int[] y; // the y coordinates of the ship parts
	private int size;
	private boolean horizontal;

	public int[] getX() { return x; }
	public int[] getY() { return y; }

	public Ship(int size, int[][] map, GamePanel panel) {

		this.size = size;
		horizontal = (int) (Math.random() * 2 + 1) % 2 == 0;
		
		// Choose a random position for the ship which does not overlap other
		// ships or go out of bounds of the map
		int[] xPositions = {};
		int[] yPositions = {};
		boolean[][] possiblePositions = possiblePos(map);
		System.out.println("Possible Positions:");
		for (int i = 0; i < possiblePositions.length; i++) {
			for (int j = 0; j < possiblePositions[i].length; j++) {
				System.out.print((possiblePositions[i][j] ? 1 : 0) + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < possiblePositions.length; i++) {
			for (int j = 0; j < possiblePositions[i].length; j++) {

				if (possiblePositions[i][j]) {

					int[] xPositionsNew = new int[xPositions.length + 1];
					for (int k = 0; k < xPositions.length; k++) {
						xPositionsNew[k] = xPositions[k];
					}
					xPositionsNew[xPositionsNew.length - 1] = j;
					xPositions = xPositionsNew;

					int[] yPositionsNew = new int[yPositions.length + 1];
					for (int k = 0; k < yPositions.length; k++) {
						yPositionsNew[k] = yPositions[k];
					}
					yPositionsNew[yPositionsNew.length - 1] = i;
					yPositions = yPositionsNew;
				}
			}
		}

		x = new int[getSize()];
		y = new int[getSize()];

		int xStart = xPositions[(int) (Math.random() * xPositions.length)];
		int yStart = yPositions[(int) (Math.random() * yPositions.length)];

		for (int i = 0; i < x.length; i++) {
			x[i] = horizontal ? xStart + i : xStart;
			y[i] = horizontal ? yStart : yStart + i;
		}

		for (int i = 0; i < x.length; i++) {
			panel.setCell(x[i], y[i], 1);
		}
	}


	private int getSize() { return size; }

	/**
	 * Returns a list of possible ship positions
	 * A cell in ans is false when the ship would overlap another ship, or go
	 * out of bounds of the map
	 */
	public boolean[][] possiblePos(int[][] map) {

		boolean[][] ans = new boolean[map.length][map.length];
		
		// Set cells which are out of bounds as false, everything else true
		for (int r = 0; r < ans.length; r++) {
			for (int c = 0; c < ans[r].length; c++) {
				try {
					if (horizontal) {
						ans[r][c + getSize()] = true;
					} else {
						ans[r + getSize()][c] = true;
					}
				} catch (Exception e) {
					ans[r][c] = false;
					System.out.println(r + " " + c);
				}
			}
		}

		System.out.println("horizontal? " + horizontal);
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
		// Set non out of bounds cells as false if they would overlap a ship
		for (int r = 0; r < ans.length; r++) {
			for (int c = 0; c < ans[r].length; c++) {

				for (int k = 0; k < getSize(); k++) {

					if (ans[r][c] && horizontal && map[r][c + k] != -1) {
						ans[r][c] = false;
						break;
					}
					if (ans[r][c] && !horizontal && map[r + k][c] != -1) {
						ans[r][c] = false;
						break;
					}
				}
			}
		}

		return ans;
	}
}