import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager {

	// Container.
	private JPanel panel;

	// List of keys the manager is tracking.
	private String[] keyValues;
	private boolean[] keys;
	// Set to true once pressed() is called, set to false when the key is
	// released. 
	private boolean[] checked;

	/**
	 * Creates an InputManager.
	 * 
	 * @param panel is the container.
	 */
	public InputManager(JPanel panel) {
		this.panel = panel;

		keyValues = new String[0];
		keys = new boolean[0];
		checked = new boolean[0];

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
						checked[i] = false;
						break;
					}
				}
			}
		};

		panel.getActionMap().put("pressed", pressed);
		panel.getActionMap().put("released", released);
	}

	/**
	 * Checks if the key is pressed
	 * 
	 * @param  key is the key to check,
	 * @return     true if the key is pressed, false if the key is not pressed.
	 *             If this method returns true, subsequent calls on the same
	 *             key will return false until it is released and pressed
	 *             again.
	 */
	public boolean pressed(String key) {
		for (int i = 0; i < keyValues.length; i++) {
			if (keyValues[i].equalsIgnoreCase(key) && keys[i] && !checked[i]) {
				checked[i] = true;
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if a key is currently being held.
	 * 
	 * @param  key is the key to check.
	 * @return     true if the key is pressed, otherwise false
	 */
	public boolean held(String key) {
		for (int i = 0; i < keyValues.length; i++) {
			if (keyValues[i].equalsIgnoreCase(key) && keys[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Signals the manager to start tracking a key.
	 *
	 * @param key is the key the manager will start tracking.
	 */
	public void addKey(String key) {
		key = key.toUpperCase();

		String[] kv = new String[keyValues.length + 1];
		for (int i = 0; i < keyValues.length; i++) {
			kv[i] = keyValues[i];
		}
		kv[keyValues.length] = key;
		keyValues = kv;

		boolean[] k = new boolean[keys.length + 1];
		for (int i = 0; i < keys.length; i++) {
			k[i] = keys[i];
		}
		k[keys.length] = false;
		keys = k;

		boolean[] c = new boolean[checked.length + 1];
		for (int i = 0; i < checked.length; i++) {
			c[i] = checked[i];
		}
		c[checked.length] = false;
		checked = c;

		panel.getInputMap().put(KeyStroke.getKeyStroke(key), 
			"pressed");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released " + key), 
			"released");
	}
}