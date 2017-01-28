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
					player.shape().getColor(),
					false);
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

		cf.listCommands();
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
					Color.BLUE,
					false), 
				0.05, 
				120,
				0.99,
				new String[] 
				{
					"a",
					"d",
					"s",
					"w"
				},
				0.10));

		players.add(
			new Ship(
				new Drawn(
					Drawn.TRIANGLE,
					new Point2D.Double(60, 0),
					30, 
					Math.PI, 
					Color.RED,
					false), 
				0.05, 
				120,
				0.99,
				new String[]
				{
					"j",
					"l",
					"k",
					"i"
				},
				0.10));

		players.add(
			new Ship(
				new Drawn(
					Drawn.TRIANGLE,
					new Point2D.Double(0, 60),
					30, 
					-Math.PI / 2, 
					Color.BLACK,
		 		false), 
				0.05, 
				120,
				0.99,
				new String[]
				{
					"f",
					"h",
					"g",
					"t"
				},
		 	0.10));

		for (Ship player : players) {

			Drawn[] bars = player.getHealthBar();

			for (Drawn bar : bars)
				camera.add(bar, false);

			camera.add(player.getShield(), false);

			camera.add(player.shape(), true);
			updates.add(player);
		}

		camera.add(
			new Drawn(
				Drawn.SQUARE,
				new Point2D.Double(),
				120, 
				0, 
				Color.GREEN,
				false),
			false);
	}

	public void update() {
		for (int i = 0; i < players.size(); i++) {
			Ship player = players.get(i);

			if (player.isAlive()) {
				String[] keys = player.getKeys();

				if (input.held(keys[0]))
					cf.executeCommand(keys[0]);
				if (input.held(keys[1]))
					cf.executeCommand(keys[1]);
				if (input.held(keys[2]))
					cf.executeCommand(keys[2]);
				if (input.pressed(keys[3]))
					cf.executeCommand(keys[3]);
			} else {
				camera.removeFocus(player.shape());
				players.remove(player);

				Drawn[] bars = player.getHealthBar();
				for (Drawn bar : bars)
					camera.removeNonFocus(bar);

				camera.removeNonFocus(player.getShield());

				i--;
			}
		}

		for (int i = 0; i < updates.size(); i++) {
			Moveable m = updates.get(i);

			if (m instanceof Projectile) {
				Projectile p = (Projectile) m;

				for (Ship player : players) {
					Ship parent = p.getParent();

					boolean remove = false;

					if (player.shieldHitBy(p) && parent != player) {
						player.hitShield(10);
						remove = true;
					}

					if (player.hitBy(p) && parent != player) {
						player.hit(5);
						parent.heal(
							(parent.getMaxHealth() - parent.getHealth()) 
							* parent.getPercentHealthOnHit());
						remove = true;
					}

					if (remove) {
						camera.removeNonFocus(p.shape());
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