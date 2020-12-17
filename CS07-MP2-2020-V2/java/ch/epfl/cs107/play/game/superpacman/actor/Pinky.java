package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends SuperGhost {
	
	/// Minimum Distance that must be between pinky and the SuperPacmanPlayer.
    private final static int MIN_AFRAID_DISTANCE =5;
    /// Maximum attempts allowed to find a random path.
    private final static int MAX_RANDOM_ATTEMPT =200;
    

    /**
     * Constructor for Pinky
     * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinates): Initial position of the entity. Not null
	 * @param shelter (DiscreteCoordinates) : Ghost's shelter. Not null.
	 */
	public Pinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.pinky");
	}
	
	@Override
	protected void generatePath() {
		
		// generates a random path if pinky doesn't know the SuperPacmanPlayer.
		
		if(getSuperPacman()==null ) {
			super.generatePath();
		}
		
		
		// generates a random path if pinky is afraid and knows the SuperPacmanPlayer. 
		
		// The target postition has to be further than MIN_AFRAID_DISTANCE from the SuperPacmanPlayer.
		// if the MAX_RANDOM_ATTEMPT is surpassed, it chooses a random path
		
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