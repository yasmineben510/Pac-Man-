package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class extLevel1 extends SuperPacmanArea {
	/// SuperPacman's initial position
		private DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
		
		@Override
		public DiscreteCoordinates getPlayerSpawnPosition() {
			return PLAYER_SPAWN_POSITION;
		}

		/**
		 * @return (String) name of the area.
		 */
		public String getTitle() {
			return "superpacman/extLevel1";
		}

		@Override
		protected void createArea() {
			
	    	super.createArea();
	    	
	    	DiscreteCoordinates position = new DiscreteCoordinates(14,0);
	    	DiscreteCoordinates otherCells = new DiscreteCoordinates(15,0);
	    	DiscreteCoordinates pos = new DiscreteCoordinates(15,29);
	    	
	    	Door door = new Door("superpacman/extLevel2",pos,Logic.TRUE,this,Orientation.DOWN,position,otherCells);
	    	registerActor(door);

	    	registerActor(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(14, 3),this));
	    	
	    	registerActor(new Gate(this,Orientation.RIGHT,new DiscreteCoordinates(15, 3),this));
	    	
		}
}
