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

	/**
	 * Applies a transformation to a series of points.
	 * 
	 * @param xpoints      are the x coordinates to be transformed
	 * @param ypoints      are the y coordinates to be transformed
	 * @param npoints      is the number of points
	 * @param xanchor      is the x coordinate that xpoints and ypoints are
	 *                     rotated around
	 * @param yanchor      is the y coordinate that xpoints and ypoints are
	 *                     rotated around
	 * @param angle        is the angle of rotation
	 * @param xtranslation is the translation in the x dimension
	 * @param ytranslation is the translation in the y dimension
	 * @param scale        is the scalar value
	 */
	private void transform(
		double[] xpoints,
		double[] ypoints,
		int npoints,
		double xanchor,
		double yanchor,
		double angle,
		double xtranslation,
		double ytranslation, 
		double scale) {

		double sin = Math.sin(angle), cos = Math.cos(angle);

		for (int i = 0; i < npoints; i++) {
			xpoints[i] -= xanchor;
			ypoints[i] -= yanchor;
		}

		double[] xpointsNew = new double[npoints];
		double[] ypointsNew = new double[npoints];

		for (int i = 0; i < npoints; i++) {
			xpointsNew[i] = xpoints[i] * cos - ypoints[i] * sin;
			ypointsNew[i] = xpoints[i] * sin + ypoints[i] * cos;
		}

		for (int i = 0; i < npoints; i++) {
			xpoints[i] = (xpointsNew[i] + xanchor) * scale + xtranslation;
			ypoints[i] = (ypointsNew[i] + yanchor) * scale + ytranslation;
		}
	}

	public boolean contains(double px, double py) {
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		transform(tx, ty, vertices, cx, cy, rotation, x, y, size);

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
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		transform(tx, ty, vertices, cx, cy, rotation, x, y, size);

		double tcx = 0;

		for (int i = 0; i < vertices; i++)
			tcx += tx[i];

		tcx /= vertices;

		return tcx;
	}
	protected double getY() {
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		transform(tx, ty, vertices, cx, cy, rotation, x, y, size);

		double tcy = 0;

		for (int i = 0; i < vertices; i++)
			tcy += ty[i];

		tcy /= vertices;

		return tcy;
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