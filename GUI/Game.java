import javax.swing.*;
import java.awt.*;

public class Game {

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
		}
	}

	private static void createAndShowGUI() {
		JFrame f = new JFrame("Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Panel(500, 500));
		f.pack();
		f.setVisible(true);
	}
}