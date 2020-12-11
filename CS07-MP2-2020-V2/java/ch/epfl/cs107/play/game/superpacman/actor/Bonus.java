package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bonus extends AutomaticallyCollectableAreaEntity{
	
    private final static int ANIMATION_DURATION = 6;
    private final static int BONUS_TIMER = 200;
    private Sprite[] sprites;
	Animation animation;
	
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
	public static int getBonusTimer() {
		return BONUS_TIMER;
	}
	
	
	/// Bonus extends Entity
	
	@Override
	public void update(float deltaTime) {
		animation.update(deltaTime);
		super.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas);
	}


	


}
