public class Paddle {

	private double radius, rotation, scaler;
	private double ROT_AMMOUNT = Math.PI / 32;

	public Paddle(double radius, double rotation, double scaler) {

		this.radius = radius;
		this.rotation = rotation;
		this.scaler = scaler;
	}

	/**
	 * @param direction: -1 is clockwise, +1 is counterclockwise
	 */
	public void rotate(int direction) {

		setRotation(getRotation() + ROT_AMMOUNT);
	}

	public double getRotation() {

		return rotation;
	}

	private void setRotation(double ammount) {

		
	}
}