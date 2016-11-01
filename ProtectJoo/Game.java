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
	public static boolean clockwise = false;
	public static boolean moving = false;

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
		frame.setUndecorated(true);

		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		Action rotate = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
					case "a":
						moving = true;
						clockwise = false;
						break;
					case "d":
						moving = true;
						clockwise = true;
						break;
					default: break;
				}
			}
		};

		Action stop = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
					case "a":
						moving = false;
						break;
					case "d":
						moving = false;
						break;
					default: break;
				}
			}
		};

		gamePanel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
		gamePanel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
		gamePanel.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released");
		gamePanel.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released");
		gamePanel.getActionMap().put("pressed", rotate);
		gamePanel.getActionMap().put("released", stop);

		frame.add(gamePanel);
		frame.pack();

		// Center the window
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
}