package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Inky extends SuperGhost {
	
	//Distance from the shelter the inky can't surpass when not scared
	private final static int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	//Distance from the shelter the inky can't surpass when scared
	private final static int MAX_DISTANCE_WHEN_SCARED = 5;

	/**
	 * Constructor for Inky
	 */
	public Inky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.inky");
	}

	@Override
	protected void generatePath() {
		/// creates a path when not scared and doesn't know the SuperPacmanPlayer
		/// The path can not be further than MAX_DISTANCE_WHEN_NOT_SCARED from the shelter.
		if (!isAfraid() && getSuperPacman()==null ) {
		   do {
		       super.generatePath();
		   } while(DiscreteCoordinates.distanceBetween(getShelter(), getTargetPos())>MAX_DISTANCE_WHEN_NOT_SCARED);
	    }
		/// creates a path when scared no matter if the ghost knows the SuperPacmanPlayer or not.
		/// The path can not be further than MAX_DISTANCE_WHEN_SCARED from the shelter.
		if(isAfraid()) {
			do {
				super.generatePath();
		    } while(DiscreteCoordinates.distanceBetween(getShelter(), getTargetPos())>MAX_DISTANCE_WHEN_SCARED);
		}						
	}
}