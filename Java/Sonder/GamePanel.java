import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int width, height;

    private InputManager input;

    private Camera camera;

    private Ship player1, player2;
    private Square block;
    private ArrayList<Drawn> focuses;
    private ArrayList<Drawn> objects;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        restart();

        input = new InputManager(this);

        // Player 1
        input.addKey("w");
        input.addKey("a");
        input.addKey("d");
        input.addKey("s");

        // Player 2
        input.addKey("i");
        input.addKey("j");
        input.addKey("l");
        input.addKey("k");
    }

    private void restart() {
        player1 = new Ship(new Triangle(30, -60, 0, 0, Color.BLUE), 0.05, 120);
        player2 = new Ship(new Triangle(30, 60, 0, Math.PI, Color.RED), 0.05, 120);

        block = new Square(120, 0, 0, 0, Color.GREEN);

        focuses = new ArrayList<Drawn>();
        objects = new ArrayList<Drawn>();

        focuses.add(player1.getShape());
        focuses.add(player2.getShape());
        focuses.add(block);

        objects.add(player1.getShape());
        objects.add(player2.getShape());
        objects.add(block);

        camera = new Camera();
    }

    public void update() {
        if (input.held("s")) 
            player1.thrust();
        if (input.held("a"))
            player1.rotate(false);
        if (input.held("d"))
            player1.rotate(true);

        if (input.held("k"))
            player2.thrust();
        if (input.held("j"))
            player2.rotate(false);
        if (input.held("l"))
            player2.rotate(true);

        player1.updatePos();
        player2.updatePos();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        camera.draw(g, getWidth(), getHeight(), focuses, objects);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}