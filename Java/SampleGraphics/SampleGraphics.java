import javax.swing.BorderFactory;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SampleGraphics {

    private static MyPanel myPanel;
    private static final int MS_PER_UPDATE = 10;
    private static boolean on = true;

    public static void main(String[] args) {

        // System.setProperty("sun.java2d.opengl", "true");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        myPanel = new MyPanel(width, height);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        loop();
    }

    private static void loop() {

        double previous = System.currentTimeMillis();
        double lag = 0;

        while (on) {

            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= MS_PER_UPDATE) {
                myPanel.updateLogic();
                lag -= MS_PER_UPDATE;
            }

            myPanel.repaint();
        }
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Snowflake");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        myPanel.setBackground(Color.BLACK);

        frame.add(myPanel);
        frame.pack();

        // Center the window
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
    }
}