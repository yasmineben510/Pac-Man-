package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Gate extends AreaEntity {

	Sprite sprite;
	Logic signal;
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		if (orientation == Orientation.DOWN || orientation == Orientation.UP ) 
	        sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,0,64,2*64));
		 else 
			 sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,64,2*64,64));
		
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (signal.isOn()) return true;
		else return false;
		
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {

	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);

	}

}
