public class AnnoyedStudent extends FamousPerson {

	public AnnoyedStudent(String saying, String name, String lastName) {
		super(saying, name, lastName);
	}

	/**
	 * Shadows saying, and says it
	 */
	@Override
	public void speak() {
		String saying = "CAR DOOR";
		System.out.println(saying + " -- " + name + " " + lastName);
	}
}