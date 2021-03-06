import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Game {

	public static GamePanel gamePanel = new GamePanel(500, 500);
	private static final int MS_PER_UPDATE = 10;

	public static void main(String[] args) {

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
		frame.setUndecorated(true);

		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		gamePanel.setBackground(Color.WHITE);

		frame.add(gamePanel);
		frame.pack();

		// Center the window
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}