import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Generate which term number? ");
		int k = scan.nextInt();
		System.out.println("Term #" + k + " is " + ModFib.modFibonacci(k));
	}
}