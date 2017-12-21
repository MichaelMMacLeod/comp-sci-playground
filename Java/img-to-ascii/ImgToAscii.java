import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

class ImgToAscii {
    static class ColorData {
        int r, g, b;
        ColorData(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("src.png"));
        
        int width = img.getWidth();
        int height = img.getHeight();

        int[] data = img.getRGB(0, 0, width, height, null, 0, width);

        ColorData[] colors = new ColorData[data.length];
        
        for (int i = 0; i < colors.length; i++) {
            Color c = new Color(data[i]);
            colors[i] = new ColorData(
                c.getRed(),
                c.getGreen(),
                c.getBlue());
        }

        int[] grayscale = new int[colors.length];

        for (int i = 0; i < grayscale.length; i++) {
            grayscale[i] = (colors[i].r + colors[i].g + colors[i].b) / 3;
        }

        int cValue = 10;

        for (int i = 0; i < width; i += cValue) {
            for (int j = 0; j < height; j += cValue) {
                System.out.print(toAscii(grayscale[width * i + j]));
            }
            System.out.println();
        }
    }

    static char toAscii(int i) {
        if (i > 250)
            return '#';
        if (i > 200)
            return '%';
        if (i > 150)
            return 'O';
        if (i > 50)
            return ':';
        return '.';
    }
}
