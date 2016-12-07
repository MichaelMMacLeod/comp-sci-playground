import javax.swing.JFrame;
import java.awt.Toolkit;
import java.lang.Runnable;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

// Original code from joni
// https://github.com/joni/fractals/blob/master/mandelbrot/MandelbrotBW.java

public class Mandelbrot {

	private static MyPanel myPanel = new MyPanel
	(
		(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
		(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
	);

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		loop();
	}

	private static void loop() {
		while (true) {

			try {
				Thread.sleep(5);
			} catch (Exception e) {}
			
			myPanel.update();
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Mandelbrot Set");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		frame.add(myPanel);
		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}