public class RockPaperScissors {
	private int playChoice;
	private int compChoice;

	public RockPaperScissors(String playChoice) {
		compChoice = (int) (Math.random() * 3);

		if (playChoice.equalsIgnoreCase("Rock"))
			this.playChoice = 0;
		else if (playChoice.equalsIgnoreCase("Paper"))
			this.playChoice = 1;
		else
			this.playChoice = 2;
	}

	public String determineWinner() {
		String winner = "no winner";

		int comp = compChoice;
		int play = playChoice;

		int diff = comp < play ? comp : play;

		comp -= diff;
		play -= diff;

		boolean playerWon = play > comp;

		if (playerWon)
			winner = "player";
		else
			winner = "computer";

		return winner;
	}

	@Override
	public String toString() {
		String output = 
			"winner: " 
			+ determineWinner() 
			+ " playChoice: " 
			+ playChoice 
			+ " compChoice " 
			+ compChoice;

		return output;
	}
}