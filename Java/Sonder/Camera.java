import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;

public class Camera {

    public void draw(Graphics g, 
        double width, 
        double height,
        ArrayList<Drawn> focusesList,
        ArrayList<Drawn> objectsList) {

        Drawn[] focuses = focusesList.toArray(new Drawn[0]);
        Drawn[] objects = objectsList.toArray(new Drawn[0]);

        Graphics2D g2d = (Graphics2D) g;

        // Calculate radius of zoom circle
        double radius = width < height ? width / 2.5 : height / 2.5;

        // Calculate centroid of focuses
        double x = 0;
        double y = 0;
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
            // Get shape vertices
            double[] xv = d.getXVerts();
            double[] yv = d.getYVerts();

            // Get shape coordinates
            double xp = d.getX();
            double yp = d.getY();

            // Get shape rotation
            double r = d.getRotation();

            // Calculate zoomed in and translated points
            double newX = 0, newY = 0;
            for (int i = 0; i < xv.length; i++) {
                xv[i] = (xv[i] + xp - x) * zoom + width / 2;
                yv[i] = (yv[i] + yp - y) * zoom + height / 2;

                newX += xv[i];
                newY += yv[i];
            }
            newX /= xv.length;
            newY /= yv.length;

            // Rotate shape around its centroid
            g2d.rotate(r, newX, newY);

            int[] xvInt = new int[xv.length];
            int[] yvInt = new int[yv.length];
            for (int i = 0; i < xv.length; i++) {
                xvInt[i] = (int) xv[i];
                yvInt[i] = (int) yv[i];
            } 

            // Drawn shape
            g.setColor(d.getColor());
            g.drawPolygon(xvInt, yvInt, xv.length);

            // Reset rotation
            g2d.rotate(-r, newX, newY);
        }
    }
}