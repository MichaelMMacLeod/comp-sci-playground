import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Menu {

	private Font font = new Font("Courier", Font.PLAIN, 20);
	private Color color = Color.BLACK;
	private MenuItem[] items = {};
	private boolean hidden = true;
	private int x;
	private int y;

	public Menu(int x, int y) {

		this.x = x;
		this.y = y;
	}
	
	public void setFont(String font, int flag, int size, Color color) {

		this.font = new Font(font, flag, size);
		this.color = color;
	}

	/** Places newItem at the end of the existing items array */
	public void addItem(MenuItem newItem) {

		MenuItem[] newArray = new MenuItem[items.length + 1];

		for (int i = 0; i < items.length; i++) {
			newArray[i] = items[i];
		}

		newArray[items.length] = newItem;

		items = newArray;
	}

	/** Displays the menu on screen if hidden is false */
	public void display(Graphics g) {

		if (!hidden) {

			g.setColor(color);
			g.setFont(font);

			for (int i = 0; i < items.length; i++) {
				g.drawString(items[i].getText(), x, (i + 1) * y);
			}
		}
	}

	public Color getColor() {

		return color;
	}

	public void setColor(Color color) {

		this.color = color;
	}

	public void hide() {

		hidden = true;
	}

	public void show() {

		hidden = false;
	}

	public void toggle() {

		hidden = !hidden;
	}
}