import java.awt.Color;

public class Joo extends Circle {

	private double direction;
	private double nextDirection;
	private double speed = 2;
	private double scalar;
	private GamePanel panel;
	private final double SPEED = 2;
	private double nextX;
	private double nextY;

	public Joo(double diameter, Color color, double scalar, GamePanel panel) {

		this.color = color;
		this.diameter = diameter;
		this.scalar = scalar;
		this.panel = panel;

		nextX = scalar + panel.getWidth() / 2 - diameter / 2;
		nextY = scalar + panel.getHeight() / 2 - diameter / 2;

		double[] possibleDirections = 
		{
			Math.PI / 6,
			Math.PI / 3,
			2 * Math.PI / 3,
			5 * Math.PI / 6,
			7 * Math.PI / 6,
			4 * Math.PI / 3,
			5 * Math.PI / 3,
			11 * Math.PI / 6
		};
		nextDirection = possibleDirections
		[
			(int) (Math.random() * (possibleDirections.length - 1))
		];
	}

	public double getNextX() { return nextX; }
	public double getNextY() { return nextY; }
	public double getNextDirection() { return nextDirection; }

	public void stop() {

		speed = 0;
	}

	/**
	 * Checks collisions with walls, and adjusts direction accordingly.
	 * 
	 * @param wall can be 0 through 3 (see below)
	 * 
	 *    0
	 *    |
	 * 3--#--1
	 *    |
	 *    2
	 */
	public void checkBounce(int wall) {

		switch (wall) {
			case 0:
				if (y <= 0)
					direction = -direction;
				break;
			case 1:
				if (x + diameter >= panel.getWidth())
					direction = Math.PI - direction;
				break;
			case 2:
				if (y + diameter >= panel.getHeight())
					direction = -direction;
				break;
			case 3:
				if (x <= 0)
					direction = Math.PI - direction;
				break;
			default: break;
		}
	}

	/** Returns true if Joo collides with object */
	public boolean checkCollision(Circle object) {

		double a = 
			(object.getX() + object.getDiameter() / 2) - 
			(x + diameter / 2),
			   b = 
			(object.getY() + object.getDiameter() / 2) - 
			(y + diameter / 2),
		       c = Math.sqrt(a * a + b * b);

		if (c < diameter / 2)
			return true;

		return false;
	}

	public void init() {

		double rand = Math.random() * Math.PI * 2;

		x = nextX;
		y = nextY;
		direction = nextDirection;

		nextX = Math.cos(rand) * scalar + panel.getWidth() / 2 - diameter / 2;
		nextY = Math.sin(rand) * scalar + panel.getHeight() / 2 - diameter / 2;

		double[] possibleDirections = 
		{
			Math.PI / 6,
			Math.PI / 3,
			2 * Math.PI / 3,
			5 * Math.PI / 6,
			7 * Math.PI / 6,
			4 * Math.PI / 3,
			5 * Math.PI / 3,
			11 * Math.PI / 6
		};

		nextDirection = possibleDirections
		[
			(int) (Math.random() * (possibleDirections.length - 1))
		];

		speed = SPEED;
	}

	public void move() {

		x += Math.cos(direction) * speed;
		y += Math.sin(direction) * speed;
	}
}