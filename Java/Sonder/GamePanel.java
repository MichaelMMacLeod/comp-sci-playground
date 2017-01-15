import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    private Camera camera;

    private Triangle ship;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        restart();
    }

    private void restart() {
        input = new InputManager(this);

        ship = new Triangle(30, 0, 0, 0, Color.BLACK);

        camera = new Camera(width, height, ship);
    }

    public void update() {
    	ship.rotate(Math.PI / 180);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera.draw(g, ship);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}