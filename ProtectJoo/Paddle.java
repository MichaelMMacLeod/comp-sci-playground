public class Paddle {

	private double radius, rotation, scalar;
	private double ROT_AMMOUNT = Math.PI / 100	;

	public Paddle(double radius, double rotation, double scalar) {

		this.radius = radius;
		this.rotation = rotation;
		this.scalar = scalar;
	}

	public double getRadius() {

		return radius;
	}

	/** Returns the center coordinates 
	 *  pos[0] is x
	 *  pos[1] is y
	 */
	public double[] getPos() {

		double[] center = {250 - radius / 2, 250 - radius / 2};
		double[] pos = {center[0] + scalar * Math.cos(rotation), center[1] + scalar * Math.sin(rotation)};

		// System.out.println(pos[0] + " " + pos[1] + " " + rotation);

		return pos;
	}

	/** Rotates the object by ROT_AMMOUNT
	 *  @param direction: -1 is clockwise, +1 is counterclockwise
	 */
	public void rotate(int direction) {

		setRotation(direction * ROT_AMMOUNT);
	}

	public double getRotation() {

		return rotation;
	}

	/** Keeps rotation within (-2 * PI, 2 * PI) */
	private void setRotation(double ammount) {

		rotation += ammount;

		// while (Math.abs(rotation) >= 2 * Math.PI) {

		// 	rotation += rotation > 0 ? -2 * Math.PI : 2 * Math.PI;
		// }
	}
}