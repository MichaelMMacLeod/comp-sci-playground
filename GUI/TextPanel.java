import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TextPanel extends JPanel {
	public static void main(String[] args) {
        JFrame frame = new MyFrame("Hello World Frame");
        frame.setVisible(true);
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font f = new Font("SansSerif", Font.BOLD, 14);
		Font fi = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
		FontMetrics fm = g.getFontMetrics(f);
		FontMetrics fim = g.getFontMetrics(fi);
		int cx = 75, cy = 100;
		g.setFont(f);
		g.drawString("Hello, ", cx, cy);
		cx += fm.stringWidth("Hello, ");
		g.setFont(fi);
		g.drawString("World!", cx, cy);
	}
}