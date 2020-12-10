package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {
	
	public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
	/// SuperPacman's initial position
	
	
	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
	}
	
	public String getTitle() {
		return "superpacman/Level2";
	}
	
    protected void createArea() {
    	super.createArea();
    	
    	Key key1 = new Key(this,new DiscreteCoordinates(3,16));
    	Key key2 = new Key(this,new DiscreteCoordinates(26,16));
    	Key key3 = new Key(this,new DiscreteCoordinates(2,8));
    	Key key4 = new Key(this,new DiscreteCoordinates(27,8));
    	
    	registerActor(key1);
    	registerActor(key2);
    	registerActor(key3);
    	registerActor(key4);
    	
    	createGates(key1,key2,key3,key4);

    	
	}
    
    
    private void createGates(Key key1, Key key2, Key key3, Key key4) {
		List<Gate> gates = new ArrayList<Gate>();
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(8, 14),key1));
		gates.add(new Gate(this,Orientation.DOWN,new DiscreteCoordinates(5, 12),key1));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(8, 10),key1));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(8, 8),key1));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(21, 14),key2));
		gates.add(new Gate(this,Orientation.DOWN,new DiscreteCoordinates(24, 12),key2));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(21, 10),key2));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(21, 8),key2));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(10, 2),key3, key4));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(19, 2),key3, key4));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(12, 8),key3, key4));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(17, 8),key3, key4));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(14, 3),this));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(15, 3),this));
		
		for (int i=0; i< gates.size(); ++i) {
	    	registerActor(gates.get(i));
		}

	}

}
