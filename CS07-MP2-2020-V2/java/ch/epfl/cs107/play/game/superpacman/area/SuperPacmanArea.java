package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area{

	private SuperPacmanBehavior behavior;
	
    /**
     * Default getter for the final DiscreteCoordinates of the sub_areas
     * @return null
     * Note: Needs to be override
     */
	 public abstract DiscreteCoordinates getPlayerSpawnPosition();
	
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

}
