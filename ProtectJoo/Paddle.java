import java.awt.Color;

public class Paddle extends Circle {

	private double rotation, scalar;
	private double ROT_AMMOUNT = Math.PI / 100	;

	public Paddle(double diameter, double rotation, double scalar, Color color) {

		this.color = color;
		this.diameter = diameter;
		this.rotation = rotation;
		this.scalar = scalar;

		rotate(0); // Initialize x and y
	}

	/** Rotates the paddle pi radians */
	public void jump() {

		rotate(Math.PI / ROT_AMMOUNT);
	}

	// @param direction is +1 for clockwise and -1 for counterclockwise
	public void rotate(double direction) {

		rotation += direction * ROT_AMMOUNT;

		while (Math.abs(rotation) >= 2 * Math.PI) {

			rotation += rotation > 0 ? -2 * Math.PI : 2 * Math.PI;
		}

		double[] center = {250 - diameter / 2, 250 - diameter / 2};

		x = center[0] + scalar * Math.cos(rotation);
		y = center[1] + scalar * Math.sin(rotation);
	}
}