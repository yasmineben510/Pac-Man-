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
	Logic signal1;
	Logic signal2;
	
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
		super(area, orientation, position);
		this.signal1 = signal;
		this.signal2 = signal;
		if (orientation == Orientation.DOWN || orientation == Orientation.UP ) 
	        sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,0,64,64));
		 else 
			 sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,64,64,64));
		
	}
	
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal1,Logic signal2) {
		
		this(area, orientation, position, signal1);
		this.signal2 = signal2;
				
	}


	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (signal1.isOn() && signal2.isOn()) return false;
		else return true;
		
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
		if (signal1.isOff() || signal2.isOff()) sprite.draw(canvas);
	}

}
