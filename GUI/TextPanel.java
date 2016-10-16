import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TextPanel extends JPanel {

    public String text;
    public int x, y;

    public static void main(String[] args) {
        JFrame frame = new MyFrame("Hello World Frame");
        frame.setVisible(true);
    }

    public TextPanel(String t, int cx, int cy) {
        text = t;
        x = cx;
        y = cy;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("SansSerif", Font.BOLD, 14);
        Font fi = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
        FontMetrics fm = g.getFontMetrics(f);
        g.setFont(f);
        g.drawString(text, x, y);
    }
}