public class Robot {

	protected int[] hall;
	protected int pos;
	protected boolean facingRight;
	protected int moves = 0;

	public Robot(int[] hall, int pos, boolean facingRight) {
		this.hall = hall;
		this.pos = pos;
		this.facingRight = facingRight;
	}

	public String getHall() {
		String strHall = "";
		for (int i = 0; i < hall.length; i++) {
			strHall += hall[i] + " ";
		}
		return strHall;
	}

	protected boolean forwardMoveBlocked() {
		return facingRight ? pos + 1 == hall.length : pos == 0;
	}

	protected void move() {
		if (hall[pos] > 0)
			hall[pos]--;
		if (!forwardMoveBlocked() && hall[pos] == 0)
			pos += facingRight ? 1 : -1;
		else if (hall[pos] == 0)
			facingRight = !facingRight;
		System.out.println("hall: " + getHall() + (facingRight ? " right" : " left") + " " + moves + " move(s)");
	}

	public int clearHall() {
		while (!hallIsClear()) {
			move();
			moves++;
		}
		return moves;
	}

	protected boolean hallIsClear() {
		int trash = 0;
		for (int i = 0; i < hall.length; i++) {
			trash += hall[i];
		}
		return !(trash > 0);
	}
}