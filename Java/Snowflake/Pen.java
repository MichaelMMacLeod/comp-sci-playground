import java.awt.Graphics2D;

public class Pen {

	private double dx, dy;
	private double x, y, rot;
	private Graphics2D g;
	private boolean reflect;

	public Pen(Graphics2D g, double x, double y, double rot, boolean reflect) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.rot = rot;
		this.reflect = reflect;

		dx = 0;
		dy = 0;
	}

	public void setIncrease(double dx, double dy) {
		this.dx = (reflect ? -1 : 1) * Math.tan(dx);
		this.dy = (reflect ? -1 : 1) * Math.tan(dy);
	}

	// draws a straight line in the direction 'rot'
	public void draw(double distance) {
		double xNew = x + distance * Math.cos(rot * Math.PI / 180);
		double yNew = y + distance * Math.sin(rot * Math.PI / 180);

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