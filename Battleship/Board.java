import java.awt.Color;

public class Board {

	private Piece[][] board;
	private int[] shipLengths = {4, 6, 8};
	private int rows;
	private int columns;

	public Board(int columns, int rows) {

		board = new Piece[columns][rows];
		this.rows = rows;
		this.columns = columns;

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				board[column][row] = new EmptyCell();
			}
		}

		for (int i = 0; i < shipLengths.length; i++) {
			createShip(i + 1, shipLengths[i]);
		}
	}

	/**
	 * Returns true if any ship parts with the specified id are present on 
	 * the board
	 */
	private boolean shipIsAlive(int id) {

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {

				if (board[column][row] instanceof ShipPart
					&& ((ShipPart) board[column][row]).ID == id) {
					return true;
				}
			}
		}
		return false;
	}

	/** 
	 * Creates a ship in a random position and orientation 
	 */
	public void createShip(int id, int size) {

		// Find coordinates where the ship will not go out of bounds or overlap
		// other ships
		boolean horizontal = (int) (Math.random() * 2) % 2 == 0;

		int possibleLocations = 0;
		int[] possibleColumns = new int[possibleLocations];
		int[] possibleRows = new int[possibleLocations];

		for (int row = 0; row < rows; row++) {
			point: for (int column = 0; column < columns; column++) {

				for (int i = 0; i < size; i++) {

					if (horizontal 
						&& (column + size >= columns
						|| board[column + i][row] instanceof ShipPart)) {
						continue point;
					}

					if (!horizontal
						&& (row + size >= rows
						|| board[column][row + i] instanceof ShipPart)) {
						continue point;
					}
				}

				// too lazy to figure out how array lists work
				int[] possibleColumnsNew = new int[possibleLocations + 1];
				int[] possibleRowsNew = new int[possibleLocations + 1];
				for (int j = 0; j < possibleLocations; j++) {
					possibleColumnsNew[j] = possibleColumns[j];
					possibleRowsNew[j] = possibleRows[j];
				}
				possibleColumnsNew[possibleLocations] = column;
				possibleRowsNew[possibleLocations] = row;
				possibleColumns = possibleColumnsNew;
				possibleRows = possibleRowsNew;
				possibleLocations++;
			}
		}

		// select a random point from the list of acceptable points, then
		// create a ship starting at that coordinate
		int rand = (int) (Math.random() * possibleLocations);
		int column = possibleColumns[rand];
		int row = possibleRows[rand];

		for (int i = 0; i < size; i++) {

			if (horizontal) {
				board[column + i][row] = new ShipPart(id);
			}

			if (!horizontal) {
				board[column][row + i] = new ShipPart(id);
			}
		}
	}

	/** 
	 * Replaces some tiles with other tiles at a specified point
	 *
	 * ShipPart -> DeadCell (upon hitting a ship)
	 * EmptyCell -> MissCell (upon missing a ship)
	 */
	public void remove(int column, int row) {

		if (board[column][row] instanceof ShipPart) {
			board[column][row] = new DeadCell(((ShipPart) board[column][row]).ID);
			if (!shipIsAlive(((DeadCell) board[column][row]).ID)) {
				System.out.println("You sank #" + ((DeadCell) board[column][row]).ID);
			}
		}

		if (board[column][row] instanceof EmptyCell) {
			board[column][row] = new MissCell();
		}
	}

	/** 
	 * Returns true if there are no ship parts on the board 
	 */
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

	/** 
	 * Returns the color of each piece on the board 
	 */
	public Color[][] pieces() {

		Color[][] colors = new Color[rows][columns];

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				colors[column][row] = board[column][row].cellColor();
			}
		}

		return colors;
	}
}