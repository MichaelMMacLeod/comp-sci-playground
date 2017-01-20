import java.awt.Color;

public class Triangle extends Drawn {

    public Triangle(int x,
        int y,
        int size,
        double rotation, 
        Color color) {

        super(x, 
            y, 
            new int[] {-1, 1, -1}, 
            new int[] {-1, 0, 1}, 
            size, 
            rotation, 
            color);
    }
}