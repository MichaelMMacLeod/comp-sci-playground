import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    private Camera camera;

    private Triangle block, block2;

    private Ship ship;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        restart();

        input = new InputManager(this);

        input.addKey("w");
        input.addKey("a");
        input.addKey("d");
    }

    private void restart() {
        ship = new Ship(new Triangle(30, 0, 0, 0, Color.BLACK), 1);

        block = new Triangle(10, 0, 0, 0, Color.RED);
        block2 = new Triangle(50, 100, 100, Math.PI / 3, Color.BLUE);

        Drawn[] objects =
        {
            ship.getShape(),
            block,
            block2
        };
        camera = new Camera(width, height, ship.getShape(), objects);
    }

    public void update() {
        if (input.held("w")) 
            ship.thrust();
        if (input.held("a"))
            ship.rotate(false);
        if (input.held("d"))
            ship.rotate(true);

        ship.updatePos();

        block.moveX(3);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}