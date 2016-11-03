import java.awt.Color;

public class Joo extends Circle {

	private double direction;
	private double speed = 2;

	public Joo(double diameter, Color color) {

		this.color = color;
		this.diameter = diameter;

		initPos();
	}

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
	public void checkBounce(GamePanel panel, int wall) {

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

		if (c < diameter)
			return true;

		return false;
	}

	public void initPos() {

		double rand = Math.random() * Math.PI * 2;

		x = Math.cos(rand) * 200 + 250 - 50 / 2;
		y = Math.sin(rand) * 200 + 250 - 50 / 2;

		direction = Math.random() * Math.PI * 2;
	}

	public void move() {

		x += Math.cos(direction) * speed;
		y += Math.sin(direction) * speed;
	}
}