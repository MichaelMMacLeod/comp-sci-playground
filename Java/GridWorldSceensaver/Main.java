import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

import java.awt.Color;

public class Main {
	public static void main(String... args) {
		ActorWorld world = new ActorWorld();
		RockDropper rd = new RockDropper();

		RockEater re = new RockEater();
		re.setColor(Color.GREEN);

		world.add(new Location(5, 5), rd);
		world.add(new Location(5, 8), re);

		world.show();
	}
}