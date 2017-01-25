import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Graphics;

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

	private Drawn[] getObjects() {
		return objectsList.toArray(new Drawn[0]);
	}

	private Drawn[] getFocuses() {
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

	private double calculateZoom(
		double width, 
		double height,
		Point2D.Double centroid, 
		Drawn[] focuses) {

		double furthest = 0;
		for (Drawn f : focuses) {
			double a = f.getPoint().x - centroid.x;
			double b = f.getPoint().y - centroid.y;
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

		return zoomedVertices;
	}

	public double[] translate(double[] vertices, double amount) {
		double[] translatedVertices = vertices;

		for (int i = 0; i < translatedVertices.length; i++) {
			translatedVertices[i] += amount;
		}

		return translatedVertices;
	}

	public void draw(
		Graphics g,
		double width,
		double height,
		double focusCircleSize) {

		// Create copies of the ArrayLists so we dont have weird concurrency errors

		Drawn[] objects = getObjects();
		Drawn[] focuses = getFocuses();

		// Calculate the center of zoom

		double[] focusXVertices = new double[focuses.length];
		double[] focusYVertices = new double[focuses.length];

		for (int i = 0; i < focuses.length; i++) {
			focusXVertices[i] = focuses[i].getPoint().x;
			focusYVertices[i] = focuses[i].getPoint().y;
		}

		Point2D.Double centerOfZoom = new Point2D.Double();

		int vertices = focusXVertices.length;

		for (int i = 0; i < vertices; i++) {
			centerOfZoom.x += focusXVertices[i];
			centerOfZoom.y += focusYVertices[i];
		}

		centerOfZoom.x /= vertices;
		centerOfZoom.y /= vertices;

		// Calculate zoom scalar value

		double zoom = calculateZoom(width, height, centerOfZoom, focuses);

		// Drawn each object

		for (Drawn d : objects) {

			// Apply color

			g.setColor(d.getColor());

			// Transform vertices based on current zoom
			// TODO: clean this up

			double[] xVerts = translate(
				zoomVertices(
					translate(
						d.getVertices()[0],
						-centerOfZoom.x),
					zoom),
				width / 2);
			double[] yVerts = translate(
				zoomVertices(
					translate(
						d.getVertices()[1],
						-centerOfZoom.y),
					zoom),
				height / 2);

			// Calculate centroid of shape
			// TODO: can't we just use getX() and getY() ?

			Point shapeCentroid = calculateCentroid(xVerts, yVerts);

			// Drawn identification circles around focuses
			// TODO: is there a better way we can determine if it is a focus?

			double rotation = d.getRotation();

			for (Drawn f : focuses) {
				if (d == f) {

					// Drawn circle
					g.drawOval(
						(int) (shapeCentroid.x - focusCircleSize / 2), 
						(int) (shapeCentroid.y - focusCircleSize / 2), 
						(int) focusCircleSize, 
						(int) focusCircleSize);

					// Drawn rotation line
					g.drawLine(
						shapeCentroid.x, 
						shapeCentroid.y,
						(int) (focusCircleSize / 2 * Math.cos(rotation)) + shapeCentroid.x,
						(int) (focusCircleSize / 2 * Math.sin(rotation)) + shapeCentroid.y);

					break;
				}
			}

			// Convert double arrays into integer arrays

			int[] xVertsInt = new int[xVerts.length];
			int[] yVertsInt = new int[yVerts.length];
			for (int i = 0; i < xVerts.length; i++) {
				xVertsInt[i] = (int) xVerts[i];
				yVertsInt[i] = (int) yVerts[i];
			}

			// Drawn the polygon

			g.drawPolygon(xVertsInt, yVertsInt, xVertsInt.length);
		}
	}
}