import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private int width, height, max = 1000, black = 0x000000;
	private Color[] colors;
	private double zoom, xShift, yShift;
	private Color[][] map;
	private KeyLis keyLis = new KeyLis(this);

	public MyPanel(int width, int height) {

		this.width = width;
		this.height = height;

		this.setFocusable(true);
		this.requestFocus();

		colors = new Color[max];
		for (int i = 1; i <= max; i++) {

			int rgb = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));

			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8) & 0xFF;
			int b = rgb & 0xFF;

			colors[i - 1] = new Color(r, g, b);
		}

		zoom = 1;
		xShift = 0;
		yShift = 0;

		map = new Color[width][height];

		renderBrot();
	}

	private void renderBrot() {

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				double c_re = zoom * (col - width / 2) * 4.0 / width - xShift;
				double c_im = zoom * (row - height / 2) * 4.0 / width - yShift;

				double x = 0, y = 0;
				int iterations = 0;

				while (x * x + y * y < 3 && iterations < max) {

					double x_new = x * x - y * y + c_re;

					y = 2 * x * y + c_im;
					x = x_new;

					iterations++;
				} 

				if (iterations < max) {
					map[col][row] = colors[iterations];
				} else {
					map[col][row] = Color.BLACK;
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				g.setColor(map[x][y]);
				g.drawLine(x, y, x, y);
			}
		}

		g.setColor(Color.RED);
		g.drawLine(width / 2, height / 2, width / 2, height / 2);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	private class KeyLis {

		public KeyLis(MyPanel panel) {

			Action zoomIn = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					zoom *= 0.5;
					renderBrot();
					repaint();
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

			panel.getInputMap().put(KeyStroke.getKeyStroke("Q"), "zoomIn");
			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "move");
			panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "move");

			panel.getActionMap().put("zoomIn", zoomIn);
			panel.getActionMap().put("move", move);
		}
	}
}