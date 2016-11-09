import java.awt.Color;

public abstract class Circle {

	protected Color color;
	protected double diameter, x, y;

	public Color getColor() { return color; }
	public double getDiameter() { return diameter; }
	public double getX() { return x; }
	public double getY() { return y; }
	public double getCenterX() { return getX() + getDiameter() / 2; }
	public double getCenterY() { return getY() + getDiameter() / 2; }
}