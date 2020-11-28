package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends SuperPacmanArea {
	
	public final static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
	/// SuperPacman's initial position
	
	public String getTitle() {
		return "Superpacman/Level0";
	}
	
    public static DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}

	protected void createArea() {
		
	}
	
	

}
