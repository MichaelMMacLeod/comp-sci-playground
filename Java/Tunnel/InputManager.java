import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager {

	JPanel panel;
	String[] keyValues = new String[0];
	boolean[] keys = new boolean[0];

	public InputManager(JPanel panel) {
		this.panel = panel;

		Action pressed = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < keyValues.length; i++) {
					if (keyValues[i].equalsIgnoreCase(e.getActionCommand())) {
						keys[i] = true;
						break;
					}
				}
			}
		};
		Action released = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < keyValues.length; i++) {
					if (keyValues[i].equalsIgnoreCase(e.getActionCommand())) {
						keys[i] = false;
						break;
					}
				}
			}
		};

		panel.getActionMap().put("pressed", pressed);
		panel.getActionMap().put("released", released);
	}

	public boolean pressed(String key) {
		for (int i = 0; i < keyValues.length; i++) {
			if (keyValues[i].equalsIgnoreCase(key)) {
				return keys[i];
			}
		}

		return false;
	}

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
		for (String i : keyValues) {
			if (i.equals(key)) {
				return;
			}
		}

		String[] keyValuesNew = new String[keyValues.length + 1];
		for (int i = 0; i < keyValues.length; i++) {
			keyValuesNew[i] = keyValues[i];
		}
		keyValuesNew[keyValues.length] = key;
		keyValues = keyValuesNew;

		boolean[] keysNew = new boolean[keys.length + 1];
		for (int i = 0; i < keys.length; i++) {
			keysNew[i] = keys[i];
		}
		keysNew[keys.length] = false;
		keys = keysNew;
	}
}