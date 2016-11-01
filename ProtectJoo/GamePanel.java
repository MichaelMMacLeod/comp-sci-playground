import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private Paddle paddle = new Paddle(75, 0, 100);
	private Joo joo = new Joo();

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
	}

	public void getInput() {

	}
	
	public void updateLogic() {
		if (Game.moving && Game.clockwise) 
			paddle.rotate(1);
		if (Game.moving && !Game.clockwise)
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
}