public class Map {

	int[][] tiles;

	/**
	 * Creates a tile layout
	 * @param width is the number of tiles in the x direction
	 * @param height is the number of tiles in the y direction
	 */
	public Map(int width, int height) {
		this.tiles = new int[width][height];
	}

	public int[][] getTiles() {
		return this.tiles;
	}
}