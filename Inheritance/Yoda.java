public class Yoda extends FamousPerson {

	public Yoda(String saying) {

		super(saying);

		name = "Yoda";
		lastName = "";
	}

	@Override
	public void speak() {
		
		String[] yodaSaying = saying.split(" ");
		String[] beforeIs = new String[yodaSaying.length];

		int i;
		for (i = 0; i < yodaSaying.length; i++) {

			beforeIs[i] = yodaSaying[i];

			if (yodaSaying[i].equals("is")) {
				break;
			}
		}
		i++;

		String[] afterIs = new String[yodaSaying.length];

		int j;
		for (j = 0; j < afterIs.length; j++) {
			afterIs[j] = yodaSaying[i + j];
		}

		afterIs[j] = ",";

		for (String word : afterIs) {
			System.out.print(word + " ");
		}

		for (String word : beforeIs) {
			if (word != null) {
				System.out.print(word + " ");
			}
		}

		System.out.print("-- " + name);
	}
}
