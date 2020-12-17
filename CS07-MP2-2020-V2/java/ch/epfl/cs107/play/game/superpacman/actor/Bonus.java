package ch.epfl.cs107.play.game.superpacman.actor;



import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Bonus extends AutomaticallyCollectableAreaEntity{
	
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 6;
    /// Timer for which the SuperPacmanPlayer will be invulnerable if it collects a bonus
    private final static float BONUS_TIMER = 30;
    private Sprite[] sprites;
	private Animation animation;
	
	/**
	 * Constructor for Bonus
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
	 */
	public Bonus(Area area,DiscreteCoordinates position) {
		super(area,Orientation.DOWN,position);
		sprites = RPGSprite.extractSprites("superpacman/coin", 4, 1, 1,this , 16, 16);
		animation = new Animation(ANIMATION_DURATION/2,sprites);
		for (int i=0; i<4; ++i) {
			sprites[i].setDepth(-100.f);
		}
	}
	
	/**
	 * @return the bonusTimer
	 */
	public static float getBonusTimer() {
		return BONUS_TIMER;
	}
	
	/// Bonus implements Actor
	
	@Override
	public void update(float deltaTime) {
		animation.update(deltaTime);
		super.update(deltaTime);
	}

	/// Bonus implements Graphics
	
	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas);
	}

	/// Bonus implements Interactable
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}
}
