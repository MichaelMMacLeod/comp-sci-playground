import java.awt.Color;

public class Board {

	private Piece[][] board;
	private int rows;
	private int columns;

	public Board(int rows, int columns) {

		board = new Piece[rows][columns];
		this.rows = rows;
		this.columns = columns;

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				board[row][column] = new EmptyCell();
			}
		}

		board[5][10] = new ShipPart(0);
	}

	/** Returns true if there are no ships on the board */
	public boolean isClear() {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (board[row][column] instanceof ShipPart) {
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
				if (board[row][column] instanceof ShipPart) {
					colors[row][column] = ShipPart.color;
				}
				if (board[row][column] instanceof EmptyCell) {
					colors[row][column] = EmptyCell.color;
				}
			}
		}
		return colors;
	}
}