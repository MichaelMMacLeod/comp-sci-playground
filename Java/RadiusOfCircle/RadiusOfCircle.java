import java.io.*;
import java.util.Scanner;

public class RadiusOfCircle {

	/*/
	 * Created by Michael MacLeod
	 * 9/1/2016, 2:33 PM
	/*/

	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		double area;

		System.out.println("What is the area?_");
		area = scan.nextDouble();
		System.out.println("Radius of your circle is " + Math.sqrt(area / Math.PI));			
	}
}
