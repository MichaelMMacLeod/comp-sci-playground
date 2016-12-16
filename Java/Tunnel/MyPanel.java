import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private int width, height, iterations, lineWidth;
	private double rot, zoom, dx, dy;
	private InputManager input;
	private boolean xs, ys;

	public MyPanel(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		input = new InputManager(this);
		input.addKey("z");
		input.addKey("a");
		input.addKey("w");
		input.addKey("d");
		input.addKey("s");
		input.addKey("l");

		restart();
	}

	private void restart() {
		iterations = 5;
		lineWidth = 3;
		zoom = 1;
		rot = 0;
		dx = 0;
		dy = 0;
		xs = false;
		ys = false;
	}
	
	public void updateLogic() {
		rot += 0.5;
		zoom += 0.01;

		// calculate the number of iterations based on current rotation
		// use f(x) = sin(x) * 3 + 3
		// range: [0, 6]
		if (input.pressed("z")) {
			iterations = (int) (Math.sin(zoom) * 3 + 3);
		}

		if (input.pressed("l")) {
			lineWidth = (int) (Math.sin(zoom) * 2 + 3);
		}

		if (input.pressed("a")) {
			dx -= (xs ? 1 : -1) * 0.01;
		}
		if (input.pressed("w")) {
			dy += (ys ? 1 : -1) * 0.01;
		}
		if (input.pressed("d")) {
			dx += (xs ? 1 : -1) * 0.01;
		}
		if (input.pressed("s")) {
			dy -= (ys ? 1 : -1) * 0.01;
		}

		if (Math.abs(dx) > Math.PI / 2) {
			xs = !xs;
		}
		if (Math.abs(dy) > Math.PI / 2) {
			ys = !ys;
		}
	}
	
	// recursively draws a side of the snowflake
	private void snowflake(Pen p, int order, double size) {
		if (order == 0) {
			p.draw(size);
		} else {
			int[] angles = {60, -120, 60, 0};
			for (int i : angles) {
				snowflake(p, order - 1, size / 3);
				p.rotate(i);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);

		// so we can do rotations easily
		Graphics2D g2D = (Graphics2D) g;

		int cx = getWidth() / 2, cy = getHeight() / 2;

		// zoom in with the function f(x) = sin(x) / 2 + 1,
		// range: [0.5, 2]
		int size = (int) ((cx + cy) / 4 * (Math.sin(zoom) / 2 + 1));

		// rotate around the center
		// g2D.rotate(rot * Math.PI / 180, cx, cy);

		g2D.setStroke(new BasicStroke(lineWidth));

		if (xs ^ ys) {
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < 360; i += 16) {
				Pen p = new Pen(g2D, cx, cy, -(rot + i), false);
				p.setIncrease(dx, dy);
				snowflake(p, iterations, size);
			}
			g2D.setColor(Color.BLUE);
			for (int i = 0; i < 360; i += 16) {
				Pen p = new Pen(g2D, cx, cy, rot + i, true);
				p.setIncrease(dx, dy);
				snowflake(p, iterations, size);
			}
		} else {
			g2D.setColor(Color.BLUE);
			for (int i = 0; i < 360; i += 16) {
				Pen p = new Pen(g2D, cx, cy, -(rot + i), true);
				p.setIncrease(dx, dy);
				snowflake(p, iterations, size);
			}
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < 360; i += 16) {
				Pen p = new Pen(g2D, cx, cy, rot + i, false);
				p.setIncrease(dx, dy);
				snowflake(p, iterations, size);
			}
		}

		g2D.setColor(Color.BLUE);
		g2D.drawLine(cx, 
			cy, 
			cx + (int) (size * Math.tan(dx)), 
			cy + (int) (size * Math.tan(dy)));
		g2D.setColor(Color.GREEN);
		g2D.drawLine(cx, 
			cy, 
			cx + (int) (size * Math.tan(-dx)), 
			cy + (int) (size * Math.tan(-dy)));
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}