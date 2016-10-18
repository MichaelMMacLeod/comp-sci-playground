import javax.swing.*;
import java.awt.*;

class Panel extends JPanel {

	private int width, height;

	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("i hope this works", 20, 20);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}