package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends AutomaticallyCollectableAreaEntity implements Logic{
	
	private Sprite sprite;

	/**
	 * Constructor for Key
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinates): Initial position of the entity. Not null
	 */
	public Key(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		sprite = new Sprite("superpacman/key", 1,1, this);
		sprite.setDepth(-100.f);
	}

	/// implements Graphics
	
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	/// implements signal - signal is On if key is collected

	@Override
	public boolean isOn() {
		if (this.isCollected()) return true;
		else return false;
	}


	@Override
	public boolean isOff() {
		if (this.isCollected()) return false;
		else return true;
	}


	@Override
	public float getIntensity() {
		if (isOn()) {
			return 1;
		}
		else return 0;
	}
}
