import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;

	private Paddle paddle = new Paddle(75, 0, 100, Color.BLUE);
	private Joo joo = new Joo(50, Color.BLACK);
	private Zone zone = new Zone(250, 250, 120, Color.GRAY);

	private KeyLis keyLis = new KeyLis(this);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
	}
		
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void updateLogic() {

		if (keyLis.getClockwise())
			paddle.rotate(1);
		if (keyLis.getCounterclockwise())
			paddle.rotate(-1);

		joo.move();

		for (int i = 0; i < 4; i++) {
			joo.checkBounce(this, i);
		}

		if (joo.checkCollision(paddle))
			joo.initPos();

		if (joo.checkCollision(zone))
			joo.stop();
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		// Paddle
		g.setColor(paddle.getColor());
		g.fillOval
		(
			(int) paddle.getX(), 
			(int) paddle.getY(), 
			(int) paddle.getDiameter(), 
			(int) paddle.getDiameter()
		);

		// Zone
		g.setColor(zone.getColor());
		g.fillOval
		(
			(int) zone.getX(), 
			(int) zone.getY(),
			(int) zone.getDiameter(), 
			(int) zone.getDiameter()
		);

		// Joo
		g.setColor(joo.getColor());
		g.fillOval
		(
			(int) joo.getX(), 
			(int) joo.getY(),
			(int) joo.getDiameter(), 
			(int) joo.getDiameter()
		);

	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}

	private class KeyLis {

		private boolean[] movement = 
		{
			false, // directions[0] is counterclockwise movement
			false  // directions[1] is clockwise movement
		};

		public boolean getClockwise() {
			return movement[1];
		}

		public boolean getCounterclockwise() {
			return movement[0];
		}

		public KeyLis(GamePanel context) {

			Action rotate = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "a":
							movement[0] = true;
							break;
						case "d":
							movement[1] = true;
							break;
						default: break;
					}
				}
			};

			Action stop = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "a":
							movement[0] = false;
							break;
						case "d":
							movement[1] = false;
							break;
						default: break;
					}
				}
			};

			context.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			context.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			context.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released");
			context.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released");
			context.getActionMap().put("pressed", rotate);
			context.getActionMap().put("released", stop);
		}
	}
}