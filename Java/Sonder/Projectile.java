public class Projectile extends Moveable {

	public Projectile(Drawn shape, double velocity) {
		super(shape);

		vector = new double[] 
		{
			velocity * Math.cos(shape.getRotation()), 
			velocity * Math.sin(shape.getRotation())
		};
	}
}