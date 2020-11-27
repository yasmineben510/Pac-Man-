package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	
	public SuperPacmanBehavior(Window window, String name) {
		super(window, name);
	}
	
	public enum SuperPacmanCellType{
		
		NONE(0),  // never used as real content
		WALL ( -16777216), //black
		FREE_WITH_DIAMOND(-1),  //white
		FREE_WITH_BLINKY (-65536), //red
		FREE_WITH_PINKY ( -157237), //pink
		FREE_WITH_INKY ( -16724737),  //cyan
		FREE_WITH_CHERRY (-36752),  //light red
		FREE_WITH_BONUS ( -16478723), //light blue
		FREE_EMPTY ( -6118750); // sort of gray
		
		final int type;
		
		SuperPacmanCellType(int type){
			this.type = type;
		}
	}
	
	public class SuperPacmanCell extends AreaBehavior.Cell { 
		// les murs sont des acteurs
		// La seule chose qui peut entraver le déplacement sur la grille est un acteur
		
		protected SuperPacmanCell(int x, int y) {
			super(x, y);
			// TODO Auto-generated constructor stub
		}

		public boolean canEnter() { // true si la cellule contient un acteur non traversable
			return this.hasNonTraversableContent();
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

		@Override
		protected boolean canEnter(Interactable entity) {
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}
	
	public void registerActors(Area area) {
		
	}

}