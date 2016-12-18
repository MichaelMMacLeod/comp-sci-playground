import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager implements Runnable {

	// Container.
	private JPanel panel;

	// List of keys the manager is tracking.
	private String[] keyValues;
	private boolean[] keys;

	// List of key delays the manager is tracking.
	// Key delay is the time the manager waits before logging a key press.
	private int[] keyDelays;
	private int[] delays;

	/**
	 * Creates an InputManager.
	 * 
	 * @param panel is the container.
	 */
	public InputManager(JPanel panel) {
		this.panel = panel;

		keyValues = new String[0];
		keys = new boolean[0];
		keyDelays = new int[0];
		delays = new int[0];

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

		Thread keyUpdate = new Thread(this);
		keyUpdate.start();
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
			if (keyValues[i].equalsIgnoreCase(key)) {
				if (delays[i] == 0 && keys[i]) {
					delays[i] = keyDelays[i];

					return true;
				}

				return false;
			}
		}
		
		return false;
	}

	/**
	 * Signals the manager to start tracking a key with a delay of 0.
	 *
	 * @param key is the key the manager will start tracking.
	 */
	public void addKey(String key) {
		addKey(key, 0);
	}
	
	/**
	 * Signals the manager to start tracking a key with a certain delay.
	 *
	 * @param key   is the key the manager will start tracking.
	 * @param delay is the time in between key presses.
	 */
	public void addKey(String key, int delay) {
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

		int[] kd = new int[keyDelays.length + 1];
		for (int i = 0; i < keyDelays.length; i++) {
			kd[i] = keyDelays[i];
		}
		kd[keyDelays.length] = delay;
		keyDelays = kd;

		int[] d = new int[delays.length + 1];
		for (int i = 0; i < delays.length; i++) {
			d[i] = delays[i];
		}
		d[delays.length] = 0;
		delays = d;

		panel.getInputMap().put(KeyStroke.getKeyStroke(key), "pressed");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released " + key), "released");
	}
	
	public void run() {
		while (true) {
			update();
		}
	}

	/**
	 * Updates key press delays.
	 */
	private void update() {
		for (int i = 0; i < delays.length; i++) {
			if (delays[i] > 0) {
				delays[i]--;
			}
		}
	}
}