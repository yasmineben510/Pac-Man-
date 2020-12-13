package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

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
	
	@Override
	public void update (float deltaTime) {
		Orientation nextOrientation = getNextOrientation();
		List <DiscreteCoordinates> nextCells = Collections.singletonList(getCurrentMainCellCoordinates().jump(nextOrientation.toVector()));			
		
		if (!isDisplacementOccurs()) {
            if(getOwnerArea().canEnterAreaCells(this, nextCells)) {
				  orientate(nextOrientation);
			    } 
            move(getAnimationDurationGhost());
		}
		
		super.update(deltaTime);
	}

}
