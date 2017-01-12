import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Clicker {

	private static GamePanel gamePanel;

	private static final int MS_PER_UPDATE = 10;

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");

		gamePanel = new GamePanel(500, 500);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		gameLoop();
	}

	private static void gameLoop() {
		double previous = System.currentTimeMillis();
		double lag = 0;

		while (true) {
			double current = System.currentTimeMillis();
			double elapsed = current - previous;
			previous = current;
			lag += elapsed;

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
		frame.setUndecorated(true);

		gamePanel.setBackground(Color.WHITE);

		frame.add(gamePanel);
		frame.pack();

		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}