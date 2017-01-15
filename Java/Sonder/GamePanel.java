import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    private Camera camera;

    private Triangle ship, block;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        restart();
    }

    private void restart() {
        input = new InputManager(this);

        input.addKey("w");
        input.addKey("a");
        input.addKey("d");

        ship = new Triangle(30, 0, 0, 0, Color.BLACK);
        block = new Triangle(10, 100, 100, 0, Color.RED);

        camera = new Camera(width, height, ship);
    }

    public void update() {
        if (input.held("w")) {
            ship.moveX(2 * Math.cos(ship.getRotation()));
            ship.moveY(2 * Math.sin(ship.getRotation()));
        }
        if (input.held("a"))
            ship.rotate(-Math.PI / 90);
        if (input.held("d"))
            ship.rotate(Math.PI / 90);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera.draw(g, ship);
        camera.draw(g, block);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}