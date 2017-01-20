import java.awt.Color;

public class Square extends Drawn {

    public Square(int x, 
        int y,
        int size,
        double rotation, 
        Color color) {

        super(x, 
            y, 
            new int[] {-1, 1, 1, -1}, 
            new int[] {-1, -1, 1, 1}, 
            size, 
            rotation, 
            color);
    }
}