import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;

public class Camera {
	private ArrayList<Drawn> objectsList;
	private ArrayList<Drawn> focusesList;

	public Camera() {
		objectsList = new ArrayList<Drawn>();
		focusesList = new ArrayList<Drawn>();
	}

	public void add(Drawn object, boolean isFocus) {
		objectsList.add(object);
		if (isFocus)
			focusesList.add(object);
	} 

	public Drawn[] getObjects() {
		return objectsList.toArray(new Drawn[0]);
	}
	public Drawn[] getFocuses() {
		return focusesList.toArray(new Drawn[0]);
	}

	public Point calculateCentroid(double[] xVertices, double[] yVertices) {
		Point centroid = new Point();

		for (int i = 0; i < xVertices.length; i++) {
			centroid.setLocation(
				centroid.x + xVertices[i],
				centroid.y + yVertices[i]);
		}

		centroid.x /= xVertices.length;
		centroid.y /= yVertices.length;

		return centroid;
	}

	public Point calculateCentroid(Drawn[] objects) {
		Point centroid = new Point();

		for (Drawn d : objects) {
			centroid.setLocation(
				centroid.x + d.getX(),
				centroid.y + d.getY());
		}

		centroid.x /= objects.length;
		centroid.y /= objects.length;

		return centroid;
	}

	private double calculateZoom(
		double width, 
		double height,
		Point centroid, 
		Drawn[] focuses) {

		double furthest = 0;
		for (Drawn f : focuses) {
			double a = f.getX() - centroid.x;
			double b = f.getY() - centroid.y;
			double c = Math.sqrt(a * a + b * b);
			if (c > furthest) {
				furthest = c;
			}
		}

		double radius = width < height ? width / 2.5 : height / 2.5;

		double zoom = radius / furthest;
		if (zoom > 1) {
			zoom = -1 / zoom + 2;
		}

		return zoom;
	}

	public double[] zoomVertices(double[] vertices, double zoom) {
		double[] zoomedVertices = new double[vertices.length];

		for (int i = 0; i < zoomedVertices.length; i++) {
			zoomedVertices[i] = vertices[i] * zoom;
		}

		return Arrays.copyOf(zoomedVertices, zoomedVertices.length);
	}

	public double[] translateVertices(double[] vertices, double amount) {
		double[] translatedVertices = vertices;

		for (int i = 0; i < translatedVertices.length; i++) {
			translatedVertices[i] += amount;
		}

		return Arrays.copyOf(translatedVertices, translatedVertices.length);
	}

	public void draw(
		Graphics g,
		double width,
		double height,
		double focusCircleSize) {

		Graphics2D g2d = (Graphics2D) g;

		Drawn[] objects = getObjects();
		Drawn[] focuses = getFocuses();

		Point centroid = calculateCentroid(focuses);

		double zoom = calculateZoom(width, height, centroid, focuses);

		for (Drawn d : objects) {
			g.setColor(d.getColor());

			// Get shape rotation
			double rotation = d.getRotation();

			double[] xVerts = translateVertices(
				zoomVertices(
					translateVertices(
						d.getXVerts(),
						-centroid.x),
					zoom),
				width / 2);
			double[] yVerts = translateVertices(
				zoomVertices(
					translateVertices(
						d.getYVerts(),
						-centroid.y),
					zoom),
				height / 2);

			Point shapeCentroid = calculateCentroid(xVerts, yVerts);

			// Draw identification circle around shape if it is a focus
			for (Drawn f : focuses) {
				if (d == f) {
					g2d.drawOval(
						(int) (shapeCentroid.x - focusCircleSize / 2), 
						(int) (shapeCentroid.y - focusCircleSize / 2), 
						(int) focusCircleSize, 
						(int) focusCircleSize);
					g2d.drawLine(
						shapeCentroid.x, 
						shapeCentroid.y,
						(int) (focusCircleSize / 2 * Math.cos(rotation)) + shapeCentroid.x,
						(int) (focusCircleSize / 2 * Math.sin(rotation)) + shapeCentroid.y);
					break;
				}
			}

			// Rotate shape around its centroid
			g2d.rotate(rotation, shapeCentroid.x, shapeCentroid.y); 

			int[] xVertsInt = new int[xVerts.length];
			int[] yVertsInt = new int[yVerts.length];
			for (int i = 0; i < xVerts.length; i++) {
				xVertsInt[i] = (int) xVerts[i];
				yVertsInt[i] = (int) yVerts[i];
			}
			// Drawn shape
			g.drawPolygon(xVertsInt, yVertsInt, xVertsInt.length);

			// Reset rotation
			g2d.rotate(-rotation, shapeCentroid.x, shapeCentroid.y);
		}
	}
}