import java.awt.Color;

public class Board {

	private Piece[][] board;
	private int rows;
	private int columns;

	public Board(int rows, int columns) {

		board = new Piece[columns][rows];
		this.rows = rows;
		this.columns = columns;

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				board[column][row] = new EmptyCell();
			}
		}

		board[5][10] = new ShipPart(0);
	}

	public void remove(int row, int column) {
		if (board[column][row] instanceof ShipPart) {
			board[column][row] = new EmptyCell();
			System.out.println("Removed");
		}
	}

	/** Returns true if there are no ships on the board */
	public boolean isClear() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (board[column][row] instanceof ShipPart) {
					return false;
				}
			}
		}
		return true;
	}

	/** Returns the color of each piece on the board */
	public Color[][] pieces() {
		Color[][] colors = new Color[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (board[column][row] instanceof ShipPart) {
					colors[column][row] = ShipPart.color;
				}
				if (board[column][row] instanceof EmptyCell) {
					colors[column][row] = EmptyCell.color;
				}
			}
		}
		return colors;
	}
}