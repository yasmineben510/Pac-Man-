package ch.epfl.cs107.play.game.superpacman.area;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area implements Logic{

	private SuperPacmanBehavior behavior;
	
    /**
     * Default getter for the final DiscreteCoordinates of the sub_areas
     * @return null
     * Note: Needs to be override
     */
	public abstract DiscreteCoordinates getPlayerSpawnPosition();
	
	public void resetAllGhostsPosition() {
		behavior.resetAllGhostsPosition();
	}
	
	/** 
	 * @return collected (boolean): true if all the diamonds are collected, false if not 
	 */
	protected boolean areDiamondsCollected() {
		List<Diamond> diamonds = behavior.getDiamonds();
		boolean collected = true; 
		for (int i=0; i<diamonds.size(); ++i) {
			if(!(diamonds.get(i)).isCollected()) {
				collected = false;
			}
		}
		return collected;
	}
	 
	 
	 /**
     * Create the area by adding it all actors
     * called by begin method
     * Note it set the Behavior as needed !
     */
    protected void createArea() {
    	behavior.registerActors(this);
    }
	
    /// SuperPacmanArea extends Area
    
	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public float getCameraScaleFactor() {
		return 20.f;
	}
	
	/// SuperPacmanArea implements playable
	
    @Override
	public boolean begin(Window window, FileSystem fileSystem) {
	    if (super.begin(window, fileSystem)) {
	       // Set the behavior map
	       behavior = new SuperPacmanBehavior(window, getTitle());
	       setBehavior(behavior);
	       createArea();
	       return true;
	    }
	    return false;
	}
    
	@Override
	public boolean isOn() {
		return areDiamondsCollected();
	}

	@Override
	public boolean isOff() {
		return !areDiamondsCollected();
	}

	@Override
	public float getIntensity() {
		if (this.isOff()) return 0;
		else return 1;
	}
}