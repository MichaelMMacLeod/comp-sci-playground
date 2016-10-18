import javax.swing.*;
import java.awt.*;

class Panel extends JPanel {

	private int width, height;

	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
		Grid map = new Grid(30);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(20, 20, 40, 40);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}