import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager {

	JPanel panel;
	String[] keysValues = new String[0];
	boolean[] keys = new boolean[0];

	public InputManager(JPanel panel) {
		this.panel = panel;

		Action pressed = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// e = e.getActionCommand();
				System.out.println("pressed " + e.getActionCommand());
			}
		};
		Action released = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// e = e.getActionCommand().substring(9); // "released A" -> "A"
				System.out.println("released " + e.getActionCommand());
			}
		};

		panel.getActionMap().put("pressed", pressed);
		panel.getActionMap().put("released", released);
	}

	// public boolean pressed(String key) {

	// }

	public void addKeyPress(String key) {
		addIdentifier(key);
		key = key.toUpperCase();
		panel.getInputMap().put(KeyStroke.getKeyStroke(key), "pressed");
	}

	public void addKeyRelease(String key) {
		addIdentifier(key);
		key = "released " + key.toUpperCase();
		panel.getInputMap().put(KeyStroke.getKeyStroke(key), "released");
	}

	public void addKey(String key) {
		addKeyPress(key);
		addKeyRelease(key);
	}

	private void addIdentifier(String key) {
		for (String i : keysValues) {
			if (i.equals(key)) {
				return;
			}
		}

		String[] keysNew = new String[keysValues.length + 1];
		for (int i = 0; i < keysValues.length; i++) {
			keysNew[i] = keysValues[i];
		}
		keysNew[keysValues.length] = key;
		keysValues = keysNew;
	}
}