package ch.epfl.cs107.play.game.superpacman.area;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Level0 extends SuperPacmanArea {
	
	private final  DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
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
    	DiscreteCoordinates positionDoor = new DiscreteCoordinates(5,9);
    	DiscreteCoordinates otherCellsDoor = new DiscreteCoordinates(6,9);
    	DiscreteCoordinates posDoor = new DiscreteCoordinates(15,6);

    	Door door = new Door("superpacman/Level1",posDoor,Logic.TRUE,this,Orientation.UP,positionDoor,otherCellsDoor);
    	registerActor(door);
    	
    	Key key = new Key(this, new DiscreteCoordinates(3,4));
    	registerActor(key);
    	
    	Gate gate1= new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(5, 8),key);
    	registerActor(gate1);
    	
    	Gate gate2= new Gate(this,Orientation.LEFT,new DiscreteCoordinates(6, 8),key);
    	registerActor(gate2);
		
	}
	
	

}
