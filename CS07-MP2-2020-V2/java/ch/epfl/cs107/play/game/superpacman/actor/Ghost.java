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

import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Ghost extends MovableAreaEntity implements Interactor{
	
	GhostHandler handler = new GhostHandler();
	
	
	private final int GHOST_SCORE = 500;  
	private final int ANIMATION_DURATION_GHOST = 18;
	private final int RADIUS = 5;
	private Sprite[][] sprites;
	private Animation[] animations;
	private Animation currentAnimation;
	private Sprite[] spriteAfraid;
	private Animation animationAfraid;
	
	private DiscreteCoordinates shelter;
	private SuperPacmanPlayer SuperPacman;
	private static boolean isAfraid;
	private static float timer;
	private boolean isStateChanged;

	public Ghost(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, Orientation.LEFT, position);
		
		SuperPacman=null;
		isAfraid=false;
		isStateChanged= false;
		
		this.shelter=shelter;
				
		sprites = RPGSprite.extractSprites(spriteName, 2, 1, 1, this, 16, 16, new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
        animations = Animation.createAnimations(ANIMATION_DURATION_GHOST, sprites);
        
        spriteAfraid = RPGSprite.extractSprites("superpacman/ghost.afraid",2, 1, 1, this, 16, 16);
		animationAfraid = new Animation(ANIMATION_DURATION_GHOST,spriteAfraid);
        
		currentAnimation = animations[Orientation.LEFT.ordinal()];
    }

	
	
	public static void setTimer(int bonusTimer){   
	    timer=bonusTimer;
	}
	
	public static float getTimer() {
		return timer;
	}

	/**
	 * @return the gHOST_SCORE
	 */
	protected int getGHOST_SCORE() {
		return GHOST_SCORE;
	}
	
	
	protected DiscreteCoordinates getShelter() {
		return shelter;
	}

	protected SuperPacmanPlayer getSuperPacman() {
		return SuperPacman;
	}

	protected void setSuperPacman(SuperPacmanPlayer superPacman) {
		if(superPacman != this.SuperPacman) {
			isStateChanged = true;
		}
		this.SuperPacman = superPacman;
		
	}

	public void setIsAfraid(boolean afraid) {
		if(isAfraid != afraid) {
			isStateChanged = true;
		}
		isAfraid = afraid;
		System.out.println(isStateChanged);
	}
	
	protected static boolean isAfraid() {
		return isAfraid;
	}
	
	protected boolean isStateChanged() {
		return isStateChanged;
	}

	
	// setter maybe not necessary check later
	protected void setStateChanged(boolean isStateChanged) {
		this.isStateChanged = isStateChanged;
	}
	
	protected int getAnimationDurationGhost() {
		return  ANIMATION_DURATION_GHOST;
	}
	
	
	

	/**
	 * note: needs to be override
	 * @return the next orientation of the ghost
	 */
	protected abstract Orientation getNextOrientation();
	
	public void resetGhostPosition() {
		isStateChanged=true;
		getOwnerArea().leaveAreaCells(this , getEnteredCells());
		setCurrentPosition(shelter.toVector());
		getOwnerArea().enterAreaCells(this , getCurrentCells());
		resetMotion();
	}
	
	
	protected void isEaten() {
		resetGhostPosition();
		setSuperPacman(null);
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
		((SuperPacmanInteractionVisitor)v).interactWith(this);
		
	}

	/// extends Entity
	
	@Override
	public void update(float deltaTime) {
		

		
		if(isAfraid) {
			timer -= deltaTime;
			currentAnimation = animationAfraid;
		} 
		
		
		

		/*if (!isDisplacementOccurs()) {
            if(getOwnerArea().canEnterAreaCells(this, nextCells)) {
				  orientate(nextOrientation);
			    } 
            move(ANIMATION_DURATION_GHOST);
		}*/
		
		  if(!isAfraid) {
			if(getOrientation().equals(Orientation.LEFT)) {
	    	     currentAnimation = animations[Orientation.LEFT.ordinal()];
	        }
	    	if(getOrientation().equals(Orientation.RIGHT)) {
	    	     currentAnimation = animations[Orientation.RIGHT.ordinal()];
	        }
	    	if(getOrientation().equals(Orientation.UP)) {
	    	     currentAnimation = animations[Orientation.UP.ordinal()];
	        }
	    	if(getOrientation().equals(Orientation.DOWN)) {
	    	     currentAnimation = animations[Orientation.DOWN.ordinal()];
	        }
	      }
		  
	    if(isDisplacementOccurs()) {
			currentAnimation.update(deltaTime);
	    }
		
		else {
			 resetMotion();
		     currentAnimation.reset();
		}
	   super.update(deltaTime);	
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		currentAnimation.draw(canvas);
	}

	/// implements Interactor
	
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> neighbor = new ArrayList<DiscreteCoordinates>();
		int x = getCurrentMainCellCoordinates().x;
		int y = getCurrentMainCellCoordinates().y;
		for(int i = x - RADIUS; i <= x + RADIUS ; ++i) { 
			for (int j = y - RADIUS; j <= y + RADIUS; ++j) {
				if(i>=0 && i<getOwnerArea().getWidth() && j>=0 && j<getOwnerArea().getHeight()) {
				neighbor.add(new DiscreteCoordinates(i,j));
				}
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
		other.acceptInteraction(handler);
	}

	private class GhostHandler implements SuperPacmanInteractionVisitor {
		 public void interactWith(SuperPacmanPlayer player){
			 //for (DiscreteCoordinates viewCells : getFieldOfViewCells()) {
				// for(DiscreteCoordinates playerCells : player.getCurrentCells()) {
				//	 if(playerCells.equals(viewCells)) {
						 setSuperPacman(player);
				//	 }
				 //}
			 //}

		 }
	}
}

