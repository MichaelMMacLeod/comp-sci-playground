import java.awt.geom.Point2D;
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

	final CommandFactory cf;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;

		restart();

		input = new InputManager(this);

		input.addKey("w");
		input.addKey("a");
		input.addKey("d");
		input.addKey("s");
		input.addKey("i");
		input.addKey("j");
		input.addKey("l");
		input.addKey("k");

		// Initiate command factory and controls

		cf = CommandFactory.init();

		cf.addCommand("a", () -> player1.rotate(false));
		cf.addCommand("d", () -> player1.rotate(true));
		cf.addCommand("s", () -> player1.thrust());
		cf.addCommand("w", () -> {
			Drawn d = new Drawn(
				Drawn.SQUARE,
				player1.shape().getPoint(),
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
		});

		cf.addCommand("j", () -> player2.rotate(false));
		cf.addCommand("l", () -> player2.rotate(true));
		cf.addCommand("k", () -> player2.thrust());
		cf.addCommand("i", () -> {
			Drawn d = new Drawn(
				Drawn.SQUARE,
				player2.shape().getPoint(),
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
		});

		cf.listCommands();
	}

	private void restart() {
		player1 = new Ship(
			new Drawn(
				Drawn.TRIANGLE,
				new Point2D.Double(-60, 0), 
				30, 
				0, 
				Color.BLUE), 
			0.05, 
			120,
			0.99);
		player2 = new Ship(
			new Drawn(
				Drawn.TRIANGLE,
				new Point2D.Double(60, 0),
				30, 
				Math.PI, 
				Color.RED), 
			0.05, 
			120,
			0.99);

		updates = new ArrayList<Moveable>();

		camera = new Camera();

		camera.add(player1.shape(), true);
		camera.add(player2.shape(), true);

		camera.add(
			new Drawn(
				Drawn.SQUARE,
				new Point2D.Double(),
				120, 
				0, 
				Color.GREEN),
			false);

		updates.add(player1);
		updates.add(player2);
	}

	public void update() {
		if (input.held("s"))
			cf.executeCommand("s");
		if (input.held("a"))
			cf.executeCommand("a");
		if (input.held("d"))
			cf.executeCommand("d");
		if (input.pressed("w"))
			cf.executeCommand("w");

		if (input.held("k"))
			cf.executeCommand("k");
		if (input.held("j"))
			cf.executeCommand("j");
		if (input.held("l"))
			cf.executeCommand("l");
		if (input.pressed("i"))
			cf.executeCommand("i");

		for (int i = 0; i < updates.size(); i++) {
			Moveable m = updates.get(i);

			if (m instanceof Projectile) {
				Projectile p = (Projectile) m;

				if (player1.hitBy(p) && p.getParent() != player1) {
					camera.add(
						new Drawn(
							Drawn.TRIANGLE,
							player1.shape().getPoint(),
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
							Drawn.TRIANGLE,
							player2.shape().getPoint(),
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