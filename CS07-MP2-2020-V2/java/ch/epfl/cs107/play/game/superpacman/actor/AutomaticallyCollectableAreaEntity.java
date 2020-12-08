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

	private boolean isWalkedOn = false;
	
	
	public AutomaticallyCollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	public void collect() {
		this.getOwnerArea().unregisterActor(this);
		this.setIsCollected(true);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	/**
	 * @return the isWalkedOn
	 */
	public boolean isWalkedOn() {
		return isWalkedOn;
	}

	/**
	 * @param isWalkedOn the isWalkedOn to set
	 */
	public void setIsWalkedOn(boolean isWalkedOn) {
		this.isWalkedOn = isWalkedOn;
	}

	/**
	 * returns the score value of the collectable entity
	 * Note: needs to be override
	 */
	public int getPoints() {
		return 0;
	}

}
