public class Projectile extends Moveable {

	public Projectile(Drawn shape, 
		double[] velocityOfParent, 
		double velocity) {

		super(shape);

		vector = new double[] 
		{
			velocityOfParent[0] + velocity * Math.cos(shape.getRotation()), 
			velocityOfParent[1] + velocity * Math.sin(shape.getRotation())
		};
	}
}