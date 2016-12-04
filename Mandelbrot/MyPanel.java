import java.lang.Exception;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private BufferedImage canvas;
	private int width, height, max = 250, black = 0x000000;
	private int[] colors;
	private double zoom, xShift, yShift;
	private KeyLis keyLis = new KeyLis(this);
	private boolean autoZoom;
	private int colorMode = 0;

	public MyPanel(int width, int height) {

		this.width = width;
		this.height = height;

		this.setFocusable(true);
		this.requestFocus();

		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		colorMode = 0;
		updateColors();

		zoom = 1;
		xShift = 0;
		yShift = 0;

		autoZoom = false;

		renderBrot();
	}

	private void updateColors() {

		colors = new int[max];

		switch (colorMode) {
			case 0:
				for (int i = 0; i < max; i++) {
					colors[i] = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));
				}
				break;
			case 1:
				for (int i = 0; i < max; i++) {
					colors[i] = Color.HSBtoRGB(0, 256f, i / (i + 8f));
				}
				break;
			case 2:
				for (int i = 0; i < max; i++) {
					colors[i] = Color.HSBtoRGB(256f, 0, i / (i + 256f));
				}
				break;
			case 3:
				for (int i = 0; i < max; i++) {
					colors[i] = Color.HSBtoRGB(i / (i + 8f), 256f, 256f);
				}
				break;
			case 4:
				for (int i = 0; i < max; i++) {
					colors[i] = Color.HSBtoRGB(i / (i + 8f), i / (i + 8f), 256f);
				}
				break;
			default: 
				System.out.println("Error: unknown color mode " + colorMode);
				break;
		}
	}

	public void update() {
		if (autoZoom) enhance(0.95);
	}

	private void renderBrot() {

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				double c_re = zoom * (col - width / 2) * 4.0 / width - xShift;
				double c_im = zoom * (row - height / 2) * 4.0 / width - yShift;

				double x = 0, y = 0;
				int iterations = 0;

				while (x * x + y * y < 4 && iterations < max) {

					double x_new = x * x - y * y + c_re;

					y = 2 * x * y + c_im;
					x = x_new;

					iterations++;
				} 

				if (iterations < max) {
					canvas.setRGB(col, row, colors[iterations]);
				} else {
					canvas.setRGB(col, row, black);
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(canvas, 0, 0, this);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	private void convertToPNG() {
		try {
			ImageIO.write(canvas, "png", new File("mandelbrot" + System.currentTimeMillis() + ".png"));
		} catch (Exception e) {}
	}

	private void enhance(double n) {
		zoom *= n;
		renderBrot();
		repaint();
	}

	private void changeColorMode() {
		if (colorMode < 4) {
			colorMode++;
		} else if (colorMode == 4) {
			colorMode = 0;
		}
	}

	private class KeyLis {

		public KeyLis(MyPanel panel) {

			Action cycleColorMode = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					changeColorMode();
					updateColors();
					renderBrot();
					repaint();
				}
			};

			Action toPNG = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					convertToPNG();
				}
			};

			Action increaseColors = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					max += 50;
					updateColors();

					renderBrot();
					repaint();
				}
			};

			Action decreaseColors = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					if (max > 50) max -= 50;
					updateColors();

					renderBrot();
					repaint();
				}
			};

			Action zoomIn = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					enhance(0.5);
				}
			};

			Action autoZoomIn = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					autoZoom = !autoZoom;
				}
			};

			Action move = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {

					double shift = 0.5;

					switch (e.getActionCommand()) {
						case "a":
							xShift += shift * zoom;
							break;
						case "w":
							yShift += shift * zoom;
							break;
						case "d":
							xShift -= shift * zoom;
							break;
						case "s":
							yShift -= shift * zoom;
							break;
						default:
							System.out.println("bad input");
							break;
					}

					renderBrot();

					repaint();
				}
			};

			panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "zoomIn");
			panel.getInputMap().put(KeyStroke.getKeyStroke("C"), "cycleColorMode");
			panel.getInputMap().put(KeyStroke.getKeyStroke("P"), "toPNG");
			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("R"), "increaseColors");
			panel.getInputMap().put(KeyStroke.getKeyStroke("F"), "decreaseColors");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released Z"), "autoZoomIn");

			panel.getActionMap().put("cycleColorMode", cycleColorMode);
			panel.getActionMap().put("autoZoomIn", autoZoomIn);
			panel.getActionMap().put("toPNG", toPNG);
			panel.getActionMap().put("increaseColors", increaseColors);
			panel.getActionMap().put("decreaseColors", decreaseColors);
			panel.getActionMap().put("zoomIn", zoomIn);
			panel.getActionMap().put("move", move);
		}
	}
}