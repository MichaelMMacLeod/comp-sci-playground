import javax.swing.BorderFactory;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {

	private static GamePanel gamePanel;
	private static final int MS_PER_UPDATE = 10;
	private static boolean on = true;

	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		gamePanel = new GamePanel(width, height);

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
		gamePanel.setBackground(Color.BLACK);

		frame.add(gamePanel);
		frame.pack();

		// Center the window
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}