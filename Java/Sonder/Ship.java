import java.awt.geom.Point2D;

import java.util.Arrays;

public class Ship extends Moveable {

	private double acceleration;
	private double rotationSpeed;

	private Drawn healthBar;
	private double maxHealth;
	private double health;

	private String[] keys = 
	{
		"rotate counter clockwise key",
		"rotate clockwise key",
		"thrust key",
		"fire key"
	};

	public String[] getKeys() {
		return Arrays.copyOf(keys, keys.length);
	}

	public Ship(
		Drawn shape, 
		double acceleration, 
		double rotationSpeed, 
		double deceleration,
		String[] keys) {

		super(shape, deceleration);

		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.keys = keys;

		maxHealth = 50;
		health = 50;

		this.healthBar = new Drawn(
			new double[][]
			{
				{-maxHealth, maxHealth, maxHealth, -maxHealth},
				{-4, -4, 4, 4}
			},
			new Point2D.Double(
				shape().getPoint().x, 
				shape().getPoint().y),
			1,
			0,
			shape().getColor());
	}

	@Override
	public void update() {
		shape.translate(vector[0], vector[1]);

		vector[0] *= deceleration;
		vector[1] *= deceleration;

		healthBar.setLocation(shape().getPoint().x, shape().getPoint().y + 50);
		healthBar.setSize(health / maxHealth);
	}

	public boolean isAlive() {
		return health > 0;
	}

	public Drawn getHealthBar() {
		return healthBar;
	}

	public void hit(double damage) {
		health = health - damage > 0 ? health - damage : 0;
	}

	public void thrust() {
		vector[0] += acceleration * Math.cos(shape.getRotation());
		vector[1] += acceleration * Math.sin(shape.getRotation());
	}

	public void rotate(boolean clockwise) {
		shape.rotate((clockwise ? 1 : -1) * Math.PI / rotationSpeed);
	}

	public boolean hitBy(Projectile p) {
		Drawn pShape = p.shape();
		return shape.contains(pShape.getPoint());
	}
}