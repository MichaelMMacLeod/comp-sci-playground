import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private Listener listener = new Listener();
	private Paddle paddle = new Paddle(100, 0, 100);

	public GamePanel(int width, int height) {

		this.width = width;
		this.height = height;
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(listener);
	}

	public void getInput() {

	}
	
	public void updateLogic() {

	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.fillOval
		(
			(int) paddle.getPos()[0], 
			(int) paddle.getPos()[1], 
			(int) paddle.getRadius(), 
			(int) paddle.getRadius()
		);

	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}
	
	private class Listener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			// switch (e.getKeyCode()) {
			// 	case KeyEvent.VK_LEFT:
			// 	case KeyEvent.VK_A:
			// 		paddle.rotate(-1);
			// 		break;
			// 	case KeyEvent.VK_RIGHT:
			// 	case KeyEvent.VK_D:
			// 		paddle.rotate(1);
			// 		break;
			// 	default: break;
			// }
		}
	}
}