import java.awt.event.*;
import javax.swing.*;

class MyFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new MyFrame();
        frame.setVisible(true);
    }

    public MyFrame() {
        setTitle("My Empty Frame");
        setSize(500, 500);
        setLocation(0, 0);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}