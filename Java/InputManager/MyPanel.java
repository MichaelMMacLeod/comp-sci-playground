import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		this.setFocusable(true);
		this.requestFocus();
		input.addKey("a");
		input.addKeyPress("b");
		input.addKeyRelease("c");
	}
}