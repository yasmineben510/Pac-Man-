package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.window.Window;

public class Tuto2Behavior extends AreaBehavior {

	public Tuto2Behavior(Window window, String name) {
		super(window, name);
		Tuto2Cell cell;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
				cell = new Tuto2Cell(x,y,Tuto2CellType.toType(getRGB(getHeight()-1-y, x)));
				setCell(x, y, cell);
			}
		}
	}
	
	public enum Tuto2CellType {
		
		NULL(0, false),
		WALL(-16777216, false), // #000000 RGB code of black
		IMPASSABLE(-8750470, false), // #7A7A7A, RGB color of gray 
		INTERACT(-256, true), // #FFFF00, RGB color of yellow 
		DOOR(-195580, true), // #FD0404, RGB color of red
		WALKABLE(-1, true),; // #FFFFFF, RGB color of white
		final int type;
		final boolean isWalkable;

		private Tuto2CellType (int type, boolean isWalkable) { 
			this.type = type;
		    this.isWalkable = isWalkable; 
		}
		
		public static Tuto2CellType toType(int type){
			for(Tuto2CellType ict : Tuto2CellType.values()){
				if(ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NULL;
		}
	}

	
	public class Tuto2Cell extends AreaBehavior.Cell{
		
		Tuto2CellType cell;
		
		Tuto2Cell(int x, int y, Tuto2CellType cell){
			super(x,y);
			this.cell=cell;
		}

		@Override
		public boolean isCellInteractable() {
			return true;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			return cell.isWalkable ;
		}
	}

}


