package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Blinky extends Ghost {

	/**
	 * Constructor of Blinky
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinates): Initial position of the entity. Not null
	 * @param shelter (DiscreteCoordinates) : Ghost's shelter. Not null.
	 */
	public Blinky(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, position, shelter, "superpacman/ghost.blinky");
	}

	///imlements Actor
	
	@Override
	public void update (float deltaTime) {
		Orientation nextOrientation = getNextOrientation();
		List <DiscreteCoordinates> nextCells = Collections.singletonList(getCurrentMainCellCoordinates().jump(nextOrientation.toVector()));			
		
		if (!isDisplacementOccurs() && !isFrozen()) {
            if(getOwnerArea().canEnterAreaCells(this, nextCells)) {
				  orientate(nextOrientation);
			    } 
            move(getAnimationDurationGhost());
		}
		super.update(deltaTime);
	}
}
