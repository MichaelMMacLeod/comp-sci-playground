import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;

public class Camera {

    private int width, height;
    private double x, y;

    private Drawn focus;
    private ArrayList<Drawn> objects;

    public Camera(int width, int height, Drawn focus, Drawn[] objects) {
        this.width = width;
        this.height = height;
        this.focus = focus;

        this.objects = new ArrayList<Drawn>(Arrays.asList(objects));
        for (Drawn d : objects) {
            addObject(d);
        }
    }

    public void addObject(Drawn object) {
        objects.add(object);
    }

    public void setFocus(Drawn focus) {
        this.focus = focus;
    }

    public void draw(Graphics g) {
        for (Drawn d : objects) {
            x = focus.getX();
            y = focus.getY();

            Graphics2D g2d = (Graphics2D) g;

            double[] xv = d.getXVerts();
            double[] yv = d.getYVerts();
            double xp = d.getX();
            double yp = d.getY();
            double r = d.getRotation();

            double cx = 0, cy = 0;

            for (int i = 0; i < xv.length; i++) {
                xv[i] = xv[i] + width / 2 + xp - x;
                yv[i] = yv[i] + height / 2 + yp - y;

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