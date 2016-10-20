public class Grid {

	private final int size, tileSize, buffer;
	private int[][] map;

	/** Creates a square grid with height and width equal to size */
	public Grid(int size, int tileSize, int buffer) {

		this.size = size;
		this.tileSize = tileSize;
		this.buffer = tileSize + buffer;
		this.map = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				setCell(i, j, 0);
			}
		}
	}

	public int getBuffer() {

		return buffer;
	}

	public int getSize() {

		return size;
	}

	public int getTileSize() {

		return tileSize;
	}

	/** Returns the value of the cell at (x,y) */
	public int getCell(int x, int y) {

		return map[x][y];
	}

	/** Sets the number stored in cell (x,y) to value */
	public void setCell(int x, int y, int value) {

		map[x][y] = value;
	}

	/** Adds a positive or negative value to the cell at (x,y) */
	public void addValueToCell(int x, int y, int value) {

		map[x][y] += value;
	}

	/** Increments cell (x,y) by 1 */
	public void incrementCell(int x, int y) {

		map[x][y] += 1;
	}

	/** Decrements cell (x,y) by 1 */
	public void decrementCell(int x, int y) {
		
		if (map[x][y] > 0) {
			map[x][y] -= 1;
		}
	}
}