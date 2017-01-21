import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public class Drawn {
	// Centroid of vertices
	private double x, y;

	private double[][] vertices;

	private double rotation;

	private Color color;

	// Vertices of certain shapes
	public static final int[][] TRIANGLE = {{-1, 1, -1}, {-1, 0, 1}};
	public static final int[][] SQUARE = {{-1, 1, 1, -1}, {-1, -1, 1, 1}};

	public Drawn(int x,
		int y,
		int[][] vertices,
		int size, 
		double rotation,
		Color color) {

		this.vertices = new double[vertices[0].length][vertices[1].length];
		for (int i = 0; i < this.vertices[0].length; i++) {
			this.vertices[0][i] = vertices[0][i] * size + x;
			this.vertices[1][i] = vertices[1][i] * size + y;

			this.x += this.vertices[0][i];
			this.y += this.vertices[1][i];
		}
		this.x /= this.vertices[0].length;
		this.y /= this.vertices[1].length;

		this.rotation = rotation;
		this.color = color;
	}

	protected double getX() {
		return x;
	}
	protected double getY() {
		return y;
	}

	protected void translate(double dx, double dy) {
		x = 0;
		y = 0;

		for (int i = 0; i < vertices[0].length; i++) {
			vertices[0][i] += dx;
			vertices[1][i] += dy;

			x += vertices[0][i];
			y += vertices[1][i];
		}

		x /= vertices[0].length;
		y /= vertices[1].length;
	}

	protected Color getColor() {
		return color;
	}

	protected double[] getXVerts() {
		return Arrays.copyOf(vertices[0], vertices[0].length);
	}
	protected double[] getYVerts() {
		return Arrays.copyOf(vertices[1], vertices[1].length);
	}

	protected double getRotation() {
		return rotation;
	}

	protected void rotate(double r) {
		rotation += r;
	}

}