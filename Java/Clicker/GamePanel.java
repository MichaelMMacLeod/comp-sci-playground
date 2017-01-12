import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class GamePanel extends JPanel {
	private int width, height;

	private InputManager input = new InputManager(this);

	private Image joo = new ImageIcon("MrJooFace.png").getImage();

	private double clicks;
	private double clicksPerUpdate;

	private double jooScale;

	private double carDoorPrice;
	private double carDoorClicks;
	private int carDoors;

	public GamePanel(double width, double height) {
		this.width = (int) width;
		this.height = (int) height;

		this.setFocusable(true);
		this.requestFocus();

		input.addKey("c");

		restart();
	}

	private void restart() {
		clicks = 0;
		clicksPerUpdate = 0;

		jooScale = 1;

		carDoorPrice = 50;
		carDoorClicks = 0.01;
		carDoors = 0;
	}

	public void updateLogic() {
		if (input.pressed("mouse")
			&& joo.getWidth(this) / 2 > Math.sqrt(Math.pow(input.mx() - width / 2, 2) + Math.pow(input.my() - height / 2, 2))) {
			clicks++;
			jooScale = 0.95;
		}

		if (jooScale < 1)
			jooScale *= 1.01;

		if (input.pressed("c") && clicks > (int) carDoorPrice) {
			carDoors++;
			clicks -= carDoorPrice;
			carDoorPrice *= 1.15;
			clicksPerUpdate += carDoorClicks;
		}

		clicks += clicksPerUpdate;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(
			joo, 
			width / 2 - (int) (joo.getWidth(this) / 2 * jooScale), 
			height / 2 - (int) (joo.getHeight(this) / 2 * jooScale), 
			(int) (joo.getWidth(this) * jooScale),
			(int) (joo.getHeight(this) * jooScale),
			this);

		g.drawString("Empty Stares: " + (int) clicks, 10, 20);
		g.drawString("Per Second: " + (int) (clicksPerUpdate * 100), 10, 40);
		g.drawString("Car Doors (press c to purchase for " + (int) carDoorPrice + "): " + carDoors, 10, 60);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}