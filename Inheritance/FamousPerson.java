public class FamousPerson {
	
	protected String name, lastName, saying;
	
	public FamousPerson(String saying, String name, String lastName) {
		this.saying = saying;
		this.name = name;
		this.lastName = lastName;
	}

	public FamousPerson(String saying) {
		this.saying = saying;
	}

	protected void speak() {
		System.out.println(saying + " -- " + name + " " + lastName);
	}
}
