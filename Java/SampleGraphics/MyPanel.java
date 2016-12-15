import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Polygon;

public class MyPanel extends JPanel {

	private int width, height, iterations;
	private double rot;
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
		iterations = 4;
		rot = 0;
	}
	
	public void updateLogic() {
		rot += 0.1;
	}
	
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

		int cx = width / 2, cy = height / 2, size = 500;

		// int[] angles = {0, 60, 120, 180, 240};

		for (int i = 0; i < 360; i += 4) {
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