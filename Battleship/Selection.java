public class Selection {

	private int row, column, rows, columns;

	public Selection(int row, int column, int rows, int columns) {
		this.row = row;
		this.column = column;
		this.rows = rows - 1;
		this.columns = columns - 1;
	}

	public int getRow() { return row; }
	public int getColumn() { return column; }

	public void moveTo(int row, int column) {
		if (row < 0) {
			row = 0;
		}
		if (column < 0) {
			column = 0;
		}
		if (row > rows) {
			row = rows;
		}
		if (column > columns) {
			column = columns;
		}
		this.row = row;
		this.column = column;
	}
}