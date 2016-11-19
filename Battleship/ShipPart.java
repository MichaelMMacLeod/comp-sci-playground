import java.awt.Color;

public class ShipPart extends Piece {

	public static final Color color = Color.GRAY;
	public final int ID; // ShipParts with the same id are part of the same ship

	public ShipPart(int id) {
		this.ID = id;
	}
}