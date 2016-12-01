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

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		MyPanel myPanel = new MyPanel(width, height);

		JFrame frame = new JFrame("Mandelbrot Set");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		frame.add(myPanel);
		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}