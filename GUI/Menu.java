public class Menu {

	private Font font = new Font("Courier", Font.PLAIN, 20);
	private Color color;
	private MenuItem[] items = {};

	public Menu() {}
	
	public void setFont(String font, int flag, int size, Color color) {

		this.font = new Font(font, flag, size);
		this.color = color;
	}

	public void addItem(MenuItem item) {

		for (int i = 0; i < )
		itemsNew = new MenuItem[items.length];

	}

	public void display(Graphics g) {

		g.setColor(color);
		g.setFont(font);

		for (int i = 0; i < items.length; i++) {
			g.drawString(items[i].getText(), 20, (i + 1) * 35);
		}
	}

	public Color getColor() {

		return color;
	}

	public void setColor(Color color) {

		this.color = color;
	}
}