import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    private Camera camera;

    private Triangle ship, block, block2;

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
        block = new Triangle(10, 0, 0, 0, Color.RED);
        block2 = new Triangle(50, 100, 100, Math.PI / 3, Color.BLUE);

        camera = new Camera(width, height, ship);
    }

    public void update() {
        if (input.held("w")) {
            ship.moveX(10 * Math.cos(ship.getRotation()));
            ship.moveY(10 * Math.sin(ship.getRotation()));
        }
        if (input.held("a"))
            ship.rotate(-Math.PI / 36);
        if (input.held("d"))
            ship.rotate(Math.PI / 36);

        block.moveX(3);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera.draw(g, ship);
        camera.draw(g, block);
        camera.draw(g, block2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}