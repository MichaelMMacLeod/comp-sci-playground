import java.awt.Graphics;

public class Pen {

	private double x, y, rot;
	private Graphics g;

	public Pen(Graphics g, double x, double y, double rot) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.rot = rot;
	}

	// draws a straight line in the direction 'rot'
	public void draw(double distance) {
		double xNew = x + distance * Math.cos(rot * Math.PI / 180);
		double yNew = y + distance * Math.sin(rot * Math.PI / 180);

		xNew -= distance / 2;
		yNew += Math.tan(30 * Math.PI / 180) * distance / 2;

		g.drawLine((int) x, 
			(int) y, 
			(int) xNew, 
			(int) yNew);

		x = xNew;
		y = yNew;
	}

	// change the direction the pen is facing
	public void rotate(double angle) {
		rot += angle;
	}
}