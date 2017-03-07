import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

import java.awt.Color;

public class Main {
	public static void main(String... args) {
		ActorWorld world = new ActorWorld();

		RockEater re = new RockEater();
		re.setColor(Color.GREEN);

		world.add(new Location(5, 6), new RockDropper(Color.RED));
		world.add(new Location(5, 7), new RockDropper(Color.BLUE));
		world.add(new Location(5, 4), new RockDropper(Color.ORANGE));

		world.add(new Location(5, 8), re);

		world.show();
	}
}