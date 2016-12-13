import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		MyPanel myPanel = new MyPanel();
		frame.add(myPanel);
		frame.setVisible(true);
		while (true) { myPanel.update(); }
	}
}