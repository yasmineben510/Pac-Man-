package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class SuperGhost extends Ghost {

	private DiscreteCoordinates targetPos;
	
	
	public SuperGhost(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, position, shelter, spriteName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Orientation getNextOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
