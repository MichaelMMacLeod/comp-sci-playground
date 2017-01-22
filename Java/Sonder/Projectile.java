public class Projectile extends Moveable {

	private Ship parent;

	public Projectile(Drawn shape, 
		double[] velocityOfParent, 
		double velocity,
		Ship parent) {

		super(shape);

		this.parent = parent;

		vector = new double[] 
		{
			velocityOfParent[0] + velocity * Math.cos(shape.getRotation()), 
			velocityOfParent[1] + velocity * Math.sin(shape.getRotation())
		};
	}

	public Ship getParent() {
		return parent;
	}
}