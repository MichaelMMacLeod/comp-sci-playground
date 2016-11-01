public class Joo {

	private double x;
	private double y;
	private double direction = Math.random() * Math.PI * 2;

	public Joo() {

		initPos();
	}

	private void initPos() {

		double rand = Math.random() * Math.PI * 2;

		x = Math.cos(rand);
		y = Math.cos(rand);
	}

	public void move() {

		x += Math.cos(direction);
		y += Math.sin(direction);
	}

	public double getX() { return x; }
	public double getY() { return y; }
}