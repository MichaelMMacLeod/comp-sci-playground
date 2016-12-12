public class TrumpBot extends Robot {

	protected int[] them;

	/**
	 * It would be a lot easier to just throw our trash at someone
	 * else than it would be to vacuum it up, right?
	 */
	public TrumpBot(int[] hall, int pos, boolean facingRight) {
		super(hall, pos, facingRight);

		buildWall();
	}

	/**
	 * Builds a wall between us and them
	 * (us){0, 1, 2, 3, 4} -> (us){0, 1} |WALL| (them){2, 3, 4}
	 * Also sets position to zero
	 */
	public void buildWall() {
		int wallPos = hall.length / 2;

		them = new int[hall.length - wallPos];
		for (int i = 0; i < them.length; i++) {
			them[i] = hall[i + wallPos];
		}

		int[] hallNew = new int[wallPos];
		for (int i = 0; i < hallNew.length; i++) {
			hallNew[i] = hall[i];
		}
		hall = hallNew;

		pos = 0;
	}

	/**
	 * Picks up the trash at pos, and throws it over the wall
	 * Requires a wall to be already built
	 */
	@Override
	public void move() {
		if (hall[pos] > 0) {
			hall[pos]--;
			them[0]++;
		}
		if (!forwardMoveBlocked() && hall[pos] == 0)
			pos += facingRight ? 1 : -1;
		else if (hall[pos] == 0)
			facingRight = !facingRight;
		System.out.println
		(
			"hall: " + getHall() 
			+ "them: " + getThem()
			+ (facingRight ? "right" : "left") 
			+ " " + moves + " move(s)"
		);
	}

	/**
	 * Contacts spies in enemy territory to relay information pertaining to
	 * trash accumulation 
	 */
	public String getThem() {
		String strHall = "";
		for (int i = 0; i < them.length; i++) {
			strHall += them[i] + " ";
		}
		return strHall;
	}
}