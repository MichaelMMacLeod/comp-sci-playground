import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

import java.util.ArrayList;

import java.util.stream.Collectors;

public class RockEater extends Critter {
	@Override
	public ArrayList<Location> getMoveLocations() {
		return getGrid()
			   .getOccupiedLocations()
			   .stream()
			   .filter(l -> getGrid().get(l) instanceof Rock)
			   .collect(Collectors.toCollection(ArrayList::new));
	}
}