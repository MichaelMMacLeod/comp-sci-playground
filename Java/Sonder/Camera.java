import java.awt.Graphics;

public class Camera {

    private int width, height, x, y;
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
        double[] xv = object.getXVerts();
        double[] yv = object.getYVerts();
        double xp = object.getX();
        double yp = object.getY();
        double r = object.getRotation();

        // Apply a 2D rotation matrix.
        double cos = Math.cos(r);
        double sin = Math.sin(r);
        for (int i = 0; i < xv.length; i++) {
            xv[i] = xv[i] * cos - yv[i] * sin;
            yv[i] = xv[i] * sin + yv[i] * cos;
        }

        for (int i = 0; i < xv.length; i++) {
            xv[i] = xv[i] + width / 2;
            yv[i] = yv[i] + height / 2;
        }

        int[] xvInt = new int[xv.length];
        int[] yvInt = new int[yv.length];
        for (int i = 0; i < xv.length; i++) {
            xvInt[i] = (int) xv[i];
            yvInt[i] = (int) yv[i];
        } 

        g.setColor(object.getColor());
        g.drawPolygon(xvInt, yvInt, xv.length);
    }
}