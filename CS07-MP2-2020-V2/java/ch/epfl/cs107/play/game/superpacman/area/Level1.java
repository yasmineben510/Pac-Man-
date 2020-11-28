package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends SuperPacmanArea {
	
	public static DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	
	/// SuperPacman's initial position
	
	public String getTitle() {
		return "Superpacman/Level1";
	}
	
	public static DiscreteCoordinates getPLAYER_SPAWN_POSITION() {
		return PLAYER_SPAWN_POSITION;
	}

	public static void setPLAYER_SPAWN_POSITION(DiscreteCoordinates pLAYER_SPAWN_POSITION) {
		PLAYER_SPAWN_POSITION = pLAYER_SPAWN_POSITION;
	}

	protected void createArea() {
		
	}

}
