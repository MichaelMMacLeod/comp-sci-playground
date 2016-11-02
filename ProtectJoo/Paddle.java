public class Paddle {

	private double diameter, rotation, scalar;
	private double ROT_AMMOUNT = Math.PI / 100	;

	public Paddle(double diameter, double rotation, double scalar) {

		this.diameter = diameter;
		this.rotation = rotation;
		this.scalar = scalar;
	}

	public double getDiameter() { return diameter; }
	
	public double getRotation() { return rotation; }

	/** Returns the center coordinates 
	 *  pos[0] is x
	 *  pos[1] is y
	 */
	public double[] getPos() {

		double[] center = {250 - diameter / 2, 250 - diameter / 2};
		double[] pos = {center[0] + scalar * Math.cos(rotation), center[1] + scalar * Math.sin(rotation)};

		return pos;
	}

	/** Rotates the object by ROT_AMMOUNT
	 *  @param direction: -1 is clockwise, +1 is counterclockwise
	 */
	public void rotate(int direction) {

		setRotation(direction * ROT_AMMOUNT);
	}

	/** Keeps rotation within reasonable bounds */
	private void setRotation(double ammount) {

		rotation += ammount;

		while (Math.abs(rotation) >= 2 * Math.PI) {

			rotation += rotation > 0 ? -2 * Math.PI : 2 * Math.PI;
		}
	}
}