// © A+ Computer Science  -  www.apluscompsci.com

public class RockPaperScissors {
	private String playChoice;
	private String compChoice;

	public RockPaperScissors() {
		compChoice = "";
		playChoice = compChoice;
	}

	public RockPaperScissors(String playChoice) {
		int rand = (int) (Math.random() * 3);

		if (rand == 0) compChoice = "Rock";
		if (rand == 1) compChoice = "Paper";
		if (rand == 2) compChoice = "Scissors";

		this.playChoice = playChoice;
	}

	public String determineWinner() {
		String winner = "none, because " + compChoice + " is the same as " + playChoice;

		if (playChoice.equalsIgnoreCase("Rock")) {
			if (compChoice.equals("Scissors")) {
				winner = "player, because Rock beats Scissors";
			}
			if (compChoice.equals("Paper")) {
				winner = "computer, because Paper beats Rock";
			}
		}
		if (playChoice.equalsIgnoreCase("Paper")) {
			if (compChoice.equals("Rock")) {
				winner = "player, because Paper beats Rock";
			}
			if (compChoice.equals("Scissors")) {
				winner = "computer, because Scissors beats Paper";
			}
		}
		if (playChoice.equalsIgnoreCase("Scissors")) {
			if (compChoice.equals("Paper")) {
				winner = "player, because Scissors beats Paper";
			}
			if (compChoice.equals("Rock")) {
				winner = "computer, because Rock beats Scissors";
			}
		}

		winner = "Winner: " + winner;

		return winner;
	}

	@Override
	public String toString() {
		String output = "";

		output += "Player: " + playChoice + "\n";
		output += "Computer: " + compChoice + "\n";
		output += determineWinner();

		return output;
	}
}