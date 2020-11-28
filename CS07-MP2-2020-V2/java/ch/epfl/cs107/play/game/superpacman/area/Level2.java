package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {
	
	public static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
	/// SuperPacman's initial position
	
	public static DiscreteCoordinates getPLAYER_SPAWN_POSITION() {
		return PLAYER_SPAWN_POSITION;
	}
	
	public String getTitle() {
		return "Superpacman/Level2";
	}
	
    protected void createArea() {
		
	}

}
