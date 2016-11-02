import java.awt.Color;

public class Zone {

	private Color color;
	private double x, y, diameter;

	public Zone(double x, double y, double diameter, Color color) {

		this.x = x - diameter / 2;
		this.y = y - diameter / 2;
		this.diameter = diameter;
		this.color = color;
	}

	public Color getColor() { return color; }
	public double getDiameter() { return diameter; }
	public double getX() { return x; }
	public double getY() { return y; }
}