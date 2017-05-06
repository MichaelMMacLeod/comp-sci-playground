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

		int[][] greyScale = new int[pixels.length][];
		for (int r = 0; r < greyScale.length; r++) {
			greyScale[r] = new int[pixels[r].length];
			for (int c = 0; c < greyScale[r].length; c++) {
				int average = 0;
				average += pixels[r][c].getRed();
				average += pixels[r][c].getGreen();
				average += pixels[r][c].getBlue();
				greyScale[r][c] = average / 3;
			}
		}

		String arr = convert(greyScale);

		PrintWriter fw = new PrintWriter(new File("../toClassify.txt"));
		fw.print(arr);
		fw.close();
	}

	static String convert(int[][] xs) {
		String converted = "";

		converted += "[";
		for (int i = 0; i < xs.length; i++) {
			converted += "[";
			for (int j = 0; j < xs[i].length; j++) {
				if (j != xs[i].length - 1)
					converted += xs[i][j] + ",";
				else
					converted += xs[i][j] + "]";
			}
			if (i != xs.length - 1)
				converted += ",";
		}
		converted += "]";

		return converted;
	}
}
