public class Food {

	private int x;
	private int y;

	public Food() {}

	public void newLocation(Grid map) {

		int[] possibleX = new int[map.getSize() * map.getSize()];
		int[] possibleY = new int[map.getSize() * map.getSize()];
		int totalPossible = 0;

		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {

				if (map.getCell(i, j) == 0) {
					possibleX[totalPossible] = i;
					possibleY[totalPossible] = j;
					totalPossible++;
				}
			}
		}

		int selected = (int) (Math.random() * totalPossible);
		x = possibleX[selected];
		y = possibleY[selected];
	}

	public int getX() {

		return x;
	}

	public int getY() {
		
		return y;
	}
}