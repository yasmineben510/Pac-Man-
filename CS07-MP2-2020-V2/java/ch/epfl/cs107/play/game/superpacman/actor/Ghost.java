package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Ghost extends MovableAreaEntity implements Interactor{
	
	private final int GHOST_SCORE = 500; // static or not ? 
	private final static int RADIUS = 5;
	private Sprite[][] sprites;
	private Animation[] animations;
	private Animation currentAnimation;
	private Sprite[] spriteAfraid;
	private Animation animationAfraid;
	private final int ANIMATION_DURATION_GHOST = 18;
	private SuperPacmanPlayer player;
	private boolean isAfraid;

	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, orientation, position);
		
		player = null;
		isAfraid = false;
		
		sprites = RPGSprite.extractSprites(spriteName, 4, 1, 1, this, 64, 64, new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION_GHOST / 3, sprites);
        currentAnimation = animations[Orientation.RIGHT.ordinal()];
        
        spriteAfraid = RPGSprite.extractSprites("superpacman/coin", 4, 1, 1, this, 16, 16);
		animationAfraid = new Animation(ANIMATION_DURATION_GHOST,spriteAfraid);
		for (int i=0; i<4; ++i) {
			spriteAfraid[i].setDepth(-100.f);
		}
        
    }
	
	public boolean isAfraid() {
		return isAfraid;
	}

	/// implements Interactable
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	/// implements Interactor
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> neighbor = new ArrayList<DiscreteCoordinates>();
		int x = getCurrentMainCellCoordinates().x;
		int y = getCurrentMainCellCoordinates().y;
		for(int i = x - RADIUS; i <= x + RADIUS ; ++i) { 
			for (int j = y - RADIUS; j <= y + RADIUS; ++i) {
				neighbor.add(new DiscreteCoordinates(i,j));
			}
		}
		return neighbor;
	}

	@Override
	public boolean wantsCellInteraction() {
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		return true;
	}

	@Override
	public void interactWith(Interactable other) {
		// TODO Auto-generated method stub
		
	}

}
