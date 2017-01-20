import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;

public class Camera {

    public void draw(Graphics g, 
        int width, 
        int height,
        ArrayList<Drawn> focusesList,
        ArrayList<Drawn> objectsList,
        int focusCircleSize) {

        Drawn[] focuses = focusesList.toArray(new Drawn[0]);
        Drawn[] objects = objectsList.toArray(new Drawn[0]);

        Graphics2D g2d = (Graphics2D) g;

        // Calculate radius of zoom circle
        double radius = width < height ? width / 2.5 : height / 2.5;

        // Calculate centroid of focuses
        int x = 0;
        int y = 0;
        for (Drawn f : focuses) {
            x += f.getX();
            y += f.getY();
        }
        x /= focuses.length;
        y /= focuses.length;

        // Calculate furthest focus from centroid
        double furthest = 0;
        for (Drawn f : focuses) {
            double a = f.getX() - x;
            double b = f.getY() - y;
            double c = Math.sqrt(a * a + b * b);
            if (c > furthest) {
                furthest = c;
            }
        }

        // Calculate zoom factor
        double zoom = radius / furthest;
        if (zoom > 1) {
            zoom = 1;
        }

        for (Drawn d : objects) {
            g.setColor(d.getColor());

            // Get shape vertices
            int[] xv = d.getXVerts();
            int[] yv = d.getYVerts();

            // Get shape coordinates
            int xp = d.getX();
            int yp = d.getY();

            // Get shape rotation
            double r = d.getRotation();

            // Calculate zoomed in and translated points
            int newX = 0, newY = 0;
            for (int i = 0; i < xv.length; i++) {
                xv[i] = (int) ((xv[i] - x) * zoom) + width / 2;
                yv[i] = (int) ((yv[i] - y) * zoom) + height / 2;

                newX += xv[i];
                newY += yv[i];
            }
            newX /= xv.length;
            newY /= yv.length;

            // Draw identification circle around shape if it is a focus
            for (Drawn f : focuses) {
                if (d == f) {
                    g2d.drawOval(newX - focusCircleSize / 2, 
                        newY - focusCircleSize / 2, 
                        focusCircleSize, 
                        focusCircleSize);
                    g2d.drawLine(newX, 
                        newY,
                        (int) (focusCircleSize / 2 * Math.cos(r)) + newX,
                        (int) (focusCircleSize / 2 * Math.sin(r)) + newY);
                    break;
                }
            }

            // Rotate shape around its centroid
            g2d.rotate(r, newX, newY); 

            // Drawn shape
            g.drawPolygon(xv, yv, xv.length);

            // Reset rotation
            g2d.rotate(-r, newX, newY);
        }
    }
}