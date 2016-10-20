import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {

	private int width, height;
	private Grid map;

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		this.map = new Grid(30, 20, 1);
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
}