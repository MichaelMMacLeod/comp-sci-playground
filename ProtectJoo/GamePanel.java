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
	private Paddle paddle = new Paddle(75, 0, 100);
	private Joo joo = new Joo();

	private KeyLis keyLis = new KeyLis(this);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
	}

	public void getInput() {

	}
	
	public void updateLogic() {
		if (keyLis.getMoving() && keyLis.getClockwise()) 
			paddle.rotate(1);
		if (keyLis.getMoving() && !keyLis.getClockwise())
			paddle.rotate(-1);
		joo.move();
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.BLUE);
		g.fillOval
		(
			(int) paddle.getPos()[0], 
			(int) paddle.getPos()[1], 
			(int) paddle.getRadius(), 
			(int) paddle.getRadius()
			);

		g.setColor(Color.GRAY);
		g.fillOval
		(
			250 - 120 / 2, 
			250 - 120 / 2,
			120, 
			120
			);

		// Joo
		g.fillOval
		(
			(int) joo.getX(), 
			(int) joo.getY(),
			100, 
			100
			);

	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}

	private class KeyLis {

		private boolean clockwise = false;
		private boolean moving = false;

		public boolean getClockwise() { return clockwise; }
		public boolean getMoving() { return moving; }

		public KeyLis(GamePanel context) {

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

			context.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed");
			context.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
			context.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released");
			context.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released");
			context.getActionMap().put("pressed", rotate);
			context.getActionMap().put("released", stop);
		}
	}
}