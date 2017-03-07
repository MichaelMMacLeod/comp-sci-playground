import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;

public class RockDropper extends Critter {
    @Override
    public void makeMove(Location l) {
    	Location current = getLocation();

    	super.makeMove(l);

    	new Rock(getColor()).putSelfInGrid(getGrid(), current);
    }
}