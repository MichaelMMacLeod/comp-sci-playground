import java.awt.Color;

import java.util.Arrays;

public abstract class Drawn {

    protected double x, y;
    protected double getX() { return x; }
    protected double getY() { return y; }

    protected double[] xVerts;
    protected double[] yVerts;
    protected double[] getXVerts() {
        return Arrays.copyOf(xVerts, xVerts.length);
    }
    protected double[] getYVerts() {
        return Arrays.copyOf(yVerts, yVerts.length);
    }

    protected double rotation;
    protected double getRotation() { return rotation; }
    
    protected Color color;
    protected Color getColor() { return color; }
}