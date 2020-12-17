package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Diamond extends AutomaticallyCollectableAreaEntity{
	
	///Points added if collected
	final int POINTS = 10;
	
    private Sprite sprite;
	
    /**
     * Constructor for Diamond
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
	public Diamond(Area area, DiscreteCoordinates position) {
		super(area,Orientation.DOWN,position);
		sprite = new Sprite("superpacman/diamond", 1,1, this);
		sprite.setDepth(-100f);
	}

	@Override
	public int getPoints() {
		return POINTS;
	}
	
	/// implements Graphics
	
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	
}
