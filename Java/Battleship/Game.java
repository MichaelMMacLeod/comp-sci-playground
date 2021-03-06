import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Color;

public class Game {

	public static GamePanel gamePanel = new GamePanel(15, 50, 1);
	private static final int MS_PER_UPDATE = 10;
	private static boolean on = true;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		System.out.println("Controls: w/a/s/d to move, enter to fire, space to restart, and escape to quit");
		
		gameLoop();
	}

	private static void gameLoop() {

		double previous = System.currentTimeMillis();
		double lag = 0;

		while (on) {
			
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