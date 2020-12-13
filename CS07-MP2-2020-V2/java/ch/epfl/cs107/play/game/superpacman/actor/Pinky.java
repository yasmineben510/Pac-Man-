package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends SuperGhost {
	
    final static int MIN_AFRAID_DISTANCE =5;
    final static int MAX_RANDOM_ATTEMPT =200;


	public Pinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.pinky");
	}

	@Override
	protected Orientation getNextOrientation() {
		 
		Orientation nextOrientation = null;
		
		// ne connait pas player (effraye ou pas)
		if(getSuperPacman()==null ) {
			nextOrientation = super.getNextOrientation();
		}
		
		// effraye et connait player
		if(isAfraid() && getSuperPacman()!=null ) {
			int attempts = 0; 
			do {
				nextOrientation = super.getNextOrientation();
				++ attempts;
			    }
			    while(DiscreteCoordinates.distanceBetween(getSuperPacman().getCurrentCells().get(0), getTargetPos())<MIN_AFRAID_DISTANCE && attempts<MAX_RANDOM_ATTEMPT);
		    }
		
		// non effraye et connait player (suit player)
		
		if(!isAfraid() && getSuperPacman()!=null ) {
			nextOrientation = followPlayer();
		}
		
		return nextOrientation;
	}
}
