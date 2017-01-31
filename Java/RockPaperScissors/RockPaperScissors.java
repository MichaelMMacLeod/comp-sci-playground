public class RockPaperScissors {
	private double playChoice;
	private double compChoice;

	private String playChoiceString;
	private String compChoiceString;

	public RockPaperScissors(String playChoice) {
		double[] pos = new double[] {0, 2 * Math.PI / 3, 4 * Math.PI / 3};
		String[] str = new String[] {"rock", "paper", "scissors"};

		int rand = (int) (Math.random() * 3);
		compChoice = pos[rand];
		compChoiceString = str[rand];

		if (playChoice.equalsIgnoreCase("Rock"))
			this.playChoice = pos[0];
		else if (playChoice.equalsIgnoreCase("Paper"))
			this.playChoice = pos[1];
		else
			this.playChoice = pos[2];

		playChoiceString = playChoice;
	}

	// Maybe not the fastest, but still kinda interesting.
	//
	// First, we assign values to each of the inputs.
	//
	//     rock:     sin(0pi)   == 0
	//     paper:    sin(2pi/3) == Math.sqrt(3) / 2
	//     scissors: sin(4pi/3) == -Math.sqrt(3) / 2
	//
	// Notice how sin(x) at x = 2pi/3 gives you rock. Adding 2pi/3 will give 
	// you paper. Adding another 2pi/3 will give you scissors. Adding another 
	// 2pi/3 will bring you back to where you started: rock.
	//
	// Next, we check how much 2pi/3 we have to add from each of the inputs to 
	// get to the other input. For example...
	//
	//     input1 = 0        sin(0)     = 0
	//     input2 = 2pi/3    sin(2pi/3) = Math.sqrt(3) / 2
	//
	//     Math.sin(input1 + 1 * (2pi/3)) == Math.sin(input2)
	//     Math.sin(input2 + 2 * (2pi/3)) == Math.sin(input1)
	//
	//     We had to add 2pi/3 to input 1, and 4pi/3 to input2.
	//
	// The winner is the one who had to add the most 2pi/3.

	// Does 1, 5, or 6 comparisons. That's kinda depressing, because we could
	// just have less comparisons by chaining if-else statements*.
	// ie:
	//
	//     winner = none
	//
	//     if player picked rock
	//         if computer picked paper
	//             winner = computer
	//         else if computer picked scissors
	//             winner = player
	//     else if player picked paper
	//         ...
	//
	//     else
	//         ...
	//
	//     return winner
	//
	// That strategy has a minimum of 2 comparisons, and a max of 3.
	//
	// *BUT WAIT...
	// 
	// The String class uses this method (.equals()) to compare strings:
	//
	// from: http://www.docjar.com/html/api/java/lang/String.java.html
	//
	// public boolean equals(Object anObject) {
	// 	if (this == anObject) {
	// 		return true;
	// 	}
	// 	if (anObject instanceof String) {
	// 		String anotherString = (String)anObject;
	// 		int n = count;
	// 		if (n == anotherString.count) {
	// 			char v1[] = value;
	// 			char v2[] = anotherString.value;
	// 			int i = offset;
	// 			int j = anotherString.offset;
	// 			while (n-- != 0) {
	// 				if (v1[i++] != v2[j++])
	// 					return false;
	// 			}
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }
	//
	// Each call of .equals() does a bunch of comparisons.
	// .equalsIgnoreCase() does even more.
	//
	// If we were to use the strategy above (which uses .equals()), we would 
	// be "hiding" our comparisons in the String class.
	//
	// so...
	//
	// technically...
	//
	// this has less comparisons.
	// 
	// Unless you decide to just take the first character of input and store 
	// it in a char; then this whole train of logic kinda falls apart because 
	// you use == instead of .equals().

	public String determineWinner2() {
		int compare = 0;

		String winner = "no winner";

		if (playChoice != compChoice) { 
			compare++;

			double play = Math.sin(playChoice), comp = Math.sin(compChoice);

			double playDiff = playChoice, compDiff = compChoice;
			int p = 0, c = 0;

			// use Math.abs() and 0.0001 because of rounding errors

			while (Math.abs(Math.sin(playChoice) - comp) > 0.0001) {
				compare++;

				playChoice += 2 * Math.PI / 3;
				p++;
			}

			while (Math.abs(Math.sin(compChoice) - play) > 0.0001) {
				compare++;

				compChoice += 2 * Math.PI / 3;
				c++;
			}

			if (p > c) {
				compare++;

				winner = "player";
			} else {
				winner = "computer";
			}
		}

		compare++;

		System.out.println("Number of comparisons: " + compare);

		return winner;
	}

	@Override
	public String toString() {
		String output = 
		"winner: " 
		+ determineWinner2() 
		+ "\nplayer: " 
		+ playChoiceString 
		+ "\ncomputer: " 
		+ compChoiceString + "\n";

		return output;
	}
}