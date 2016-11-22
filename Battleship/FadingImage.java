import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;

public class FadingImage {

	private static FadingImage[] list = new FadingImage[0];
	private int column, row, width = 28, height = 28;
	private Image image;
	private double alpha = 0;
	private int[] overlay = new int[3];

	public FadingImage(boolean hitTheTarget, int column, int row) {
		this.column = column;
		this.row = row;
		
		if (hitTheTarget) {
			image = new ImageIcon("hit.png").getImage();
			overlay[0] = 255;
			overlay[1] = 0;
			overlay[2] = 0;
		} else {
			image = new ImageIcon("missed.png").getImage();
			overlay[0] = 255;
			overlay[1] = 255;
			overlay[2] = 255;
		}

		FadingImage[] listNew = new FadingImage[list.length + 1];
		for (int i = 0; i < list.length; i++) {
			listNew[i] = list[i];
		}
		listNew[listNew.length - 1] = this;
		list = listNew;
	}

	/** 
	 * Returns the overlay with correct alpha
	 * Also increments the alpha if it is less than 255
	 */
	public Color getOverlay() {
		Color color = new Color(overlay[0], overlay[1], overlay[2], (int) alpha);
		alpha += alpha < 255 ? 0.5 : 0;
		return color;
	}

	public static void reset() {
		list = new FadingImage[0];
	}

	public static FadingImage[] getList() { return list; }
	public Image getImage() { return image; }
	public int getColumn() { return column; }
	public int getRow() { return row; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}