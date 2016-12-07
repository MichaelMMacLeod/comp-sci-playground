import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Game {

	public static GamePanel gamePanel = new GamePanel(629, 629);
	private static final int[] MS_PER_UPDATE = {150, 125, 100, 75, 50};
	private static int selectedMS = 2; 

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		gameLoop();
	}

	public static int getSpeed() {

		return selectedMS + 1;
	}

	public static void toggleSpeed() {

		selectedMS = selectedMS == MS_PER_UPDATE.length - 1 ? 0 : selectedMS + 1;
	}

	private static void gameLoop() {

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
			while (lag >= MS_PER_UPDATE[selectedMS]) {
				gamePanel.updateLogic();
				lag -= MS_PER_UPDATE[selectedMS];
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
		
		frame.add(gamePanel);
		frame.pack();

		// Center the window
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}