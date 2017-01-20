import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public abstract class Drawn {
	// Centroid of vertices
	private double x, y;

	// Vertices of shape
	private double[] xVerts, yVerts;

	private double rotation;

	private Color color;

	// Vertices of certain shapes
	public static final double[][] TRIANGLE = {{-1, 1, -1}, {-1, 0, 1}};
	public static final double[][] SQUARE = {{-1, 1, 1, -1}, {-1, -1, 1, 1}};

	public Drawn(int x,
		int y,
		int[] xVerts, 
		int[] yVerts,
		int size, 
		double rotation,
		Color color) {

		this.xVerts = new double[xVerts.length];
		this.yVerts = new double[yVerts.length];
		for (int i = 0; i < this.xVerts.length; i++) {
			this.xVerts[i] = xVerts[i] * size + x;
			this.yVerts[i] = yVerts[i] * size + y;

			this.x += this.xVerts[i];
			this.y += this.yVerts[i];
		}
		this.x /= xVerts.length;
		this.y /= yVerts.length;

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

		for (int i = 0; i < xVerts.length; i++) {
			xVerts[i] += dx;
			yVerts[i] += dy;

			x += xVerts[i];
			y += yVerts[i];
		}

		x /= xVerts.length;
		y /= yVerts.length;
	}

	protected Color getColor() {
		return color;
	}

	protected double[] getXVerts() {
		return Arrays.copyOf(xVerts, xVerts.length);
	}
	protected double[] getYVerts() {
		return Arrays.copyOf(yVerts, yVerts.length);
	}

	protected double getRotation() {
		return rotation;
	}

	protected void rotate(double r) {
		rotation += r;
	}

}