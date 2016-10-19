import javax.swing.*;
import java.awt.*;

public class Game {

	public static Panel panel = new Panel(500, 500);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		gameLoop();
	}

	private static void gameLoop() {
		while (true) {
			// update logic
			// render
			panel.repaint();
			System.out.println("looped!");
			try {
				Thread.sleep(60);
			} catch (Exception e) {}
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}