public class Snake {

	private int size;
	private int x;
	private int y;

	public Snake(int x, int y, int size) {

		this.size = size;
		this.x = x;
		this.y = y;
	}

	public int getSize() {
		
		return size;
	}

	public int getSnoutX() {

		return x;
	}

	public int getSnoutY() {

		return y;
	}
}