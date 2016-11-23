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

	public GamePanel(double width, double height) {
		
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		restart();
	}

	public void restart() { }

	public void updateLogic() {
		switch (keyLis.getCmd()) {
			case "a":
				break;
			case "w":
				break;
			case "d":
				break;
			case "s":
				break;
			case "escape":
				System.exit(0);
				break;
			default: break;
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	private static class KeyLis {

		private String[] log = new String[0];
		
		public KeyLis(GamePanel panel) {

			Action press = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					String[] logNew = new String[log.length + 1];
					for (int i = 0; i < log.length; i++) {
						logNew[i] = log[i];
					}
					logNew[logNew.length - 1] = e.getActionCommand();

					log = logNew;
				}
			};

			Action exit = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					String[] logNew = new String[log.length + 1];
					for (int i = 0; i < log.length; i++) {
						logNew[i] = log[i];
					}
					logNew[logNew.length - 1] = "escape";

					log = logNew;
				}
			};

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "exit");
			panel.getActionMap().put("pressed", press);
			panel.getActionMap().put("exit", exit);
		}

		/**
		 * Returns the oldest command in the list and removes it
		 */
		public String getCmd() {

			if (log.length == 0) {
				return "";
			}

			String cmd = log[0];

			String[] logNew = new String[log.length - 1];
			for (int i = 0; i < logNew.length; i++) {
				logNew[i] = log[i + 1];
			}
			log = logNew;

			return cmd;
		}
	}
}