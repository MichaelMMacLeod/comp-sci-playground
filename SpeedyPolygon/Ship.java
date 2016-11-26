import java.awt.Polygon;

public class Ship  {

	private int x, y, width, height;
	private double[] vector;
	private double rotation, rotationSpeed, weight;

	public Ship(int x, int y, int width, int height, double weight) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.weight = weight;

		vector = new double[2];
		rotation = 0;
	}

	public double getRotation() { return rotation; }

	public void update() {
		x += (1 - weight) * vector[0] / 2;
		y += (1 - weight) * vector[1] / 2;
		rotation += rotationSpeed;
		rotationSpeed *= 0.95;
	}

	public void rotate(boolean clockwise) {
		rotationSpeed = clockwise ? Math.PI / 128 : -Math.PI / 128;
	}

	public void thrust() {

		double[] vectorNew =
		{
			Math.cos(rotation),
			Math.sin(rotation)
		};

		vector[0] += vectorNew[0];
		vector[1] += vectorNew[1];
	}

	public int[] getTranslation() {

		double cos = Math.cos(-rotation);
		double sin = Math.sin(-rotation);

		double[] mid =
		{
			x + width / 2,
			y + height / 2
		};
		double[] midNew = 
		{
			mid[1] * sin + mid[0] * cos,
			mid[1] * cos - mid[0] * sin
		};
		int[] diff =
		{
			(int) (mid[0] - midNew[0]),
			(int) (mid[1] - midNew[1])
		};

		return diff;
	}

	public Polygon polygon() {

		int[] xVerts = 
		{
			x, 
			x + width, 
			x
		};
		int[] yVerts = 
		{
			y, 
			y + height / 2, 
			y + height
		};
		int n = 3;

		Polygon p = new Polygon(xVerts, yVerts, n);

		return p;
	}
}