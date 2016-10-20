public class KeyLog {

	private final String LEFT = "LEFT";
	private final String UP = "UP";
	private final String RIGHT = "RIGHT";
	private final String DOWN = "DOWN";
	private final String NONE = "NONE";
	private String[] log = {NONE, NONE};

	public KeyLog() {}

	public void addKey(String key) {

		if (!log[1].equals(key)) {
			log[0] = log[1];
			log[1] = key;
		}
	}

	public String oppositeKey(String key) {
		
		switch (key) {
			case LEFT:
				return RIGHT;
			case UP:
				return DOWN;
			case RIGHT:
				return LEFT;
			case DOWN:
				return UP;
		}
		
		return NONE;
	}

	public String getKey() {

		return log[0];
	}

	public void shift() {

		log[0] = log[1];
		log[1] = NONE;
	}
}