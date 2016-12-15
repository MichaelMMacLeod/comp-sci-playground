import java.awt.Graphics;

public class Pen {

	private double dx, dy;
	private double x, y, rot;
	private Graphics g;

	public Pen(Graphics g, double x, double y, double rot) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.rot = rot;

		dx = 0;
		dy = 0;
	}

	public void setIncrease(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	// draws a straight line in the direction 'rot'
	public void draw(double distance) {
		double xNew = x + distance * Math.cos(rot * Math.PI / 180);
		double yNew = y + distance * Math.sin(rot * Math.PI / 180);

		// xNew -= distance / 2;
		xNew += dx;
		yNew += dy;

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