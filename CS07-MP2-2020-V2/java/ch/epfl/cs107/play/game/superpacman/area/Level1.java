package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends SuperPacmanArea {
	
	public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	
	/// SuperPacman's initial position
	
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}

	public String getTitle() {
		return "Superpacman/Level1";
	}

	protected void createArea() {
    	super.createArea();
		// Base
        registerActor(new Background(this));
        registerActor(new Foreground(this));
	}

}
