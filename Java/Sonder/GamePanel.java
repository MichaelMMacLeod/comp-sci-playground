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

	private ArrayList<Ship> players;

	private ArrayList<Moveable> updates;

	final CommandFactory cf;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;

		restart();

		// Initialize command factory and controls

		input = new InputManager(this);

		cf = CommandFactory.init();

		for (Ship player : players) {
			String[] keys = player.getKeys();

			for (String key : keys)
				input.addKey(key);

			cf.addCommand(keys[0], () -> player.rotate(false));
			cf.addCommand(keys[1], () -> player.rotate(true));
			cf.addCommand(keys[2], () -> player.thrust());
			cf.addCommand(keys[3], () -> {
				Drawn d = new Drawn(
					Drawn.SQUARE,
					player.shape().getPoint(),
					5,
					player.shape().getRotation(),
					player.shape().getColor());
				Projectile p = new Projectile(
					d, 
					player.vector(), 
					10, 
					player, 
					0.99);
				camera.add(p.shape(), false);
				updates.add(p);
			});
		}
	}

	private void restart() {
		updates = new ArrayList<Moveable>();

		camera = new Camera();

		// Initialize players

		players = new ArrayList<Ship>();

		players.add(
			new Ship(
				new Drawn(
					Drawn.TRIANGLE,
					new Point2D.Double(-60, 0), 
					30, 
					0, 
					Color.BLUE), 
				0.05, 
				120,
				0.99,
				new String[] 
				{
					"a",
					"d",
					"s",
					"w"
				}));

		players.add(
			new Ship(
				new Drawn(
					Drawn.TRIANGLE,
					new Point2D.Double(60, 0),
					30, 
					Math.PI, 
					Color.RED), 
				0.05, 
				120,
				0.99,
				new String[]
				{
					"j",
					"l",
					"k",
					"i"
				}));

		for (Ship player : players) {
			camera.add(player.shape(), true);
			updates.add(player);
		}

		camera.add(
			new Drawn(
				Drawn.SQUARE,
				new Point2D.Double(),
				120, 
				0, 
				Color.GREEN),
			false);
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

				for (Ship player : players) {
					if (player.hitBy(p) && p.getParent() != player) {
						camera.add(
							new Drawn(
								Drawn.TRIANGLE,
								player.shape().getPoint(),
								30,
								player.shape().getRotation(),
								Color.BLACK),
							false);

						updates.remove(i);

						i--;
					}
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