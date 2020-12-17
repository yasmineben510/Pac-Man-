package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Flake extends AutomaticallyCollectableAreaEntity{

	/// Timer for which the Ghosts will be freezed if the SuperPacmanPlayer collects a Flake
    private final static float FLAKE_TIMER = 30;
    
    private Sprite sprite;
    
    /**
	 * Constructor for Bonus
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
	 */
	public Flake(Area area,DiscreteCoordinates position) {
		super(area,Orientation.DOWN,position);
		sprite = new Sprite("superpacman/flake", 1,1, this);
		sprite.setDepth(-100f);
	}
	
	/**
	 * @return the flakeTimer
	 */
	public static float getFlakeTimer() {
		return FLAKE_TIMER;
	}
	

	/// Flake implements Graphics
	
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	/// Flake implements Interactable
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}
}
