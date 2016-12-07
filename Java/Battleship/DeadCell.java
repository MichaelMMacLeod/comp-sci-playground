import java.awt.Color;

public class DeadCell extends Piece {

	public final int ID; // id which corresponds with the destroyed ship part

	public DeadCell(int id) {

		this.ID = id;
		color = Color.RED;
	}
}