import java.awt.Graphics;
import java.awt.Graphics2D;

public class Camera {

    private int width, height;
    private double x, y;
    private Drawn focus;

    public Camera(int width, int height, Drawn focus) {
        this.width = width;
        this.height = height;
        this.focus = focus;
    }

    public void setFocus(Drawn focus) {
        this.focus = focus;
    }

    public void draw(Graphics g, Drawn object) {
        x = focus.getX();
        y = focus.getY();

        Graphics2D g2d = (Graphics2D) g;

        double[] xv = object.getXVerts();
        double[] yv = object.getYVerts();
        double xp = object.getX();
        double yp = object.getY();
        double r = object.getRotation();

        double cx = 0, cy = 0;

        for (int i = 0; i < xv.length; i++) {
            xv[i] = xv[i] + width / 2 + xp - x;
            yv[i] = yv[i] + height / 2 + yp - y;

            cx += xv[i] + xp - x;
            cy += yv[i] + yp - y;
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

        g.setColor(object.getColor());
        g.drawPolygon(xvInt, yvInt, xv.length);

        g2d.rotate(-r, cx, cy);
    }
}