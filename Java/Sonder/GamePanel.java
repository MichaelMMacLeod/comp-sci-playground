import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int width, height;

	private InputManager input;

	private Camera camera;

	private Ship player1, player2;

	private ArrayList<Drawn> focuses;
	private ArrayList<Drawn> objects;
	private ArrayList<Moveable> updates;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;

		restart();

		input = new InputManager(this);

        // Player 1
		input.addKey("w");
		input.addKey("a");
		input.addKey("d");
		input.addKey("s");

        // Player 2
		input.addKey("i");
		input.addKey("j");
		input.addKey("l");
		input.addKey("k");
	}

	private void restart() {
		player1 = new Ship(
			new Drawn(
				-60, 
				0, 
				Drawn.TRIANGLE, 
				30, 
				0, 
				Color.BLUE), 
			0.05, 
			120);
		player2 = new Ship(
			new Drawn(
				60, 
				0, 
				Drawn.TRIANGLE, 
				30, Math.PI, 
				Color.RED), 
			0.05, 120);

		focuses = new ArrayList<Drawn>();
		objects = new ArrayList<Drawn>();
		updates = new ArrayList<Moveable>();

		focuses.add(player1.shape());
		focuses.add(player2.shape());

		objects.add(player1.shape());
		objects.add(player2.shape());
		objects.add(
			new Drawn(0, 
				0, 
				Drawn.SQUARE, 
				120, 
				0, 
				Color.GREEN));

		updates.add(player1);
		updates.add(player2);

		camera = new Camera();
	}

	public void update() {
		if (input.held("s")) 
			player1.thrust();
		if (input.held("a"))
			player1.rotate(false);
		if (input.held("d"))
			player1.rotate(true);
		if (input.pressed("w")) {
			Drawn d = new Drawn(
				(int) player1.shape().getX(),
				(int) player1.shape().getY(),
				Drawn.SQUARE,
				10,
				player1.shape().getRotation(),
				Color.BLUE);
			Projectile p = new Projectile(d, player1.vector(), 10);
			objects.add(p.shape());
			updates.add(p);
		}   

		if (input.held("k"))
			player2.thrust();
		if (input.held("j"))
			player2.rotate(false);
		if (input.held("l"))
			player2.rotate(true);
		if (input.pressed("i")) {
			Drawn d = new Drawn(
				(int) player2.shape().getX(),
				(int) player2.shape().getY(),
				Drawn.SQUARE,
				10,
				player2.shape().getRotation(),
				Color.RED);
			Projectile p = new Projectile(d, player2.vector(), 10);
			objects.add(p.shape());
			updates.add(p);
		}

		for (Moveable m : updates) {
			m.update();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		camera.draw(g, getWidth(), getHeight(), focuses, objects, 32);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}