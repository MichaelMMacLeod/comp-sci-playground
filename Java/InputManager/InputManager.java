import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class InputManager {

	// Container.
	private JPanel panel;

	// List of keys the manager is tracking.
	private ArrayList<String> keyValues;
	private ArrayList<Boolean> keys;
	// Set to true once pressed() is called, set to false when the key is
	// released. 
	private ArrayList<Boolean> checked;

	// X and y coordinate of the mouse inside the container
	private Point mouse;

	/**
	 * Creates an InputManager.
	 * 
	 * @param panel is the container.
	 */
	public InputManager(JPanel panel) {
		this.panel = panel;

		mouse = new Point();

		keyValues = new ArrayList<String>();
		keys = new ArrayList<Boolean>();
		checked = new ArrayList<Boolean>();

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				mouse = e.getPoint();
			}
		});

		Action pressed = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < keyValues.size(); i++) {
					if (keyValues.get(i).equalsIgnoreCase(
						e.getActionCommand())) {
						keys.set(i, true);
						break;
					}
				}
			}
		};
		Action released = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < keyValues.size(); i++) {
					if (keyValues.get(i).equalsIgnoreCase(
						e.getActionCommand())) {
						keys.set(i, false);
						checked.set(i, false);
						break;
					}
				}
			}
		};

		panel.getActionMap().put("pressed", pressed);
		panel.getActionMap().put("released", released);
	}

	public double mousex() { return mouse.getX(); }
	public double mousey() { return mouse.getY(); }

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
		for (int i = 0; i < keyValues.size(); i++) {
			if (keyValues.get(i).equalsIgnoreCase(key) 
				&& keys.get(i) 
				&& !checked.get(i)) {
				checked.set(i, true);
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
		for (int i = 0; i < keyValues.size(); i++) {
			if (keyValues.get(i).equalsIgnoreCase(key) && keys.get(i)) {
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

		keyValues.add(key);
		keys.add(false);
		checked.add(false);

		panel.getInputMap().put(KeyStroke.getKeyStroke(key), 
			"pressed");
		panel.getInputMap().put(KeyStroke.getKeyStroke("released " + key), 
			"released");
	}
}