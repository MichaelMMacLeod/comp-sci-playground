public class Projectile {

	private Drawn shape;

	private double[] vectors;
	private double acceleration;
	private double rotationSpeed;

	public Projectile(Drawn shape, double velocity) {
		this.shape = shape;
		vectors = new double[] {Math.cos(velocity), Math.sin(velocity)};
	}

	public Drawn getShape() {
		return shape;
	}

	public void updatePos() {
		shape.moveX(vectors[0]);
		shape.moveY(vectors[1]);
	}
}