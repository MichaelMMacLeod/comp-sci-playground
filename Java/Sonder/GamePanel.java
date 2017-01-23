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
			120,
			0.99);
		player2 = new Ship(
			new Drawn(
				60, 
				0, 
				Drawn.TRIANGLE, 
				30, Math.PI, 
				Color.RED), 
			0.05, 
			120,
			0.99);

		updates = new ArrayList<Moveable>();

		camera = new Camera();

		camera.add(player1.shape(), true);
		camera.add(player2.shape(), true);

		camera.add(
			new Drawn(0, 
				0, 
				Drawn.SQUARE, 
				120, 
				0, 
				Color.GREEN),
			false);

		updates.add(player1);
		updates.add(player2);
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
				5,
				player1.shape().getRotation(),
				Color.BLUE);
			Projectile p = new Projectile(
				d, 
				player1.vector(), 
				10, 
				player1, 
				0.99);
			camera.add(p.shape(), false);
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
				5,
				player2.shape().getRotation(),
				Color.RED);
			Projectile p = new Projectile(
				d, 
				player2.vector(), 
				10, 
				player2,
				0.99);
			camera.add(p.shape(), false);
			updates.add(p);
		}

		for (int i = 0; i < updates.size(); i++) {
			Moveable m = updates.get(i);

			if (m instanceof Projectile) {
				Projectile p = (Projectile) m;

				if (player1.hitBy(p) && p.getParent() != player1) {
					camera.add(
						new Drawn(
							(int) player1.shape().getX(),
							(int) player1.shape().getY(),
							Drawn.TRIANGLE,
							30,
							player1.shape().getRotation(),
							Color.BLACK),
						false);

					updates.remove(i);

					i--;
					continue;
				}

				if (player2.hitBy(p) && p.getParent() != player2) {
					camera.add(
						new Drawn(
							(int) player2.shape().getX(),
							(int) player2.shape().getY(),
							Drawn.TRIANGLE,
							30,
							player2.shape().getRotation(),
							Color.BLACK),
						false);

					updates.remove(i);

					i--;
					continue;
				}
			}

			m.update();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		camera.draw(g, getWidth(), getHeight(), 32);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}