public class R3D {

	public static void main(String[] args) {
		double[] a = {0, 0, 0};
		double[] c = {0, 0, 0};
		double[] t = {0, 0, 0};
		double[] e = {0, 0, 0};

		double[] ans = project(a, c, t, e);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	// https://en.wikipedia.org/wiki/3D_projection
	public static double[] project(double[] a, double[] c, double[] theta, double[] e) {
		double[][] m1 = 
		{
			{1,  0,                  0                 },
			{0,  Math.cos(theta[0]), Math.sin(theta[0])},
			{0, -Math.sin(theta[0]), Math.cos(theta[0])}
		};
		double[][] m2 =
		{
			{Math.cos(theta[1]), 0, -Math.sin(theta[1])},
			{0,                  1,  0                 },
			{Math.sin(theta[1]), 0,  Math.cos(theta[1])}
		};
		double[][] m3 =
		{
			{ Math.cos(theta[2]), Math.sin(theta[2]), 0},
			{-Math.sin(theta[2]), Math.cos(theta[2]), 0},
			{0,                   0,                  1}
		};
		double[][] m4 =
		{{
			a[0] - c[0],
			a[1] - c[1],
			a[2] - c[2]
		}};

		double[][] d = mult(mult(m1, m2), m3);
		double bx = e[2] / d[0][2] * d[0][0] - e[0];
		double by = e[2] / d[0][2] * d[0][1] - e[1];

		double[] ans = {bx, by};
		return ans;
	}

	public static double[][] mult(double[][] a, double[][] b) {
		int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
	}
}