package ch.epfl.cs107.play.game.superpacman.area;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutos.Tuto2Behavior.Tuto2Cell;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	
	/**
	 * Default SuperPacmanBehavior Constructor  
	 * @param window (Window): graphic context, not null
	 * @param name (String): name of the behavior image, not null
	 */
	public SuperPacmanBehavior(Window window, String name) {
		super(window, name);
		SuperPacmanCell cell;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
				cell = new SuperPacmanCell(x,y,SuperPacmanCellType.toType(getRGB(getHeight()-1-y, x)));
				setCell(x, y, cell);
			}
	  }
	}
	
	/**
	 * Enum type SuperPacmanCellType
	 * Associate to each cell of the area its type depending on its color
	 */
	public enum SuperPacmanCellType {
		
		NONE(0),  // never used as real content
		WALL (-16777216), //black
		FREE_WITH_DIAMOND(-1),  //white
		FREE_WITH_BLINKY (-65536), //red
		FREE_WITH_PINKY (-157237), //pink
		FREE_WITH_INKY (-16724737),  //cyan
		FREE_WITH_CHERRY (-36752),  //light red
		FREE_WITH_BONUS (-16478723), //light blue
		FREE_EMPTY (-6118750); // sort of gray
		
		final int type;
		/**
		 * Default constructor for the enum type SuperPacmanCellType
		 * @param type (int) the RGB color of the cell that will be associated to a SuperPacmanCellType
		 */
		SuperPacmanCellType(int type){
			this.type = type;
		}
		
		public static SuperPacmanCellType toType(int type){
			for(SuperPacmanCellType ict : SuperPacmanCellType.values()){
				if(ict.type == type)
				return ict;
			}
			return NONE;
	    }
	}
	
<<<<<<< Updated upstream
	public void registerActors(Area area) {
		SuperPacmanCellType type;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
			  if (getCellType(x,y)==SuperPacmanCellType.WALL) {
				  
			  }
=======
	public void registerActors(Area area) {  
		
		SuperPacmanCellType ict;
		
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
				getCell(x, y);
				if(ict.type == -16777216) {
					
				}
>>>>>>> Stashed changes
			}
		}
	}
	
	/**
	 * Contruct the neighborhood of an actor
	 * Indicate if it contains an actor of the same type in his neighborhood
	 * @param x (int): x-coordinate of this cell
     * @param y (int): y-coordinate of this cell
	 * @return (boolean[][]): matrix 3x3 
	 */
	private boolean[][] neighborhood(int x, int y){
	    
		boolean[] [] neighborhood = new boolean[3] [3];
		
		DiscreteCoordinates cellPosition = new DiscreteCoordinates(x, y);

		List<DiscreteCoordinates> neighbors = cellPosition.getNeighbours();
		if (getCellType() == SuperPacmanCellType.WALL) {
		     //cells near the position x, y (true = a wall, false = not a wall) (default : false)
		    
		}
		return neighborhood;
	}
		
	
	
	
	/**
	 * Getter for the type of a SuperPacmanCell cell
	 * @param x (int): x-coordinate of this cell
     * @param y (int): y-coordinate of this cell
	 * @return (SuperPacmanCellType ): The type of the SuperPacmanCell cell
	 */
	private SuperPacmanCellType getCellType(int x, int y) {
		return ((SuperPacmanCell)getCell(x,y)).type;
	}
	
	/**
	 * Class SuperPacmanCell extends AreaBehavior.Cell
	 * Instance for the SuperPacmanCellType of the SuperPacmanBehavior
	 */
	public class SuperPacmanCell extends AreaBehavior.Cell { 
		
		private SuperPacmanCellType type;
		
		/**
		 *  Constructor for SuperPacmanCell
		 *  @param x (int) : x coordinate of the cell
		 *  @param y (int) : y coordinate of the cell
		 *  @param type (SuperPacmanCellType): the enum type of the cell
		*/
		protected SuperPacmanCell(int x, int y, SuperPacmanCellType type) {
			super(x, y);
			this.type=type;
		}
        

		@Override
		public boolean canEnter(Interactable entity) { 
			return !this.hasNonTraversableContent();
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return false;
		}

		

		
	}
}