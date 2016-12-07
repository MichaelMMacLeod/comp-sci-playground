import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Menu {

	private Font font;
	private Color color;
	private int size;
	private MenuItem[] items = {};
	private boolean hidden = true;
	private boolean vertical;
	private int x;
	private int y;

	public Menu(int x, int y, boolean vertical) {

		this.x = x;
		this.y = y;
		this.vertical = vertical;

		size = 20;
		font = new Font("Courier", Font.PLAIN, size);
		color = Color.BLACK;
	}
	
	public void setFont(String font, int flag, int size, Color color) {

		this.font = new Font(font, flag, size);
		this.color = color;
		this.size = size;
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

			int spacing = 0;

			for (int i = 0; i < items.length; i++) {

				if (vertical) {
					g.drawString(items[i].getText(), x, getTextSize() * i + y);
				} else {
					g.drawString(items[i].getText(), spacing + x, y);
					spacing += g.getFontMetrics().stringWidth(items[i].getText());
				}
			}
		}
	}


	public Font getFont() {

		return font;
	}

	public int getTextSize() {

		return size;
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