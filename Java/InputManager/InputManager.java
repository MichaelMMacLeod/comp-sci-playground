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

	/**
	 * Creates an InputManager.
	 * 
	 * @param panel is the container.
	 */
	public InputManager(JPanel panel) {
		this.panel = panel;

		keyValues = new String[0];
		keys = new boolean[0];

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

	/**
	 * Checks if a key is pressed.
	 * 
	 * @param  key is the key to check.
	 * @return     true if the key is pressed, and its delay is 0. 
	 *             Otherwise false.
	 */
	public boolean pressed(String key) {
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
	 * @param key     is the key the manager will start tracking.
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

		panel.getInputMap().put(KeyStroke.getKeyStroke(key), 
			"pressed");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released " + key), 
			"released");
	}
}