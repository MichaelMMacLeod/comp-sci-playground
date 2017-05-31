import javax.swing.*;

import java.awt.Color;

public class Init {
    private static Input input;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        input = new Input(800, 800);

        SwingUtilities.invokeLater(Init::createAndShowGUI);

        loop();
    }

    private static void loop() {
        double previous = System.currentTimeMillis();
        double lag = 0;

        while (true) {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= 50) {
                input.update();
                input.repaint();
                lag -= 50;
            }
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Input");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(false);

        input.setBackground(Color.WHITE);

        frame.add(input);
        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}