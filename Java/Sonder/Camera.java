import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;

public class Camera {

    private int width, height;
    private double x, y;

    private ArrayList<Drawn> focuses;
    private ArrayList<Drawn> objects;

    public Camera(int width, int height, Drawn[] focuses, Drawn[] objects) {
        this.width = width;
        this.height = height;

        this.focuses = new ArrayList<Drawn>(Arrays.asList(focuses));
        this.objects = new ArrayList<Drawn>(Arrays.asList(objects));
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Drawn d : objects) {
            for (Drawn f : focuses) {
                x += f.getX();
                y += f.getY();
            }
            x /= focuses.size();
            y /= focuses.size();

            double zoom = 500 / Math.sqrt(x * x + y * y);
            if (zoom > 1.5) zoom = 1.5;

            double[] xv = d.getXVerts();
            double[] yv = d.getYVerts();
            double xp = d.getX();
            double yp = d.getY();
            double r = d.getRotation();

            double cx = 0, cy = 0;
            for (int i = 0; i < xv.length; i++) {
                xv[i] = (xv[i] + xp - x) * zoom + width / 2;
                yv[i] = (yv[i] + yp - y) * zoom + height / 2;

                cx += xv[i];
                cy += yv[i];
            }
            cx /= xv.length;
            cy /= yv.length;

            g2d.rotate(r, cx, cy);

            int[] xvInt = new int[xv.length];
            int[] yvInt = new int[yv.length];
            for (int i = 0; i < xv.length; i++) {
                xvInt[i] = (int) xv[i];
                yvInt[i] = (int) yv[i];
            } 

            g.setColor(d.getColor());
            g.drawPolygon(xvInt, yvInt, xv.length);

            g2d.rotate(-r, cx, cy);
        }
    }
}