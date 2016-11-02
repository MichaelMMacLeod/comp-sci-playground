public class Joo {

	private double x, y, diameter;
	private double direction = Math.random() * Math.PI * 2;
	private final double SPEED = 2;

	public Joo(double diameter) {

		this.diameter = diameter;

		initPos();
	}

	public double getDiameter() { return diameter; };
	public double getX() { return x; }
	public double getY() { return y; }

	private void initPos() {

		double rand = Math.random() * Math.PI * 2;

		x = Math.cos(rand) * 200 + 250 - 50 / 2;
		y = Math.sin(rand) * 200 + 250 - 50 / 2;
	}

	public void move() {

		x += Math.cos(direction) * SPEED;
		y += Math.sin(direction) * SPEED;
	}
}