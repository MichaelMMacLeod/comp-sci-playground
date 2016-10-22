import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private Grid map = new Grid(30, 20, 1);
	private Food food = new Food();
	private Snake snake = new Snake(15, 15, 1, 2);
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
	private Menu menu = new Menu(20, 35);
	private MenuItem snakeLength = new MenuItem();

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(listener);

		food.newLocation(map);

		menu.addItem(snakeLength);
		menu.show();
	}

	public void getInput() {

		if (!log.getKey().equals(NONE)) {
			snake.setDirection(log.getKey());
		}
	}
	
	public void updateLogic() {

		switch (snake.getDirection()) {
			case LEFT:
				snake.setX(snake.getX() - 1);
				break;
			case UP:
				snake.setY(snake.getY() - 1);
				break;
			case RIGHT:
				snake.setX(snake.getX() + 1);
				break;
			case DOWN:
				snake.setY(snake.getY() + 1);
				break;
			default:
		}

		log.shift();
		
		// Close the program when the snake goes out of bounds
		if (snake.getX() > map.getSize() - 1 || snake.getX() < 0 || 
			snake.getY() > map.getSize() - 1 || snake.getY() < 0) {
			System.exit(0);
		}
		
		// Close the program when the snake hits itself
		if (map.getCell(snake.getX(), snake.getY()) > 0 && !snake.getDirection().equals(NONE)) {
			System.exit(0);
		}

		if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
			food.newLocation(map);
			snake.incrementSize();
		}
		
		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {

				if (map.getCell(i, j) > 0) {
					map.decrementCell(i, j);
				}

				if (i == snake.getX() && j == snake.getY()) {
					map.setCell(i, j, snake.getSize());
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

		snakeLength.setText("Size: " + Integer.toString(snake.getSize()));
		menu.display(g);
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
				case KeyEvent.VK_LEFT:
					if (!opposite.equals(LEFT)) {
						log.addKey(LEFT);
					}
					break;
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					if (!opposite.equals(UP)) {
						log.addKey(UP);
					}
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if (!opposite.equals(RIGHT)) {
						log.addKey(RIGHT);
					}
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					if (!opposite.equals(DOWN)) {
						log.addKey(DOWN);
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				case KeyEvent.VK_SPACE:
					menu.toggle();
					break;
				default:
			}
		}
	}
}