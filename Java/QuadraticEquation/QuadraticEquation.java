public class QuadraticEquation {

	public static void main(String[] args) {
		double[] parsedArgs = new double[args.length];
		for (int i = 0; i < args.length; i++) {
			parsedArgs[i] = Double.parseDouble(args[i]);
		}
		double[] ans = findRoots(parsedArgs[0], parsedArgs[1], parsedArgs[2]);
		System.out.println("Finding roots of: " + parsedArgs[0] + "x^2 + " + parsedArgs[1] + "x + " + parsedArgs[2]);
		for (int i = 0; i < ans.length; i++) {
			System.out.println("Root #" + (i + 1) + ": " + ans[i]);
		}
	}

	/** Returns the roots of a quadratic in the form of ax^2 + bx + c
	 * @param a is the coefficient in front of the x^2 term
	 * @param b is the coefficient in front of the x^1 term
	 * @param c is the coefficient in front of the x^0 term
	 */
	public static double[] findRoots(double a, double b, double c) {
		double[] ans = new double[2];
		if (Math.pow(b, 2) - 4 * a * c < 0) {
			System.out.println("This requires imaginary numbers, and I'm not feeling very imaginary right now.");
			System.exit(0);
		} else {
			ans[0] = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
			ans[1] = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		}
		return ans;
	}
}