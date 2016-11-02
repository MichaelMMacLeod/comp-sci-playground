import java.awt.Color;

public class Zone extends Circle {

	public Zone(double x, double y, double diameter, Color color) {

		this.x = x - diameter / 2;
		this.y = y - diameter / 2;
		this.diameter = diameter;
		this.color = color;
	}
}