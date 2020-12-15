package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.List;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends SuperGhost {
	
    private final static int MIN_AFRAID_DISTANCE =5;
    private final static int MAX_RANDOM_ATTEMPT =200;
    

	public Pinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.pinky");
	}
	
	
	@Override
	protected void generatePath() {
		
		
		// ne connait pas player (effraye ou pas)
		if(getSuperPacman()==null ) {
			super.generatePath();
		}
		
		// effraye et connait player
		if(isAfraid() && getSuperPacman()!=null ) {
			int attempts = 0; 
			do {
				super.generatePath();
				++ attempts;
			    }
			    while(DiscreteCoordinates.distanceBetween(getSuperPacman().getCurrentCells().get(0), getTargetPos())<MIN_AFRAID_DISTANCE && attempts<MAX_RANDOM_ATTEMPT);
		    }
	}
	
}
