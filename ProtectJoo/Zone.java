import java.awt.Color;

public class Zone extends Circle {

	public Zone(double x, double y, double diameter, Color color) {

		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.color = color;
	}

	public void incrementDiameter() {

		diameter += 10;
	}

	public double getX() { return x - diameter / 2; }
	public double getY() { return y - diameter / 2; }
}