import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import java.awt.Point;

public class Input extends JPanel {
    private final int width, height;
    
    private final InputManager inputManager;

    private final ArrayList<Point> points;

    private double mx, my;

    public Input(int width, int height) {
        this.width = width;
        this.height = height;

        inputManager = new InputManager(this);
        inputManager.addKey("e");

        points = new ArrayList<>();
    }

    public void update() {
        if (inputManager.held("mouse"))
            points.add(new Point((int) mx, (int) my));

        mx = inputManager.mx();
        my = inputManager.my();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Point p : points)
            g.fillOval(p.x - 15, p.y - 15, 30, 30);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}