import java.util.Arrays;

public class SortStringArray {

	public static void main(String[] args) {

		String[] ss =
		{
			"Bill",
			"Mary",
			"Lee",
			"Agnes",
			"Alfred",
			"Thomas",
			"Alvin",
			"Bernard",
			"Ezra",
			"Herman"
		};

		Arrays.sort(ss);

		System.out.println("Ascend    Descend\n");
		for (int i = 0; i < ss.length; i++) {
			System.out.print(ss[i]);
			for (int j = 0; j < 10 - ss[i].length(); j++) {
				System.out.print(" ");
			}
			System.out.print(ss[ss.length - 1 - i]);
			System.out.println();
		}
	}
}