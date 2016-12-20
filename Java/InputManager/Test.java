import java.awt.Color;
import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		MyPanel myPanel = new MyPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(myPanel);
		frame.pack();
		frame.setVisible(true);
		while (true) { 
			myPanel.update(); 
		}
	}
}