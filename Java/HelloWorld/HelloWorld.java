public class HelloWorld {

	public static void main(String args[]) {
		p(" __________ ");
		p("|          |");
		p("| ( o) ( o)|");
		p("|     ^    |");
		p("|   ====== |");
		p("|__________|");
		p("     _| |_____");
		p("    |         |");
		p("    | ||   || |");
		p("    | ||   || |");
		p("    | \\/   \\/ |");
		p("    |_________|");
		p("    |---------|");
		p("    |         |");
		p("    |    |    |");
		p("    |    |    |");
		p("    |    |    |");
		p("    |    |^^^^|");
		p("    |    |");
		p("    |^^^^|");
	}

	private static void p(String text) {
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				System.out.println("You can't take the sky from me, " + e);
			}
			
		}
		System.out.println("");
	}
}
