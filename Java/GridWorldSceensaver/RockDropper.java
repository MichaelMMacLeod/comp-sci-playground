import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

import java.util.ArrayList;

import java.awt.Color;

public class RockDropper extends Critter {
	public RockDropper(Color color) {
		setColor(color);
	}

    @Override
    public void makeMove(Location l) {
    	Location current = getLocation();

    	super.makeMove(l);

    	if (current != l) 
    		new Rock(getColor()).putSelfInGrid(getGrid(), current);
    }
}