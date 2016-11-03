import java.awt.Color;

public class Joo extends Circle {

	private double direction = Math.random() * Math.PI * 2;
	private final double SPEED = 2;

	public Joo(double diameter, Color color) {

		this.color = color;
		this.diameter = diameter;

		initPos();
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
				if (getY() <= 0)
					direction = -direction;
				break;
			case 1:
				if (getX() + diameter >= panel.getWidth())
					direction = Math.PI - direction;
				break;
			case 2:
				if (getY() + diameter >= panel.getHeight())
					direction = -direction;
				break;
			case 3:
				if (getX() <= 0)
					direction = Math.PI - direction;
				break;
			default: break;
		}
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
}