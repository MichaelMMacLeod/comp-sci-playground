public class Snake {

	private int size;
	private int x;
	private int y;
	private int growth;
	private String direction = "NONE";

	public Snake(int x, int y, int size, int growth) {

		this.size = size;
		this.x = x;
		this.y = y;
		this.growth = growth;
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

		size += growth;
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