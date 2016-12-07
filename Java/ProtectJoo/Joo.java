import java.awt.Color;

public class Joo extends Circle {

    private double speed = 2;
    private double scalar;
    private GamePanel panel;
    private final double SPEED = 2;

    private double[] direction = 
    {
        0, // Current direction
        0  // The direction when init() is called
    };

    private double[] x = 
    {
        0, // Current x coordinate
        0  // The x coordinate when init() is called
    };
    private double[] y = 
    {
        0, // Current y coordinate
        0  // The y coordinate when init() is called
    };

    private double[] possibleDirections =
    {
        Math.PI / 6,
        Math.PI / 3,
        2 * Math.PI / 3,
        5 * Math.PI / 6,
        7 * Math.PI / 6,
        4 * Math.PI / 3,
        5 * Math.PI / 3,
        11 * Math.PI / 6
    };

    public Joo(double diameter, Color color, double scalar, GamePanel panel) {
        this.color = color;
        this.diameter = diameter;
        this.scalar = scalar;
        this.panel = panel;

        setNextX(getScalar() + getPanel().getWidth() / 2 - getDiameter() / 2);
        setNextY(getScalar() + getPanel().getHeight() / 2 - getDiameter() / 2);

        setNextDirection(getNewDirection());
    }

    public double getX() { 
        return x[0]; 
    }
    public void setX(double x) { 
        this.x[0] = x; 
    }
    public double getY() { 
        return y[0]; 
    }
    public void setY(double y) {
        this.y[0] = y;
    }
    public double getNextX() { 
        return x[1]; 
    }
    public void setNextX(double nextX) {
        this.x[1] = nextX;
    }
    public double getNextY() { 
        return y[1]; 
    }
    public void setNextY(double nextY) {
        this.y[1] = nextY;
    }
    public double getDirection() { 
        return direction[0]; 
    }
    public void setDirection(double direction) {
        this.direction[0] = direction;
    }
    public double getNextDirection() { 
        return direction[1]; 
    }
    public void setNextDirection(double nextDirection) {
        this.direction[1] = nextDirection;
    }
    public double getSpeed() { 
        return speed; 
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getScalar() { 
        return scalar; 
    }
    public void setScalar(double scalar) {
        this.scalar = scalar;
    }
    public GamePanel getPanel() { 
        return panel; 
    }
    public double getNewDirection() {
        int rand = (int) (Math.random() * (possibleDirections.length - 1));
        return possibleDirections[rand];
    }

    public void stop() {
        setSpeed(0);
    }

    /**
     * Checks collisions with walls, and adjusts direction accordingly.
     * 
     * @param wall is 0 (top), 1 (right), 2 (bottom), 3 (left)
     */
    public void checkBounce(int wall) {
        switch (wall) {
            case 0:
            if (getY() <= 0)
                setDirection(-getDirection());
            break;
            case 1:
            if (getX() + getDiameter() >= getPanel().getWidth())
                setDirection(Math.PI - getDirection());
            break;
            case 2:
            if (getY() + getDiameter() >= getPanel().getHeight())
                setDirection(-getDirection());
            break;
            case 3:
            if (getX() <= 0)
                setDirection(Math.PI - getDirection());
            break;
            default: break;
        }
    }

    /** Returns true if Joo collides with object */
    public boolean checkCollision(Circle object) {
        // Pythagorean theorem
        double a = object.getCenterX() - getCenterX();
        double b = object.getCenterY() - getCenterY();
        double c = Math.sqrt(a * a + b * b);

        // Using diameter / 3 instead of diameter / 2 in order to be a bit more
        // generous regarding hitbox collision
        if (c < getDiameter() / 3 + object.getDiameter() / 3)
            return true;

        return false;
    }

    public void init() {
        setX(getNextX());
        setY(getNextY());
        setDirection(getNextDirection());

        double rand = Math.random() * Math.PI * 2;

        setNextX
        (
            Math.cos(rand) * getScalar() + 
            getPanel().getWidth() / 2 -
            getDiameter() / 2
        );
        setNextY
        (
            Math.sin(rand) * scalar + 
            getPanel().getHeight() / 2 - 
            getDiameter() / 2
        );

        setNextDirection(getNewDirection());

        setSpeed(SPEED);
    }

    public void move() {
        setX(getX() + Math.cos(getDirection()) * getSpeed());
        setY(getY() + Math.sin(getDirection()) * getSpeed());
    }
}