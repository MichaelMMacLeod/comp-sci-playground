import javax.swing.*;
import java.awt.*;

public class Game {

	public static GamePanel gamePanel = new GamePanel(500, 500);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		gameLoop();
	}

	private static void gameLoop() {
		int MS_PER_UPDATE = 60;
		double previous = System.currentTimeMillis();
		double lag = 0;
		while (true) {
			double current = System.currentTimeMillis();
			double elapsed = current - previous;
			previous = current;
			lag += elapsed;
			// So the game runs at a constant speed on slower machines
			while (lag >= MS_PER_UPDATE) {
				gamePanel.updateLogic();
				lag -= MS_PER_UPDATE;
			}
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