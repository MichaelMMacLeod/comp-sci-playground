public class KeyLog {

	private final String NONE = "NONE";
	String[] log = {NONE, NONE};

	public KeyLog() {}

	public void addKey(String key) {

		if (!log[1].equals(key)) {
			log[0] = log[1];
			log[1] = key;
		}
	}

	public String getKey() {

		return log[0];
	}

	public void shift() {

		log[0] = log[1];
		log[1] = NONE;
	}
}