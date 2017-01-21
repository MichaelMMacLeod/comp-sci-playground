import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public class Drawn {
	private double[] centroid;

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

		centroid = new double[2];

		this.vertices = new double[vertices[0].length][vertices[1].length];
		for (int i = 0; i < this.vertices[0].length; i++) {
			this.vertices[0][i] = vertices[0][i] * size + x;
			this.vertices[1][i] = vertices[1][i] * size + y;

			centroid[0] += this.vertices[0][i];
			centroid[1] += this.vertices[1][i];
		}
		centroid[0] /= this.vertices[0].length;
		centroid[1] /= this.vertices[1].length;

		this.rotation = rotation;
		this.color = color;
	}

	protected double getX() {
		return centroid[0];
	}
	protected double getY() {
		return centroid[1];
	}

	protected void translate(double dx, double dy) {
		centroid[0] += dx;
		centroid[1] += dy;

		for (int i = 0; i < vertices[0].length; i++) {
			vertices[0][i] += dx;
			vertices[1][i] += dy;
		}
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