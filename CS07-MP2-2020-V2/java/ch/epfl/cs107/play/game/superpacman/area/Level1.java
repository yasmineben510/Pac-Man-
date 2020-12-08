package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;

import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Level1 extends SuperPacmanArea {
	
	private DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
	
	/// SuperPacman's initial position
	
	
	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}

	public String getTitle() {
		return "superpacman/Level1";
	}

	protected void createArea() {
    	super.createArea();
    	DiscreteCoordinates position = new DiscreteCoordinates(14,0);
    	DiscreteCoordinates otherCells = new DiscreteCoordinates(15,0);
    	DiscreteCoordinates pos = new DiscreteCoordinates(15,29);
    	Door door = new Door("superpacman/Level2",pos,Logic.TRUE,this,Orientation.DOWN,position,otherCells);
    	registerActor(door);

    	//registerActor(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(14, 3),this));
    	//registerActor(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(15, 3),this));
    	
	}
	


}
