import java.util.Arrays;

public abstract class Moveable {

	protected Drawn shape;
	protected double deceleration;

	protected double[] vector;

	public Moveable(Drawn shape, double deceleration) {
		this.shape = shape;
		this.deceleration = deceleration;
		vector = new double[2];
	}

	public Drawn shape() {
		return shape;
	}

	public void update() {
		shape.translate(vector[0], vector[1]);

		vector[0] *= deceleration;
		vector[1] *= deceleration;
	}

	public double[] vector() {
		return Arrays.copyOf(vector, vector.length);
	}
}