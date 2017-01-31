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

	public String determineWinner() {
		String winner = "no winner";

		if (playChoice != compChoice) {
			double play = Math.sin(playChoice), comp = Math.sin(compChoice);

			double playDiff = playChoice, compDiff = compChoice;
			int p = 0, c = 0;

			// use Math.abs() and 0.0001 because of rounding errors

			while (Math.abs(Math.sin(playChoice) - comp) > 0.0001) {
				playChoice += 2 * Math.PI / 3;
				p++;
			}

			while (Math.abs(Math.sin(compChoice) - play) > 0.0001) {
				compChoice += 2 * Math.PI / 3;
				c++;
			}

			if (p > c)
				winner = "player";
			else
				winner = "computer";
		}

		return winner;
	}

	@Override
	public String toString() {
		String output = 
		"winner: " 
		+ determineWinner() 
		+ "\nplayer: " 
		+ playChoiceString 
		+ "\ncomputer: " 
		+ compChoiceString + "\n";

		return output;
	}
}