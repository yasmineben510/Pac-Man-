package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
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
    	
    	Key key1 = new Key()
    	
	}
    
    private void createGates() {
		List<Gate> gates = new ArrayList<Gate>();
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(14, 3),this));
		gates.add(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(15, 3),this));

		
	}

}
