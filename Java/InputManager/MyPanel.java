import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		input.addKey("a");
	}

	public void update() {
		if (input.pressed("a")) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.WHITE);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}