public class R3D {

	public static void main(String[] args) {
		double[] a = {10, 10, 10};
		double[] c = {0, 0, 0};
		double[] t = {0, 2, 0};
		double[] e = {1, 1, 1};

		double[] ans = project(a, c, t, e);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	// https://en.wikipedia.org/wiki/3D_projection
	public static double[] project(double[] a, double[] c, double[] t, double[] e) {
		double tx = t[0], ty = t[1], tz = t[2];
		double ax = a[0], ay = a[1], az = a[2];
		double cx = c[0], cy = c[1], cz = c[2];
		double ex = e[0], ey = e[1], ez = e[2];

		double ad = ax - cx;
		double bd = ay - cy;
		double cd = az - cz;

		double dx = ad * Math.cos(ty) * Math.cos(tz)
			+ bd * Math.cos(ty) * Math.sin(tz)
			- cd * Math.sin(ty);
		double dy = -ad * Math.cos(tx) * Math.sin(tz)
			+ bd * Math.cos(tx) * Math.cos(tz)
			+ cd * Math.sin(tx) * Math.cos(ty)
			+ Math.sin(tx) * Math.sin(ty) * Math.cos(tz)
			+ Math.sin(tx) * Math.sin(ty) * Math.sin(tz);
		double dz = ad * Math.sin(tx) * Math.sin(tz)
			- bd * Math.sin(tx) * Math.cos(tz)
			+ cd * Math.cos(tx) * Math.cos(ty)
			+ Math.cos(tx) * Math.sin(ty) * Math.cos(tz)
			+ Math.cos(tx) * Math.sin(ty) * Math.sin(tz);

		double bx = ez / dz * dx - ex;
		double by = ez / dz * dy - ey;

		double[] ans = {bx, by};

		return ans;
	}
}