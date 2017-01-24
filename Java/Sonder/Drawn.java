import java.awt.geom.AffineTransform;
import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public class Drawn {
	// Coordinates of the shape
	private double x, y;

	private double size;

	// Number of vertices
	private int vertices;

	// Location of vertices before transformation with centroid at (0, 0)
	private double[] xVertices, yVertices;

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

	/**
	 * Creates a shape defined by a series of vertices
	 * 
	 * @param x        is the x coordinate of the shape.
	 * @param y        is the y coordinate of the shape.
	 * @param shape    is the set of vertices defining the shape. X vertices 
	 *                 are stored in shape[0]. Y vertices are stored in 
	 *                 shape[1]. There must be an equal number of x and y
	 *                 vertices.
	 * @param size     is the size of the shape.
	 * @param rotation is the angle of rotation in radians.
	 * @param color    is the color of the shape.
	 */
	public Drawn(
		double[][] shape,
		double x,
		double y,
		double size, 
		double rotation,
		Color color) {

		setShape(shape);

		this.x = x;
		this.y = y;
		this.size = size;
		this.rotation = rotation;
		this.color = color;
	}

	/**
	 * Stores a shape in xVertices and yVertices
	 * 
	 * @param shape is the set of vertices defining the shape. X vertices 
	 *              are stored in shape[0]. Y vertices are stored in 
	 *              shape[1]. There must be an equal number of x and y
	 *              vertices.
	 */
	public void setShape(double[][] shape) {

		// Get vertices

		if (shape[0].length < shape[1].length)
			vertices = shape[0].length;
		else
			vertices = shape[1].length;

		double[] srcXVerticies = Arrays.copyOf(shape[0], vertices);
		double[] srcYVerticies = Arrays.copyOf(shape[1], vertices);

		// Move the center of the vertices to (0,0)

		double cx = 0, cy = 0;

		for (int i = 0; i < vertices; i++) {
			cx += srcXVerticies[i];
			cy += srcYVerticies[i];
		}
		cx /= vertices;
		cy /= vertices;

		for (int i = 0; i < vertices; i++) {
			srcXVerticies[i] -= cx;
			srcYVerticies[i] -= cy;
		}

		// Store vertices

		xVertices = srcXVerticies;
		yVertices = srcYVerticies;
	}

	/**
	 * Applies a transformation to a series of points.
	 * 
	 * @param xpoints      are the x coordinates to be transformed.
	 * @param ypoints      are the y coordinates to be transformed.
	 * @param npoints      is the number of points.
	 * @param angle        is the angle of rotation in radians. Assumes that 
	 *                     the centroid of the vertices is located at (0, 0).
	 * @param xtranslation is the translation in the x dimension.
	 * @param ytranslation is the translation in the y dimension.
	 * @param scale        is the scalar value.
	 */
	private void transform(
		double[] xpoints,
		double[] ypoints,
		int npoints,
		double angle,
		double xtranslation,
		double ytranslation, 
		double scale) {

		double sin = Math.sin(angle), cos = Math.cos(angle);

		double[] xpointsNew = new double[npoints];
		double[] ypointsNew = new double[npoints];

		for (int i = 0; i < npoints; i++) {
			xpointsNew[i] = xpoints[i] * cos - ypoints[i] * sin;
			ypointsNew[i] = xpoints[i] * sin + ypoints[i] * cos;
		}

		for (int i = 0; i < npoints; i++) {
			xpoints[i] = xpointsNew[i] * scale + xtranslation;
			ypoints[i] = ypointsNew[i] * scale + ytranslation;
		}
	}

	public boolean contains(double px, double py) {
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		transform(tx, ty, vertices, rotation, x, y, size);

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

		transform(tx, ty, vertices, rotation, x, y, size);

		double cx = 0;

		for (int i = 0; i < vertices; i++)
			cx += tx[i];

		cx /= vertices;

		return cx;
	}
	protected double getY() {
		double[] tx = Arrays.copyOf(xVertices, vertices);
		double[] ty = Arrays.copyOf(yVertices, vertices);

		transform(tx, ty, vertices, rotation, x, y, size);

		double cy = 0;

		for (int i = 0; i < vertices; i++)
			cy += ty[i];

		cy /= vertices;

		return cy;
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