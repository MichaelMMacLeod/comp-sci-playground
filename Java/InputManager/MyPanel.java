import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private InputManager input = new InputManager(this);

	public MyPanel() {
		input.addKey("a");
		input.addKey("m");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (input.held("mouse")) {
			g.drawString(Integer.toString((int) input.mx())
				+ " "
				+ Integer.toString((int) input.my()), 
				(int) input.mx(), 
				(int) input.my());
		}

		if (input.pressed("mouse")) {
			System.out.println("Mouse click!");
		}
	}

	public void update() {
		if (input.held("a")) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.WHITE);
		}

		if (input.pressed("a")) {
			System.out.println("Pressed a");
		}

		if (input.pressed("m")) {
			System.out.println(input.mx() + " " + input.my());
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}