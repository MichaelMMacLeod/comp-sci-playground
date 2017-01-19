import java.util.Arrays;

public abstract class Moveable {

	protected Drawn shape;

	protected double[] vector;

	public Moveable(Drawn shape) {
		this.shape = shape;
		vector = new double[2];
	}

	public Drawn shape() {
		return shape;
	}

	public void update() {
		shape.moveX(vector[0]);
		shape.moveY(vector[1]);
	}

	public double[] vector() {
		return Arrays.copyOf(vector, vector.length);
	}
}