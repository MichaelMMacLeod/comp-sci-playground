public class Ship extends Moveable {

	private double acceleration;
	private double rotationSpeed;

	public Ship(
		Drawn shape, 
		double acceleration, 
		double rotationSpeed, 
		double deceleration) {

		super(shape, deceleration);

		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
	}

	public void thrust() {
		vector[0] += acceleration * Math.cos(shape.getRotation());
		vector[1] += acceleration * Math.sin(shape.getRotation());
	}

	public void rotate(boolean clockwise) {
		shape.rotate((clockwise ? 1 : -1) * Math.PI / rotationSpeed);
	}

	public boolean hitBy(Projectile p) {
		Drawn pShape = p.shape();
		return shape.contains(pShape.getX(), pShape.getY());
	}
}