import java.awt.Polygon;
import java.awt.Color;

import java.util.Arrays;

public abstract class Drawn {

    public Drawn(int x,
        int y,
        int[] xVerts, 
        int[] yVerts,
        int size, 
        double rotation,
        Color color) {

        this.x = x;
        this.y = y;

        for (int i = 0; i < xVerts.length; i++) {
            xVerts[i] *= size;
            yVerts[i] *= size;
        }
        polygon = new Polygon(xVerts, yVerts, xVerts.length);

        this.rotation = rotation;
        this.color = color;
    }

    protected double x, y;
    protected int getX() { return (int) x; }
    protected int getY() { return (int) y; }
    protected void moveX(double distance) {
        x += distance;
    }
    protected void moveY(double distance) {
        y += distance;
    }

    protected Polygon polygon;
    protected int[] getXVerts() {
        int[] xpoints = Arrays.copyOf(polygon.xpoints, polygon.xpoints.length);
        for (int i = 0; i < xpoints.length; i++) {
            xpoints[i] += getX();
        }
        return xpoints;
    }
    protected int[] getYVerts() {
        int[] ypoints = Arrays.copyOf(polygon.ypoints, polygon.ypoints.length);
        for (int i = 0; i < ypoints.length; i++) {
            ypoints[i] += getY();
        }
        return ypoints;
    }

    protected double rotation;
    protected double getRotation() { return rotation; }
    public void rotate(double radians) {
    	rotation += radians;
    }
    
    protected Color color;
    protected Color getColor() { return color; }
}