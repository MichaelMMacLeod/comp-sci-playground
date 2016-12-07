public class Yoda extends FamousPerson {

	public Yoda(String saying) {
		this.saying = saying;
		name = "Yoda";
		lastName = "";
	}

	/** 
	 * If the saying contains the word "is", swap it around into Yoda-speak.
	 * "word1 word2 is word3 word4" --> "word3 word4, is word1 word2."
	 */
	@Override
	public void speak() {
		
		String[] original = saying.split(" ");
		int is = 0;

		for (int i = 0; i < original.length; i++) {
			original[i] = original[i].toLowerCase();
		}

		int l;
		for (l = 0; l < original.length; l++) {
			if (original[l].equals("is")) {
				is = l + 1;
				break;
			}
		}

		if (l == original.length) {
			System.out.println(saying + " -- Yoda");
			return;
		}

		String[] beforeIs = new String[is];
		String[] afterIs = new String[original.length - is];

		for (int i = 0; i < beforeIs.length; i++) {
			beforeIs[i] = original[i];
		}
		for (int i = 0; i < afterIs.length; i++) {
			afterIs[i] = original[i + is];
		}

		afterIs[0] = afterIs[0].substring(0, 1).toUpperCase() + afterIs[0].substring(1);

		for (int i = 0; i < afterIs.length - 1; i++) {
			System.out.print(afterIs[i] + " ");
		}
		System.out.print(afterIs[afterIs.length - 1] + ", ");
		for (int i = 0; i < beforeIs.length - 1; i++) {
			System.out.print(beforeIs[i] + " ");
		}
		System.out.println(beforeIs[beforeIs.length - 1] + ". -- " + name + " " + lastName);
	}
}
