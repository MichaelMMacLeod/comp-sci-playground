public class Tester {

	public static void main(String[] args) {

		FamousPerson[] people = 
		{
			new Yoda("The mind of the child is truly wonderful"),
			new AnnoyedStudent("To be or not to be? That is the question.", "Michael", "MacLeod"),
			new Programmer("Oct 31 = Dec 25.", "Holiday", "Programmer"),
			new Joo("Does not inherit does not mean does not inherit.", "Mr.", "Joo"),
			new Physicist("Creativity is intelligence having fun.", "Albert", "Einstein")
		};
		
		for (FamousPerson i : people) i.speak();
	}
}
