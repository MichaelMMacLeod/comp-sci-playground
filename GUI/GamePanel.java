import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private Grid map = new Grid(30, 20, 1);
	private Food food = new Food();
	private Snake snake = new Snake(15, 15, 2, 2);
	private KeyLis listener = new KeyLis();
	private KeyLog log = new KeyLog();
	private final String LEFT = "LEFT";
	private final String UP = "UP";
	private final String RIGHT = "RIGHT";
	private final String DOWN = "DOWN";
	private final String NONE = "NONE";
	private final Color SNAKE_COLOR = Color.GREEN;
	private final Color FOOD_COLOR = Color.RED;
	private final Color TILE_COLOR = Color.WHITE;

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(listener);

		food.newLocation(map);
	}

	public void getInput() {

		if (!log.getKey().equals(NONE)) {
			snake.setDirection(log.getKey());
		}
	}
	
	public void updateLogic() {

		switch (snake.getDirection()) {
			case LEFT:
				snake.setSnoutX(snake.getSnoutX() - 1);
				break;
			case UP:
				snake.setSnoutY(snake.getSnoutY() - 1);
				break;
			case RIGHT:
				snake.setSnoutX(snake.getSnoutX() + 1);
				break;
			case DOWN:
				snake.setSnoutY(snake.getSnoutY() + 1);
				break;
			default:
		}

		log.shift();
		
		// Close the program when the snake goes out of bounds
		if (snake.getSnoutX() > map.getSize() - 1 || snake.getSnoutX() < 0 || 
			snake.getSnoutY() > map.getSize() - 1 || snake.getSnoutY() < 0) {
			System.exit(0);
		}
		
		if (snake.getSnoutX() == food.getX() && snake.getSnoutY() == food.getY()) {
			food.newLocation(map);
			snake.incrementSize();
		}
		
		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {

				// Close the program when the snake hits itself
				if (i == snake.getSnoutX() && j == snake.getSnoutY() && map.getCell(i, j) > 0 && !snake.getDirection().equals(NONE)) {
					System.exit(0);
				}
				
				if (i == snake.getSnoutX() && j == snake.getSnoutY()) {
					map.setCell(i, j, snake.getSize());
				} 

				if (map.getCell(i, j) > 0) {
					map.decrementCell(i, j);
				}

				if (i == food.getX() && j == food.getY()) {
					map.setCell(i, j, -1);
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {

				if (map.getCell(i, j) > 0) {
					g.setColor(SNAKE_COLOR);
				} else if (map.getCell(i, j) == -1) {
					g.setColor(FOOD_COLOR);
				} else {
					g.setColor(TILE_COLOR);
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

			// Don't instantly-kill yourself if you press the key opposite  
			// your direction, that's just annoying
			String opposite = log.oppositeKey(snake.getDirection());

			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					if (!opposite.equals(LEFT)) {
						log.addKey(LEFT);
					}
					break;
				case KeyEvent.VK_W:
					if (!opposite.equals(UP)) {
						log.addKey(UP);
					}
					break;
				case KeyEvent.VK_D:
					if (!opposite.equals(RIGHT)) {
						log.addKey(RIGHT);
					}
					break;
				case KeyEvent.VK_S:
					if (!opposite.equals(DOWN)) {
						log.addKey(DOWN);
					}
					break;
			}
		}
	}
}