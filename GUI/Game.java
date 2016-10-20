import javax.swing.*;
import java.awt.*;

public class Game {

	public static GamePanel gamePanel = new GamePanel(629, 629);

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		gameLoop();
	}

	private static void gameLoop() {

		int MS_PER_UPDATE = 250;
		double previous = System.currentTimeMillis();
		double lag = 0;

		while (true) {

			// Get input as often as we can
			gamePanel.getInput();
			
			double current = System.currentTimeMillis();
			double elapsed = current - previous;
			previous = current;
			lag += elapsed;

			// So the game runs at a constant speed on slower machines
			while (lag >= MS_PER_UPDATE) {
				gamePanel.updateLogic();
				lag -= MS_PER_UPDATE;
			}

			// Render the game as often as we can
			gamePanel.repaint();
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Game");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.pack();
		frame.setVisible(true);
	}
}