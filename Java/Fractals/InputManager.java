import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager {

	private JPanel panel;
	private String[] keyValues;
	private boolean[] keys;
	private int[] keyDelays;
	private int[] delays;

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
	}

	public void update() {
		for (int i = 0; i < delays.length; i++) {
			if (delays[i] > 0) {
				delays[i]--;
			}
		}
	}

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
	 * @param delay is the time in between key presses
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
}