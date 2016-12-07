public class DistToLine {

	public static double A, B, C;

	public static double getDist(double a, double b) {

		return Math.abs(A * a + B * b + C) / Math.sqrt(A * A + B * B);
	}
}