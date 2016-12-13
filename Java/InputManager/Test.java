import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.add(new MyPanel());
		frame.setVisible(true);
	}
}