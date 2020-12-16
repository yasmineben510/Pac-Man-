package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.List;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends SuperGhost {
	
	/// Minimum Distance that must be between pinky and the SuperPacmanPlayer.
    private final static int MIN_AFRAID_DISTANCE =5;
    /// Maximum attempts allowed to find a random path.
    private final static int MAX_RANDOM_ATTEMPT =200;
    

    /**
     * Constructor for Pinky
     */
	public Pinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.pinky");
	}
	
	@Override
	protected void generatePath() {
		
		/// generates a random path if pinky doesn't know the SuperPacmanPlayer.
		if(getSuperPacman()==null ) {
			super.generatePath();
		}
		/// generates a random path if pinky is afraid and knows the SuperPacmanPlayer. 
		/// The path can not be generated if the maximum attempts allowed to find it is surpassed.
		/// The path has to be further than MIN_AFRAID_DISTANCE from the SuperPacmanPlayer.
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