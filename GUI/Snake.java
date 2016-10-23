public class Snake {

	private int size;
	private int x;
	private int y;
	private final int[] GROWTH = {1, 2, 4, 8, 16};
	private int selectedGrowth = 1;
	private String direction = "NONE";

	public Snake(int x, int y, int size, int selectedGrowth) {

		this.size = size;
		this.x = x;
		this.y = y;
		this.selectedGrowth = selectedGrowth;
	}

	public int getGrowth() {

		return GROWTH[selectedGrowth];
	}

	public void toggleGrowth() {

		selectedGrowth = selectedGrowth == GROWTH.length - 1 ? 0 : selectedGrowth + 1;
	}

	public void setDirection(String direction) {

		this.direction = direction;
	}

	public String getDirection() {

		return direction;
	}

	public int getSize() {
		
		return size;
	}

	public void incrementSize() {

		size += GROWTH[selectedGrowth];
	}

	public void setX(int x) {

		this.x = x;
	}

	public int getX() {

		return x;
	}

	public void setY(int y) {

		this.y = y;
	}

	public int getY() {

		return y;
	}
}