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

	public void draw(
		Graphics g,
		double width,
		double height,
		double focusCircleSize) {

		// Create copies of the ArrayLists so we don't have weird concurrency 
		// errors.

		Drawn[] objects = objectsList.toArray(new Drawn[0]);
		Drawn[] focuses = focusesList.toArray(new Drawn[0]);

		// Calculate the center of zoom, store it in a Point2D.Double point.

		Point2D.Double centerOfZoom = new Point2D.Double();

		int vertices = focuses.length;

		for (int i = 0; i < vertices; i++) {
			centerOfZoom.x += focuses[i].getPoint().x;
			centerOfZoom.y += focuses[i].getPoint().y;
		}

		centerOfZoom.x /= vertices;
		centerOfZoom.y /= vertices;

		// Calculate zoom scalar value.

		double furthest = 0;

		// Find furthest focus from the center of zoom.
		for (Drawn f : focuses) {

			// Use the Pythagorean theorem.
			double a = f.getPoint().x - centerOfZoom.x;
			double b = f.getPoint().y - centerOfZoom.y;
			double c = Math.sqrt(a * a + b * b);

			if (c > furthest)
				furthest = c;
		}

		// Use 2.5 instead of 2 so that objects aren't directly on the edge of 
		// the screen.
		double radius = width < height ? width / 2.5 : height / 2.5;

		double zoom = radius / furthest;

		// Make the zoom factor smoothly approach maximum zoom.
		if (zoom > 1) {
			zoom = -1 / zoom + 2;
		}

		// Drawn each object.

		for (Drawn d : objects) {

			// Apply color.

			g.setColor(d.getColor());

			// Transform vertices based on current zoom center and scalar.

			double[] xVerts = d.getVertices()[0];
			double[] yVerts = d.getVertices()[1];

			if (xVerts.length < yVerts.length)
				vertices = xVerts.length;
			else
				vertices = yVerts.length;

			for (int i = 0; i < vertices; i++) {
				xVerts[i] = (xVerts[i] - centerOfZoom.x) * zoom + width / 2;
				yVerts[i] = (yVerts[i] - centerOfZoom.y) * zoom + height / 2;
			}

			// Calculate center of shape.

			double rotation = d.getRotation();

			Point2D.Double shapeCenter = new Point2D.Double();

			for (int i = 0; i < vertices; i++) {
				shapeCenter.x += xVerts[i];
				shapeCenter.y += yVerts[i];
			}

			shapeCenter.x /= vertices;
			shapeCenter.y /= vertices;

			// Drawn identification circles around focuses.
			// TODO: is there a better way we can determine if it is a focus?

			for (Drawn f : focuses) {
				if (d == f) {

					// Drawn circle
					g.drawOval(
						(int) (shapeCenter.x - focusCircleSize / 2), 
						(int) (shapeCenter.y - focusCircleSize / 2), 
						(int) focusCircleSize, 
						(int) focusCircleSize);

					// Drawn rotation line
					g.drawLine(
						(int) shapeCenter.x, 
						(int) shapeCenter.y,
						(int) (focusCircleSize / 2 * Math.cos(rotation) + shapeCenter.x),
						(int) (focusCircleSize / 2 * Math.sin(rotation) + shapeCenter.y));

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