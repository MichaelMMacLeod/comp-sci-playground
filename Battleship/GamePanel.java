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
	private Board board;
	private KeyLis keyLis = new KeyLis(this);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();

		restart();
	}

	public int width() { return width; }
	public int height() { return height; }

	public void restart() {
		board = new Board(30, 30);
	}

	public void updateLogic() {

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Color[][] pieces = board.pieces();
		for (int row = 0; row < pieces.length; row++) {
			for (int column = 0; column < pieces[row].length; column++) {
				g.setColor(pieces[row][column]);
				g.fillRect(row * 30, column * 30, 28, 28);
			}
		}

		if (board.isClear()) {
			System.out.println("Clear!");
		}
	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}

	private static class KeyLis {

		private String[] log = {""};
		
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

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
			panel.getActionMap().put("pressed", press);
		}

		/**
		 * Returns log[0] and shifts the array left by 1
		 */
		public String getCmd() {

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