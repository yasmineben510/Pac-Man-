package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Blinky extends Ghost {

	public Blinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.blinky");
	}

	@Override
	protected Orientation getNextOrientation() {
		int randomInt = RandomGenerator.getInstance().nextInt(4);
		return Orientation.fromInt(randomInt);
	}

}
