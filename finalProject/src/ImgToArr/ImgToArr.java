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
		char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		for (char c : letters) {
			for (int i = 0; i < 200; i++) {
				System.out.println("converting: " + "training/lower/" + c + "/" + i + ".gif");
				try {
					convertImage("training/lower/" + c + "/" + i + ".gif",
								 "../training/lower/" + c + "/" + i + ".txt");
				} catch (IOException e) {
					System.out.println("Error converting " + "training/lower/" + c + "/" + i + ".gif");
				}
			}
		}

		for (char c : letters) {
			for (int i = 0; i < 200; i++) {
				System.out.println("converting: " + "training/upper/" + c + "/" + i + ".gif");
				try {
					convertImage("training/upper/" + c + "/" + i + ".gif",
								 "../training/upper/" + c + "/" + i + ".txt");
				} catch (IOException e) {
					System.out.println("Error converting " + "training/lower/" + c + "/" + i + ".gif");
				}
			}
		}
	}

	static void convertImage(String filePath, String outputFile) throws IOException {
		BufferedImage img = ImageIO.read(new File(filePath));

		Color[][] pixels = new Color[img.getHeight()][img.getWidth()];

		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
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

		PrintWriter fw = new PrintWriter(new File(outputFile));
		fw.print(arr);
		fw.close();
	}

	static String convert(int[][] xs) {
		String converted = "";

		converted += "[";
		for (int i = 0; i < xs.length; i++) {
			for (int j = 0; j < xs[i].length; j++) {
				converted += xs[i][j] + ",";
			}
		}
		converted = converted.substring(0, converted.length() - 1);
		converted += "]";

		return converted;
	}
}
