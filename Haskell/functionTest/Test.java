import java.util.Scanner;

class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long x = scan.nextLong();

		System.out.println(fac(x));
	}

	static long fac(long n) {
		if (n == 0)
			return 1;

		return n * fac(n - 1);
	}
}