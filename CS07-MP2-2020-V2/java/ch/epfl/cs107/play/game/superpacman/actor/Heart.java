package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Heart extends AutomaticallyCollectableAreaEntity{

	
		
	    private Sprite sprite;
		
	    /**
	     * Constructor for Cherry
	     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
	     */
		public Heart(Area area, DiscreteCoordinates position) {
			super(area,Orientation.DOWN,position);
			sprite = new Sprite("superpacman/heart", 1,1, this);
			sprite.setDepth(-100f);
		}

		/// implements Graphics
		
		@Override
		public void draw(Canvas canvas) {
			sprite.draw(canvas);
		}
		
		/// Heart implements Interactable
		
		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			((SuperPacmanInteractionVisitor)v).interactWith(this);
		}
}
