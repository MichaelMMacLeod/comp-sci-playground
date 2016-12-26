import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        input = new InputManager(this);
    }

    private void restart() {

    }

    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}