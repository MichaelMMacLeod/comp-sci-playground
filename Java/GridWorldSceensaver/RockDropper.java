import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;

public class RockDropper extends Critter {
    @Override
    public void act() {
        Location current = getLocation();

        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);

        try {
            getGrid().put(current, new Rock(getColor()));
        } catch (Exception e) {}
    }
}