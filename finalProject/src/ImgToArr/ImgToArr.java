import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.io.PrintWriter;

class ImgToArr {
	public static void main(String[] args) throws IOException {
		BufferedImage img = ImageIO.read(new File("toClassify.gif"));

		Color[][] pixels = new Color[img.getHeight()][img.getWidth()];

		for (int r = 0; r < img.getHeight(); r++) {
			for (int c = 0; c < img.getWidth(); c++) {
				pixels[r][c] = new Color(img.getRGB(r, c));
			}
		}

		int[][][] rgb = new int[pixels.length][][];
		for (int r = 0; r < rgb.length; r++) {
			rgb[r] = new int[pixels[r].length][];
			for (int c = 0; c < rgb[r].length; c++) {
				rgb[r][c] = new int[3];
				rgb[r][c][0] = pixels[r][c].getRed();
				rgb[r][c][1] = pixels[r][c].getGreen();
				rgb[r][c][2] = pixels[r][c].getBlue();
			}
		}

		String arr = convert(rgb);

		PrintWriter fw = new PrintWriter(new File("../toClassify.txt"));
		fw.print(arr);
		fw.close();
	}

	static String convert(int[][][] xs) {
		String converted = "";

		converted += "[";
		for (int i = 0; i < xs.length; i++) {
			for (int j = 0; j < xs[i].length; j++) {
				converted += "[";
				for (int k = 0; k < xs[i][j].length; k++) {
					if (k != xs[i][j].length - 1) {
						converted += xs[i][j][k] + ",";
					} else {
						converted += xs[i][j][k] + "]";
					}
				}
				if (j != xs[i].length - 1) {
					converted += ",";
				}
			}
		}
		converted += "]";

		return converted;
	}
}
