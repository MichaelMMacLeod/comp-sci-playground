import java.util.ArrayList;

public class Fraction {

	private int numerator;
	private int denominator;

	public Fraction(int numerator, int denominator) {

		this.numerator = numerator;
		this.denominator = denominator;
	}

	public void printSmall() {

		System.out.println(numerator + " / " + denominator);
	}

	public void printLarge() {

		System.out.println(numerator);

		int length;
		if (Integer.toString(numerator).length() > Integer.toString(denominator).toString().length())
			length = Integer.toString(numerator).toString().length();
		else
			length = Integer.toString(denominator).toString().length();
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}

		System.out.println("\n" + denominator);
	}

	public boolean isReducible() {

		ArrayList<Integer> numeratorFactors = new ArrayList<Integer>(); 
		ArrayList<Integer> denominatorFactors = new ArrayList<Integer>();

		for (int i = 2; i <= numerator; i++) {
			if (numerator % i == 0)
				numeratorFactors.add(i);
		}
		for (int i = 2; i <= denominator; i++) {
			if (denominator % i == 0)
				denominatorFactors.add(i);
		}

		int largest;
		for (int i = numeratorFactors.size() - 1; i >= 0; i--) {
			for (int j = denominatorFactors.size() - 1; j >= 0; j--) {
				if (numeratorFactors.get(i) == denominatorFactors.get(j))
					return true;
			}
		}
		return false;
	}

	public void reduce() {

		ArrayList<Integer> numeratorFactors = new ArrayList<Integer>(); 
		ArrayList<Integer> denominatorFactors = new ArrayList<Integer>();

		for (int i = 2; i <= numerator; i++) {
			if (numerator % i == 0)
				numeratorFactors.add(i);
		}
		for (int i = 2; i <= denominator; i++) {
			if (denominator % i == 0)
				denominatorFactors.add(i);
		}

		int largest;
		for (int i = numeratorFactors.size() - 1; i >= 0; i--) {
			for (int j = denominatorFactors.size() - 1; j >= 0; j--) {
				if (numeratorFactors.get(i) == denominatorFactors.get(j)) {
					numerator /= numeratorFactors.get(i);
					denominator /= denominatorFactors.get(j);
					return;
				}
			}
		}
	}
}