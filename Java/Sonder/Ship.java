import java.util.Arrays;

public class Ship extends Moveable {

	private double acceleration;
	private double rotationSpeed;

	private String[] keys = 
	{
		"rotate counter clockwise key",
		"rotate clockwise key",
		"thrust key",
		"fire key"
	};

	public String[] getKeys() {
		return Arrays.copyOf(keys, keys.length);
	}

	public Ship(
		Drawn shape, 
		double acceleration, 
		double rotationSpeed, 
		double deceleration,
		String[] keys) {

		super(shape, deceleration);

		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.keys = keys;
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
		return shape.contains(pShape.getPoint());
	}
}