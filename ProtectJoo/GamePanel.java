import java.awt.Image;
import javax.swing.ImageIcon;
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

	private int points = 0;

	private Paddle paddle = new Paddle(50, 0, 100, Color.BLUE);
	private Joo joo = new Joo(100, Color.BLACK, 175, this);
	private Zone zone = new Zone(250, 250, 120, Color.GRAY);

	private Image jooImage = new ImageIcon("MrJooFace.png").getImage();
	private Image zoneImage = new ImageIcon("CarDoor.png").getImage();

	private KeyLis keyLis = new KeyLis(this);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();

		joo.init();
	}
		
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void restart() {

		joo.init();

		points = 0;
	}

	public void updateLogic() {

		if (joo.getSpeed() > 0)
			points += 1;

		if (keyLis.getClockwise())
			paddle.rotate(1);
		if (keyLis.getCounterclockwise())
			paddle.rotate(-1);

		joo.move();

		for (int i = 0; i < 4; i++) {
			joo.checkBounce(i);
		}

		if (joo.checkCollision(paddle)) {
			zone.incrementDiameter();
			joo.init();
			points += 500;
		}

		if (joo.checkCollision(zone))
			joo.stop();
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		// Points
		g.drawString("Empty Stares: " + Integer.toString(points), 20, 35);

		// Paddle
		g.setColor(paddle.getColor());
		g.drawOval
		(
			(int) paddle.getX(), 
			(int) paddle.getY(), 
			(int) paddle.getDiameter(), 
			(int) paddle.getDiameter()
		);

		// Zone
		// g.setColor(zone.getColor());
		g.drawImage
		(
			zoneImage, 
			(int) zone.getX(), 
			(int) zone.getY(), 
			(int) zone.getDiameter(), 
			(int) zone.getDiameter(), 
			this
		);

		// Joo
		g.setColor(Color.GRAY);
		g.fillOval
		(
			(int) joo.getNextX(), 
			(int) joo.getNextY(), 
			(int) joo.getDiameter() / 2, 
			(int) joo.getDiameter() / 2
		);
		g.setColor(Color.WHITE);
		g.fillOval
		(
			(int) (joo.getNextX() + 20 * Math.cos(joo.getNextDirection()) + joo.getDiameter() / 8), 
			(int) (joo.getNextY() + 20 * Math.sin(joo.getNextDirection()) + joo.getDiameter() / 8), 
			(int) joo.getDiameter() / 4, 
			(int) joo.getDiameter() / 4
		);
		g.drawImage
		(
			jooImage, 
			(int) joo.getX(), 
			(int) joo.getY(), 
			(int) joo.getDiameter(), 
			(int) joo.getDiameter(), 
			this
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

		public KeyLis(GamePanel panel) {

			Action press = new AbstractAction() {
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

			Action release = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
						case "a":
							movement[0] = false;
							break;
						case "d":
							movement[1] = false;
							break;
						case " ":
							paddle.jump();
							break;
						case "\n":
							panel.restart();
							break;
						default: break;
					}
				}
			};

			panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released ENTER"), "released");
			panel.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "released");
			panel.getActionMap().put("pressed", press);
			panel.getActionMap().put("released", release);
		}
	}
}