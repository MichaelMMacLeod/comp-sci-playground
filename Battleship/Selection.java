public class Selection {

	private int row, column, rows, columns;

	public Selection(int column, int row, int columns, int rows) {
		this.column = column;
		this.row = row;
		this.columns = columns - 1;
		this.rows = rows - 1;
	}

	public int getRow() { return row; }
	public int getColumn() { return column; }

	public void moveTo(int column, int row) {
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