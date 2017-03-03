import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;

import java.util.Random;

/**
 * The elusive ImmortalBug (Immortalis Bugginius) is said to partially exist in
 * a separate dimension. It is a kind creature, willing to just go about its
 * own way. Against war and all other forms of violence, the ImortalBug does
 * not seek out enemies to kill. Instead, it has spent millions of years
 * honing the practice of self defense. When slain by a less civilized 
 * creature, the ImmortalBug is revived by its inter-dimensional counterpart.
 * Unfortunately, all creatures present in the 'reviving zone' will be slain 
 * due to the bug's rage.
 *
 * Scientists are unable to determine why the Bugginius rapidly changes color.
 * It's probably due to some side-affect of immortality.
 */

public class ImmortalBug extends Bug implements Runnable {
	private Grid<Actor> myGrid;
	private Location myLocation;

	private Random colorGen = new Random();

	public ImmortalBug() {
		Thread thread = new Thread(this);
		thread.setName("plz no hurt");
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if (getGrid() != null) {
				myGrid = getGrid();
				myLocation = getLocation();
			}
			
			// because immortal bugs also have to look nice
			setColor(
				new Color(
					colorGen.nextFloat(), 
					colorGen.nextFloat(), 
					colorGen.nextFloat()));
			try {
				putSelfInGrid(myGrid, myLocation);
			} catch(Exception e) { /* already in a grid */ }
		}
	}
}