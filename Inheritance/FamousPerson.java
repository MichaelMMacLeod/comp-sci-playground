public class FamousPerson {
	
	protected String name, lastName, saying;
	
	public FamousPerson(String saying, String name, String lastName) {
		this.saying = saying;
		this.name = name;
		this.lastName = lastName;
	}

	public FamousPerson() {}

	protected void speak() {
		System.out.println(saying + " -- " + name + " " + lastName);
	}
}
