public class Snake {

	private int size;
	private int x;
	private int y;
	private String direction = "NONE";

	public Snake(int x, int y, int size) {

		this.size = size;
		this.x = x;
		this.y = y;
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

	public void setSnoutX(int x) {

		this.x = x;
	}

	public int getSnoutX() {

		return x;
	}

	public void setSnoutY(int y) {

		this.y = y;
	}

	public int getSnoutY() {

		return y;
	}
}