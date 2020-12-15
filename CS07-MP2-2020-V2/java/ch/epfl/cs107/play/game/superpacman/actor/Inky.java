package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public class Inky extends SuperGhost {
	
	private final static int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	private final static int MAX_DISTANCE_WHEN_SCARED = 5;


	public Inky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.inky");
	}

	@Override
	protected void generatePath() {
		
		// non effraye et ne connait pas player		
		if(!isAfraid() && getSuperPacman()==null ) {
		   do {
		       super.generatePath();
		   }
		   while(DiscreteCoordinates.distanceBetween(getShelter(), getTargetPos())>MAX_DISTANCE_WHEN_NOT_SCARED);
	    }
				
		// effraye et peu importe player
		if(isAfraid()) {
			do {
				super.generatePath();
		    }
		    while(DiscreteCoordinates.distanceBetween(getShelter(), getTargetPos())>MAX_DISTANCE_WHEN_SCARED);
		}
								
	}
	
	


}
