public class MatrixMult {

	public static int[][] mult(int[][] a, int[][] b) {

		int[][] ans = new int[a.length][b[0].length];

		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					ans[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		return ans;
	}
}