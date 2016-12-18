import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		this.setFocusable(true);
		this.requestFocus();
		input.addKey("a", 100000000);
	}

	public void update() {
		if (input.pressed("a")) {
			System.out.println(true);
		}
	}
}