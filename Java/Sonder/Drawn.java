import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public class Drawn {
	// Centroid of vertices
	private double cx, cy;

	// Number of vertices
	private int vertices;
	private double[] xVertices, yVertices;

	private double rotation;

	private Color color;

	// Vertices of certain shapes
	public static final double[][] TRIANGLE = {{-1, 1, -1}, {-1, 0, 1}};
	public static final double[][] SQUARE = {{-1, 1, 1, -1}, {-1, -1, 1, 1}};

	public Drawn(
		int x,
		int y,
		double[][] shapeVertices,
		int size, 
		double rotation,
		Color color) {

		this.vertices = shapeVertices[0].length;

		xVertices = Arrays.copyOf(shapeVertices[0], this.vertices);
		yVertices = Arrays.copyOf(shapeVertices[1], this.vertices);

		for (int i = 0; i < this.vertices; i++) {
			xVertices[i] = xVertices[i] * size + x;
			yVertices[i] = yVertices[i] * size + y;
			cx += xVertices[i];
			cy += yVertices[i];
		}

		cx /= this.vertices;
		cy /= this.vertices;

		this.rotation = rotation;
		this.color = color;
	}

	protected double getX() {
		return cx;
	}
	protected double getY() {
		return cy;
	}

	protected void translate(double dx, double dy) {
		cx += dx;
		cy += dy;

		for (int i = 0; i < vertices; i++) {
			xVertices[i] += dx;
			yVertices[i] += dy;
		}
	}

	protected Color getColor() {
		return color;
	}

	protected double[] getXVerts() {
		return Arrays.copyOf(xVertices, vertices);
	}
	protected double[] getYVerts() {
		return Arrays.copyOf(yVertices, vertices);
	}

	protected double getRotation() {
		return rotation;
	}

	protected void rotate(double r) {
		rotation += r;
	}
}