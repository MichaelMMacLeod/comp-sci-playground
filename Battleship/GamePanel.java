import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;

	private KeyLis keyLis = new KeyLis(this);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
	}
		
	public int width() { return width; }
	public int height() { return height; }

	public void restart() {

	}

	public void updateLogic() {

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}

	private static class KeyLis {

		private enum Command {
			A, W, D, S, FIRE, QUIT
		}

		private Command[] log;
		
		public KeyLis(GamePanel panel) {

			Action press = new AbstractAction() {

				public void actionPerformed(ActionEvent e) {

					Command cmd = e.getActionCommand();
				}
			};

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
			panel.getActionMap().put("pressed", press);
		}
	}
}