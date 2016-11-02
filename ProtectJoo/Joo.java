public class Joo {

	private double x;
	private double y;
	private double direction = Math.random() * Math.PI * 2;
	private final double SPEED = 2;

	public Joo() {

		initPos();
	}

	private void initPos() {

		double rand = Math.random() * Math.PI * 2;

		x = Math.cos(rand) * 200 + 250 - 50 / 2;
		y = Math.sin(rand) * 200 + 250 - 50 / 2;
	}

	public void move() {

		x += Math.cos(direction) * SPEED;
		y += Math.sin(direction) * SPEED;
	}

	public double getX() { return x; }
	public double getY() { return y; }
}