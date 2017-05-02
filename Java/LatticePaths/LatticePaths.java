class LatticePaths {
	static long[][] map = new long[41][41];

	public static void main(String[] args) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				try {
					map[r][c] = map[r - 1][c] + map[r][c - 1];
				} catch (Exception e) {
					map[r][c] = 1;
				}
			}
		}
		p(map);
		System.out.println(map[40][40]);
	}

	static void p(long[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}