import java.awt.geom.AffineTransform;
import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public class Drawn {
	// Coordinates
	private double x, y;

	// Centroid of vertices
	private final double cx, cy;

	private double size;

	// Number of vertices
	private final int vertices;
	private final double[] xVertices, yVertices;

	private double rotation;

	private Color color;

	// Vertices of certain shapes
	public static final double[][] TRIANGLE = 
	{
		{-1, 1, -1}, 
		{-1, 0, 1}
	};
	public static final double[][] SQUARE = 
	{
		{-1, 1, 1, -1}, 
		{-1, -1, 1, 1}
	};

	public Drawn(
		double x,
		double y,
		double[][] shape,
		double size, 
		double rotation,
		Color color) {

		this.x = x;
		this.y = y;

		if (shape[0].length < shape[1].length)
			vertices = shape[0].length;
		else
			vertices = shape[1].length;

		xVertices = Arrays.copyOf(shape[0], vertices);
		yVertices = Arrays.copyOf(shape[1], vertices);

		double centroidx = 0, centroidy = 0;
		for (int i = 0; i < vertices; i++) {
			centroidx += xVertices[i];
			centroidy += yVertices[i];
		}
		centroidx /= vertices;
		centroidy /= vertices;
		cx = centroidx;
		cy = centroidy;

		this.size = size;
		this.rotation = rotation;
		this.color = color;

	}

	public boolean contains(double px, double py) {
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);

		for (int i = 0; i < vertices; i++) {
			tx[i] -= cx;
			ty[i] -= cy;
		}

		double[] xVerticesRotated = new double[vertices];
		double[] yVerticesRotated = new double[vertices];

		for (int i = 0; i < vertices; i++) {
			xVerticesRotated[i] = tx[i] * cos - ty[i] * sin;
			yVerticesRotated[i] = tx[i] * sin + ty[i] * cos;
		}

		for (int i = 0; i < vertices; i++) {
			tx[i] = (xVerticesRotated[i] + cx) * size + x;
			ty[i] = (yVerticesRotated[i] + cy) * size + y;
		}

		int[] itx = new int[vertices];
		int[] ity = new int[vertices];

		for (int i = 0; i < vertices; i++) {
			itx[i] = (int) tx[i];
			ity[i] = (int) ty[i];
		}

		Polygon p = new Polygon(itx, ity, vertices);
		
		return p.contains(px, py);
	}

	protected double getX() {
		return cx + x;
	}
	protected double getY() {
		return cy + y;
	}

	protected void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	protected Color getColor() {
		return color;
	}

	protected double[] getXVerts() {
		double[] tx = Arrays.copyOf(xVertices, vertices);

		for (int i = 0; i < vertices; i++) 
			tx[i] = tx[i] * size + x;

		return tx;
	}
	protected double[] getYVerts() {
		double[] ty = Arrays.copyOf(yVertices, vertices);

		for (int i = 0; i < vertices; i++) 
			ty[i] = ty[i] * size + y;

		return ty;
	}

	protected double getRotation() {
		return rotation;
	}

	protected void rotate(double r) {
		rotation += r;
	}
}