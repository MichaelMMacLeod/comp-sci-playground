import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private int width, height, iterations;
	private double rot, zoom;
	private InputManager input;

	public MyPanel(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		input = new InputManager(this);
		input.addKey("z");

		restart();
	}

	private void restart() {
		iterations = 6;
		zoom = 1;
		rot = 0;
	}
	
	public void updateLogic() {
		rot += 0.1;
		zoom += 0.01;

		// calculate the number of iterations based on current rotation
		// use f(x) = sin(x) * 3 + 3
		// range: [0, 6]
		if (input.pressed("z")) {
			iterations = (int) (Math.sin(zoom) * 3 + 3);
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

		// draw each side of the snowflake
		for (int i = 0; i < 360; i += 8) {
			snowflake(new Pen(g, 
				cx, 
				cy, 
				rot + i), iterations, size);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}