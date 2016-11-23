import java.awt.Polygon;

public class Ship  {

	private int x, y, width, height;
	private double[] vector;
	private double rotation;

	public Ship(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		vector = new double[2];
		rotation = 0;
	}

	public double getRotation() { return rotation; }

	public void update() {
		x += vector[0];
		y += vector[1];
	}

	public void rotate(boolean clockwise) {
		rotation += clockwise ? Math.PI / 128 : -Math.PI / 128;
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
			x 
			/*- width / 2*/, 
			x + width 
			/*- width / 2*/, 
			x 
			/*- width / 2*/
		};
		int[] yVerts = 
		{
			y 
			/*- height / 2*/, 
			y + height / 2 
			/*- height / 2*/, 
			y + height 
			/*- height / 2*/
		};
		int n = 3;

		Polygon p = new Polygon(xVerts, yVerts, n);

		return p;
	}
}