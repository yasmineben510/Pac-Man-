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

import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Ghost extends MovableAreaEntity implements Interactor{
	
	private final int GHOST_SCORE = 500;  
	private final int ANIMATION_DURATION_GHOST = 18;
	private final int RADIUS = 5;
	
	private Sprite[][] sprites;
	private Animation[] animations;
	private Animation currentAnimation;
	private Sprite[] spriteAfraid;
	private Animation animationAfraid;
	private GhostHandler handler;
	private DiscreteCoordinates shelter;
	private SuperPacmanPlayer SuperPacman;
	private boolean isAfraid;
	private boolean isFrozen;
	
	///The timer (set if a bonus is collected by the player) is common to every ghost. If begun, all the ghosts must be scared, if finished, they come back to their normal state.
	private static float timer;
	private static float frozenTimer;
	private boolean isStateChanged;
	
	/**
	 * Constructor of Ghost
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinates): Initial position of the entity. Not null
	 * @param shelter (DiscreteCoordinates) : Ghost's shelter. Not null.
	 * @param spriteName (String) : Ghost's name.
	 */

	public Ghost(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, Orientation.LEFT, position);
		
		handler = new GhostHandler();
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

	
	/**
	 * sets the timer
	 * @param (float) bonusTimer : the new value of the timer
	 */
	public static void setTimer(float bonusTimer){   
	    timer = bonusTimer;
	}
	
	/**
	 * @return the timer 
	 */
	public static float getTimer() {
		return timer;
	}

	/**
	 * @return the GHOST_SCORE
	 */
	protected int getGHOST_SCORE() {
		return GHOST_SCORE;
	}
	
	/**
	 * @return the ghost's shelter
	 */
	protected DiscreteCoordinates getShelter() {
		return shelter;
	}
	
	/**
	 * @return the SuperPacmanPlayer, may be null
	 */
	protected SuperPacmanPlayer getSuperPacman() {
		return SuperPacman;
	}

	/**
	 * sets the SuperPacmanPlayer
	 * @param (SuperPacmanPlayer) superPacman : initialized once it is seen, null if forgotten
	 */
	protected void setSuperPacman(SuperPacmanPlayer superPacman) {
		if(superPacman != this.SuperPacman) {
			isStateChanged = true;
		}
		this.SuperPacman = superPacman;
	}

	/**
	 * sets the ghost's state 
	 * @param (boolean) afraid : true if SuperPacmanPlayer is invulnerable, false if not
	 */
	public void setIsAfraid(boolean afraid) {
		if(isAfraid != afraid) {
			isStateChanged = true;
		}
		isAfraid = afraid;
	}
	
	/**
	 * @return (boolean) true if the ghost is afraid, false if not
	 */
	protected boolean isAfraid() {
		return isAfraid;
	}
	
	/**
	 * @return (boolean) true, if the ghost's state has changed, false if not
	 */
	protected boolean isStateChanged() {
		return isStateChanged;
	}

	/**
	 * Sets the ghost's state
	 * @param (boolean) isStateChanged : true if the state has changed, false if not 
	 */
	protected void setStateChanged(boolean isStateChanged) {
		this.isStateChanged = isStateChanged;
	}
	
	/**
	 * @return (int) the ghost's animation duration
	 */
	protected int getAnimationDurationGhost() {
		return ANIMATION_DURATION_GHOST;
	}
	
	/**
	 * @return the next orientation of the ghost
	 */
	protected Orientation getNextOrientation() {
		int randomInt = RandomGenerator.getInstance().nextInt(4);
		return Orientation.fromInt(randomInt);
	}
	
	/**
	 * Unregister the ghost from his current position and register him back to his shelter
	 */
	public void resetGhostPosition() {
		isStateChanged=true;
		getOwnerArea().leaveAreaCells(this , getEnteredCells());
		setCurrentPosition(shelter.toVector());
		getOwnerArea().enterAreaCells(this , getCurrentCells());
		resetMotion();
	}
	
	/**
	 * Reset the ghost's position and forgets the SuperPacmanPlayer
	 */
	protected void isEaten() {
		resetGhostPosition();
		setSuperPacman(null);
	}
	
	
	/**
	 * Getter for the boolean isFrozen
	 * @return isFrozen (boolean): true if the Ghost is frozen, false if not 
	 */
	public boolean isFrozen() {
		return isFrozen;
	}


	/**
	 * setter for the boolean isFrozen
	 * @param isFrozen (boolean): true if the Ghost is frozen, false if not 
	 */
	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}


	/**
	 * Getter for frozenTimer
	 * @return the frozenTimer
	 */
	public static float getFrozenTimer() {
		return frozenTimer;
	}


	/**
	 * setter for the frozenTimer
	 * @param frozenTimer (float): value for the frozenTimer to set
	 */
	public static void setFrozenTimer(float frozenTimer) {
		Ghost.frozenTimer = frozenTimer;
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

	/// implements Actor
	
	@Override
	public void update(float deltaTime) {
		
		if(isFrozen) {
			frozenTimer-=deltaTime;
			if(frozenTimer<=0) {
				setFrozen(false);
			}
		}
		
		
		if(isAfraid) {
			timer -= deltaTime;
			currentAnimation = animationAfraid;
		} 
		
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
	
	/// implements Graphics
	
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

	
	/**
	 * Nested class of Ghost
	 * Handles the interactions between the Ghost and the other Interactables 
	 */
	private class GhostHandler implements SuperPacmanInteractionVisitor {
	   /**
	    * The ghost remembers the SuperPacmanPlayer if he sees him.
	    */ 
		
		/**
	     * Simulates an interaction between Ghost and the SuperPacmanPlayer
	     * The ghost remembers the SuperPacmanPlayer if he sees him.
	     * @param player (SuperPacmanPlayer), not null
	     */
		public void interactWith(SuperPacmanPlayer player){
		  setSuperPacman(player);
		 }
	}
}