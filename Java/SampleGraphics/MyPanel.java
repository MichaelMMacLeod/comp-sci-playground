import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Polygon;

public class MyPanel extends JPanel {

	private int width, height, iterations;
	private InputManager input;

	public MyPanel(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;

		iterations = 0;

		this.setFocusable(true);
		this.requestFocus();

		input = new InputManager(this);

		restart();
	}

	private void restart() {}
	
	public void updateLogic() {}
	
	public Polygon snowflake() {
		Polygon poly = new Polygon();
		int size = 400;

		int cx = width / 2, cy = height / 2;
		double a = size / Math.sqrt(3), b = size;

		poly.addPoint((int) (cx - b), (int) (cy + a));
		poly.addPoint((int) (cx + b), (int) (cy + a));
		poly.addPoint((int) cx, (int) (cy - Math.sqrt(a * a + b * b)));

		return poly;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawPolygon(snowflake());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}