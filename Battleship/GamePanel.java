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
	private int mapWidth = 15;
	private int mapHeight = 15;
	private int[][] map;
	private Ship ship1;
	private Ship ship2;
	private Ship ship3;
	private Ship ship4;

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();

		map = new int[mapHeight][mapWidth];

		restart();
	}
	
	public void setCell(int x, int y, int value) {
		map[y][x] = value;
	}

	public int width() { return width; }
	public int height() { return height; }

	public void restart() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				setCell(i, j, -1);
			}
		}

		ship1 = new Ship(1, map, this);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print((map[j][i] < 0 ? 1 : 0) + " ");
			}
			System.out.println("printed 1");
		}
		System.out.println();
		ship2 = new Ship(2, map, this);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print((map[j][i] < 0 ? 1 : 0) + " ");
			}
			System.out.println("printed 2");
		}
		System.out.println();
		ship3 = new Ship(3, map, this);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print((map[j][i] < 0 ? 1 : 0) + " ");
			}
			System.out.println("printed 3");
		}
		System.out.println();
		ship4 = new Ship(4, map, this);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print((map[j][i] < 0 ? 1 : 0) + " ");
			}
			System.out.println("printed 4");
		}
		System.out.println();
	}

	public void updateLogic() {

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				if (map[i][j] > 0)
					g.setColor(Color.RED);
				else
					g.setColor(Color.BLACK);
				
				g.fillRect(i * 30, j * 30, 28, 28);
			}
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