import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

public class Main {
	public static void main(String... args) {
		ActorWorld world = new ActorWorld();
		Critter alice = new Critter();
		ImmortalBug immortalBug = new ImmortalBug();

		world.add(new Location(3, 3), alice);
		world.add(new Location(5, 5), immortalBug);

		world.show();
	}
}