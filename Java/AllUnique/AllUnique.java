class AllUnique {
	public static void main(String[] args) {
		System.out.println(allUnique("abcdefghijklmnopqrstuvwxyz"));
	}

	static boolean allUnique(String s) {
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (j == i)
					continue;
				if (s.charAt(i) == s.charAt(j))
					return false;
			}
		}
		return true;
	}
}