import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.Action;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private KeyLis keyLis = new KeyLis(this);
	private Ship ship;

	public GamePanel(double width, double height) {
		
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		restart();
	}

	public void restart() {
		ship = new Ship(width / 2, height / 2, 75, 50, 0.7);
	}

	public void updateLogic() {

		while (keyLis.pressedCommands() > 0) {
			KeyHandler.handle(keyLis.getPressed(), true);
		}

		while (keyLis.releasedCommands() > 0) {
			KeyHandler.handle(keyLis.getReleased(), false);
		}

		if (KeyHandler.check("a")) {
			ship.rotate(false);
		}
		if (KeyHandler.check("w")) {
			ship.thrust();
		}
		if (KeyHandler.check("d")) {
			ship.rotate(true);
		}
		if (KeyHandler.check("q")) {
			System.exit(0);
		}

		ship.update();
	}

	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		super.paintComponent(g2d);

		g2d.setColor(Color.WHITE);
		g2d.translate(ship.getTranslation()[0], ship.getTranslation()[1]);
		g2d.rotate(ship.getRotation());

		g2d.fillPolygon(ship.polygon());
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	private static class KeyHandler {

		private static boolean[] keys = new boolean[127];

		private static int valueOf(String key) {

			int value = key.charAt(0);

			return value;
		}

		public static void handle(String key, boolean pressed) {
			keys[valueOf(key)] = pressed;
		}

		public static boolean check(String key) {
			return keys[valueOf(key)];
		}
	}

	private static class KeyLis {

		private String[] pressedLog = new String[0];
		private String[] releasedLog = new String[0];
		
		public KeyLis(GamePanel panel) {

			Action press = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					String[] pressedLogNew = new String[pressedLog.length + 1];
					for (int i = 0; i < pressedLog.length; i++) {
						pressedLogNew[i] = pressedLog[i];
					}
					pressedLogNew[pressedLogNew.length - 1] = e.getActionCommand();

					pressedLog = pressedLogNew;
				}
			};

			Action release = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					String[] releasedLogNew = new String[releasedLog.length + 1];
					for (int i = 0; i < releasedLog.length; i++) {
						releasedLogNew[i] = releasedLog[i];
					}
					releasedLogNew[releasedLogNew.length - 1] = e.getActionCommand();

					releasedLog = releasedLogNew;
				}
			};

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("Q"), "pressed");

			panel.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released W"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released Q"), "released");

			panel.getActionMap().put("released", release);
			panel.getActionMap().put("pressed", press);
		}

		public String getPressed() {

			if (pressedLog.length == 0) {
				return "";
			}

			String cmd = pressedLog[0];

			String[] pressedLogNew = new String[pressedLog.length - 1];
			for (int i = 0; i < pressedLogNew.length; i++) {
				pressedLogNew[i] = pressedLog[i + 1];
			}
			pressedLog = pressedLogNew;

			return cmd;
		}

		public String getReleased() {

			if (releasedLog.length == 0) {
				return "";
			}

			String cmd = releasedLog[0];

			String[] releasedLogNew = new String[releasedLog.length - 1];
			for (int i = 0; i < releasedLogNew.length; i++) {
				releasedLogNew[i] = releasedLog[i + 1];
			}
			releasedLog = releasedLogNew;

			return cmd;
		}

		public int pressedCommands() { return pressedLog.length; }
		public int releasedCommands() { return releasedLog.length; }
	}
}