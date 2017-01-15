public class Ship {

	private Drawn shape;

	private double[] vectors;
	private double acceleration;
	private double rotationSpeed;

	public Ship(Drawn shape, double acceleration, double rotationSpeed) {
		this.shape = shape;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;

		vectors = new double[2];
	}

	public Drawn getShape() {
		return shape;
	}

	public void updatePos() {
		shape.moveX(vectors[0]);
		shape.moveY(vectors[1]);
	}

	public void thrust() {
		vectors[0] += acceleration * Math.cos(shape.getRotation());
		vectors[1] += acceleration * Math.sin(shape.getRotation());
	}

	public void rotate(boolean clockwise) {
		shape.rotate((clockwise ? 1 : -1) * Math.PI / rotationSpeed);
	}
}