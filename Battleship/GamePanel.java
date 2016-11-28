import java.awt.event.KeyEvent;
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

	private int size, pixelsPerCell, pixels;
	private Board board;
	private Selection sel;
	private KeyLis keyLis = new KeyLis(this);
	private Image winImage = new ImageIcon("win.png").getImage();

	/**
	 * Creates a GamePanel object.
	 * The panel has size * size number of cells arranged in a square.
	 * Each of the cells are squares with dimensions pixelsPerCell * 
	 * pixelsPerCell.
	 */
	public GamePanel(int size, int pixelsPerCell, int windowBorderInPixels) {

		this.size = size;
		this.pixelsPerCell = pixelsPerCell;

		this.pixels = size * pixelsPerCell - windowBorderInPixels * 2;
		
		this.setFocusable(true);
		this.requestFocus();

		restart();
	}

	public int width() { return pixels; }
	public int height() { return pixels; }

	public void restart() {

		board = new Board(size, size);
		sel = new Selection(size / 2, size / 2, size, size);
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
			case " ":
				restart();
				break;
			case "escape":
				System.exit(0);
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
				g.fillRect(row * pixelsPerCell - 2, column * pixelsPerCell - 2, pixelsPerCell, pixelsPerCell);
			}
		}

		Color transparentGreen = new Color(0, 255, 0, 100);
		g.setColor(transparentGreen);
		g.fillRect(sel.getColumn() * pixelsPerCell - 2, 0, pixelsPerCell, width());
		g.fillRect(0, sel.getRow() * pixelsPerCell - 2, height(), pixelsPerCell);

		if (board.isClear()) {
			Color transparentWhite = new Color(100, 100, 100, 150);
			g.setColor(transparentWhite);
			g.fillRect(0, 0, height(), width());
			g.drawImage(winImage, 0, 0, height(), width(), this);
		}
	}

	public Dimension getPreferredSize() {

		return new Dimension(pixels, pixels);
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