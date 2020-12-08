package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class CollectableAreaEntity extends AreaEntity {

	
	
	// indicate if the collectable is collected 
	private boolean isCollected = false;
	
	
	
	public CollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		
	}

	public boolean isCollected() {
		return isCollected;
	}
	
	public void setIsCollected(boolean isCollected) {
		this.isCollected=isCollected;
	}
	
	
	public abstract void collect();
	

	@Override
	public boolean isCellInteractable() {
		return true;
	}



	
}
