import java.awt.Color;

public class Square extends Drawn {

    public Square(double size, 
        double x, 
        double y, 
        double rotation, 
        Color color) {

        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.color = color;

        xVerts = new double[] {-1, 1, 1, -1};
        yVerts = new double[] {-1, -1, 1, 1};
        for (int i = 0; i < xVerts.length; i++) {
            xVerts[i] *= size;
            yVerts[i] *= size;
        }
    }
}