package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutos.Tuto2Behavior.Tuto2Cell;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	
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
	
	public void registerActors(Area area) {
		SuperPacmanCellType type;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
			   type = SuperPacmanCellType.toType(getRGB(getHeight()-1-y, x));
			}
		}
	}
	
	
	
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