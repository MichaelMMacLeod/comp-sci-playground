import javax.swing.JFrame;

public class R3D {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");

		JFrame frame = new JFrame("R3D");
		MyPanel myPanel = new MyPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(myPanel);
		frame.pack();

		frame.setVisible(true);

		while (true) {
			myPanel.update();
			myPanel.repaint();
		}
	}

	// https://en.wikipedia.org/wiki/3D_projection
	public static double[] project(double[] a, double[] c, double[] t, double[] e) {
		double tx = t[0] * Math.PI / 180, 
			ty = t[1] * Math.PI / 180, 
			tz = t[2] * Math.PI / 180;
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