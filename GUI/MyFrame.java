import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    public MyFrame(String s) {
        setTitle(s);
        setSize(500, 500);
        setLocation(0, 0);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Container contentPane = getContentPane();
        contentPane.add(new TextPanel("Hello World!", 100, 100));
    }
}