import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

public class MyPanel extends JPanel {

	private int width, height;

	public MyPanel(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		restart();
	}

	private void restart() {}
	
	public void updateLogic() {}
	
	@Override
	protected void paintComponent(Graphics g) {}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}