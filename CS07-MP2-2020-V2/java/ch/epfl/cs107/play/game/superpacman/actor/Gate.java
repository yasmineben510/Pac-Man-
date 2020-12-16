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
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class Gate extends AreaEntity {

	private Sprite sprite;
	//Logic signal indicate if the gate is open
	private Logic isOpen;
	
	/**
	 * Constructor of Gate
	 * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
	 * @param isOpen (Logic): Logic signal linked to the gate. Not null.
	 */
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic isOpen) {
		super(area, orientation, position);
		
		this.isOpen = isOpen;
		
		((SuperPacmanArea)getOwnerArea()).setAreaGraphSignal(position,this.isOpen);
		
		if (orientation == Orientation.DOWN || orientation == Orientation.UP ) 
	        sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,0,64,64));
		 else 
			 sprite = new Sprite("superpacman/gate",  1, 1, this, new RegionOfInterest(0,64,64,64));
	}
	
	/**
	 * Constructor of a gate that needs two signals on to be opened.
	 */
	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal1,Logic signal2) {
		this(area, orientation, position, new And(signal1,signal2));	
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		if (isOpen.isOn()) return false;
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
		if (isOpen.isOff()) sprite.draw(canvas);
	}
}
