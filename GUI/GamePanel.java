import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private Grid map;
	private Snake snake = new Snake(15, 15, 10);
	private KeyLis listener;

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		this.map = new Grid(30, 20, 1);
		listener = new KeyLis();
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(listener);
	}

	public void updateLogic() {

	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {

				if (map.getCell(i, j) > 0) {
					g.setColor(Color.BLUE);
				} else if (map.getCell(i, j) == -1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				
				g.fillRect(
					i * map.getBuffer(), 
					j * map.getBuffer(), 
					map.getTileSize(), 
					map.getTileSize());
			}
		}
	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}
	
	private class KeyLis extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			System.out.println(e);
		}
	}
}