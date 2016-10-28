import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;
	private KeyLis listener = new KeyLis();

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

	}

	public Dimension getPreferredSize() {

		return new Dimension(width, height);
	}
	
	private class KeyLis extends KeyAdapter {


	}
}