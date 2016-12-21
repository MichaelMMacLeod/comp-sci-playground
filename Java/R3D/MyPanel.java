import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	private double[][] points =
	{
		{5, 5, 5},
		{10, 5, 5},
		{5, 10, 5},
		{10, 10, 5},
		{5, 5, 10},
		{10, 5, 10},
		{5, 10, 10},
		{10, 10, 10}
	};
	private double[] c = {0, 0, 0};
	private double[] t = {0, 0, 0};
	private double[] e = {1, 1, 1};

	private double[][] pointProjection =
	{
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0},
		{0, 0}
	};

	public MyPanel() {
		this.setFocusable(true);
		this.requestFocus();

		input.addKey("a");
		input.addKey("w");
		input.addKey("d");
		input.addKey("s");

		input.addKey("f");
		input.addKey("t");
		input.addKey("g");
		input.addKey("h");
	}

	public void update() {
		if (input.pressed("a")) {
			t[2] += 0.0001;
		}
		if (input.pressed("w")) {
			t[0] += 0.0001;
		}
		if (input.pressed("d")) {
			t[2] -= 0.0001;
		}
		if (input.pressed("s")) {
			t[0] -= 0.0001;
		}

		if (input.pressed("f")) {
			c[0] += 0.0001;
		}
		if (input.pressed("t")) {
			c[1] += 0.0001;
		}
		if (input.pressed("h")) {
			c[0] -= 0.0001;
		}
		if (input.pressed("g")) {
			c[1] -= 0.0001;
		}

		for (int i = 0; i < pointProjection.length; i++) {
			pointProjection[i] = R3D.project(points[i], c, t, e);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int[] p0 = 
		{
			(int) (100 * pointProjection[0][0] + getWidth() / 2),
			(int) (100 * pointProjection[0][1] + getWidth() / 2)
		};
		int[] p1 = 
		{
			(int) (100 * pointProjection[1][0] + getWidth() / 2),
			(int) (100 * pointProjection[1][1] + getWidth() / 2)
		};
		int[] p2 =
		{
			(int) (100 * pointProjection[2][0] + getWidth() / 2),
			(int) (100 * pointProjection[2][1] + getWidth() / 2)
		};
		int[] p3 =
		{
			(int) (100 * pointProjection[3][0] + getWidth() / 2),
			(int) (100 * pointProjection[3][1] + getWidth() / 2)
		};
		int[] p4 =
		{
			(int) (100 * pointProjection[4][0] + getWidth() / 2),
			(int) (100 * pointProjection[4][1] + getWidth() / 2)
		};
		int[] p5 =
		{
			(int) (100 * pointProjection[5][0] + getWidth() / 2),
			(int) (100 * pointProjection[5][1] + getWidth() / 2)
		};
		int[] p6 =
		{
			(int) (100 * pointProjection[6][0] + getWidth() / 2),
			(int) (100 * pointProjection[6][1] + getWidth() / 2)
		};
		int[] p7 =
		{
			(int) (100 * pointProjection[7][0] + getWidth() / 2),
			(int) (100 * pointProjection[7][1] + getWidth() / 2)
		};

		g.setColor(Color.BLACK);
		for (int i = 0; i < pointProjection.length; i++) {
			for (int j = 0; j < pointProjection[i].length; j++) {
				g.drawRect((int) (100 * pointProjection[i][0]) + getWidth() / 2 - 3, (int) (100 * pointProjection[i][1]) + getHeight() / 2 - 3, 6, 6);

				g.drawLine(p0[0], p0[1], p1[0], p1[1]);
				g.drawLine(p0[0], p0[1], p2[0], p2[1]);
				g.drawLine(p2[0], p2[1], p3[0], p3[1]);
				g.drawLine(p1[0], p1[1], p3[0], p3[1]);

				g.drawLine(p4[0], p4[1], p5[0], p5[1]);
				g.drawLine(p4[0], p4[1], p6[0], p6[1]);
				g.drawLine(p6[0], p6[1], p7[0], p7[1]);
				g.drawLine(p5[0], p5[1], p7[0], p7[1]);

				g.drawLine(p0[0], p0[1], p4[0], p4[1]);
				g.drawLine(p1[0], p1[1], p5[0], p5[1]);
				g.drawLine(p2[0], p2[1], p6[0], p6[1]);
				g.drawLine(p3[0], p3[1], p7[0], p7[1]);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}