package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class AutomaticallyCollectableAreaEntity extends CollectableAreaEntity {

	
	private boolean isWalkedOn; // true if a player entered its cell 
	
	/**
     * AutomaticallyCollectableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
	public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isWalkedOn = false;
	}
	
	/**
	 * Simulates that the CollectableAreaEntity is collected
	 */
	public void collect() {
		this.getOwnerArea().unregisterActor(this);
		this.setIsCollected(true);
	}
	
	/**
	 * Getter for the boolean isWalkedOn
	 * @return isWalkedOn
	 */
	public boolean isWalkedOn() {
		return isWalkedOn;
	}

	/**
	 * @param isWalkedOn (boolean): the isWalkedOn to set
	 */
	public void setIsWalkedOn(boolean isWalkedOn) {
		this.isWalkedOn = isWalkedOn;
	}

	/**
	 * returns the score value of the collectible entity
	 * Note: needs to be override
	 */
	public int getPoints() {
		return 0;
	}
	
	/// implements Interactable

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	/// implements Graphics

	@Override
	public void draw(Canvas canvas) {
		
	}
}
