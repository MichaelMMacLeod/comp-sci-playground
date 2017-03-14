import java.util.Arrays;	

public class TowersOfHanoi {

	public static void main(String[] args) {
		System.out.println(solve(3);
	}

	static String solve(int n) {
		if (n == 0)
			return "";
		if (n == 1)
			return 
		return "Move 1 to " solve(--n);
	}
}

/* 4 tall
 1 to 2 : 1
 1 to 3 : 2
 2 to 3 : 1

 1 to 2 : 1
 3 to 1 : 2
 3 to 2 : 1

 1 to 2 : 1
 1 to 3 : 2
 2 to 3 : 1

 2 to 1 : 1
 3 to 1 : 2
 2 to 3 : 1

 1 to 2 : 1
 1 to 3 : 2
 2 to 3 : 1
*/

 /* 3 tall
 1 to 3 : 2
 1 to 2 : 1
 3 to 2 : 1
 1 to 3 : 2
 2 to 1 : 1
 2 to 3 : 1
 1 to 3 : 2
 */