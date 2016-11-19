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
	private Selection sel;
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
		board = new Board(15, 15);
		sel = new Selection(7, 7, 15, 15);
	}

	public void updateLogic() {
		switch (keyLis.getCmd()) {
			case "a":
				sel.moveTo(sel.getColumn() - 1, sel.getRow());
				break;
			case "w":
				sel.moveTo(sel.getColumn(), sel.getRow() - 1);
				break;
			case "d":
				sel.moveTo(sel.getColumn() + 1, sel.getRow());
				break;
			case "s":
				sel.moveTo(sel.getColumn(), sel.getRow() + 1);
				break;
			case "\n":
				board.remove(sel.getColumn(), sel.getRow());
				break;
			default: break;
		}
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

		Color transparentGreen = new Color(0, 255, 0, 100);
		g.setColor(transparentGreen);
		g.fillRect(sel.getColumn() * 30, 0, 28, width());
		g.fillRect(0, sel.getRow() * 30, height(), 28);

		if (board.isClear()) {
			System.out.println("Clear!");
			System.exit(0);
		}
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

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
			panel.getActionMap().put("pressed", press);
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