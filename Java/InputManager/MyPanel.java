import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		this.setFocusable(true);
		this.requestFocus();
		input.addKey("a");
		input.addKey("w");
		input.addKey("d");
		input.addKey("s");
	}

	public void update() {
		System.out.print("a " + input.pressed("a") + " ");
		System.out.print("w " + input.pressed("w") + " ");
		System.out.print("d " + input.pressed("d") + " ");
		System.out.println("s " + input.pressed("s"));
	}
}