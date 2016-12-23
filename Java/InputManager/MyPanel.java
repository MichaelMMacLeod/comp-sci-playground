import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		input.addKey("a");
		input.addKey("w");
	}

	public void update() {
		if (input.held("a")) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.WHITE);
		}

		if (input.pressed("a")) {
			System.out.println("Pressed a");
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}