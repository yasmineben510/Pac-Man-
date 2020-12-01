package ch.epfl.cs107.play.game.superpacman.area;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Level0 extends SuperPacmanArea {
	
	public final  DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
	/// SuperPacman's initial position
	
	public String getTitle() {
		return "superpacman/Level0";
	}
	
    @Override
    public  DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}

	protected void createArea() {
    	super.createArea();
    	DiscreteCoordinates position = new DiscreteCoordinates(5,9);
    	DiscreteCoordinates otherCells = new DiscreteCoordinates(6,9);
    	DiscreteCoordinates pos = new DiscreteCoordinates(15,6);

    	Door door = new Door("superpacman/Level1",pos,Logic.TRUE,this,Orientation.UP,position,otherCells);
    	registerActor(door);
		
	}
	
	

}
