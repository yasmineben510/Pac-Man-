package ch.epfl.cs107.play.game.superpacman.actor;


import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 6;
	
	private SuperPacmanPlayerHandler handler;
    private SuperPacmanPlayerStatusGUI statusGUI;
    private Orientation desiredOrientation;
    private Animation[] animations;
    private Animation currentAnimation;
    private static int hp = 3;
    private int score;
    private boolean isVulnerable;
    private boolean isEaten;
   
	/**
	 * Constructor for SuperPacmanPlayer
	 * @param owner (area) : Owner area. Not null
	 * @param orientation (Orientation) : Initial orientation of the entity in the Area. Not null
	 * @param coordinates (DiscreteCoordinates) : Initial position of the entity in the Area. Not null
	 * @param spriteName (String) : Sprite's name.
	 */
	public SuperPacmanPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates) {
		super(owner, orientation, coordinates);
		
		desiredOrientation=orientation;
		statusGUI = new SuperPacmanPlayerStatusGUI(this.score,hp);
		handler = new SuperPacmanPlayerHandler();	
		score=0;
		isVulnerable=true;
		setEaten(false);
		
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 3, sprites);
        
        currentAnimation = animations[Orientation.RIGHT.ordinal()];
        
	}
	
	/**
	 * @return (boolean) : true if SuperPacmanPlayer is vulnerable, false if not.
	 */
	public boolean isVulnerable() {
		return isVulnerable;
	}
	/**
	 * sets the boolean isVulnerable to true
	 */
	public void resetVulnerable() {
		isVulnerable = true;
	}

	/**
	 * Simulates that SuperPacmanPlayer is eaten.
	 * SuperPacmanPlayer loses 1 hp.
	 */
	
	private void eatsPlayer() {
		hp-=1;
		getOwnerArea().leaveAreaCells(this , getEnteredCells());
		DiscreteCoordinates position = ((SuperPacmanArea)getOwnerArea()).getPlayerSpawnPosition();
		setCurrentPosition(position.toVector());
		getOwnerArea().enterAreaCells(this , getCurrentCells());
		resetMotion();
		setEaten(true);
	}
	
		
	 /**
	 * @return (boolean) true if SuperPacmanPlayer is eaten, false is not.
	 */
	public boolean isEaten() {
		return isEaten;
	}

	/**
	 * @param (boolean) : true if SuperPacmanPlayer is eaten, false is not.
	 */
	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}

	/// implements Actor
	
	@Override
	 public void update(float deltaTime) {
     	
		if (hp > 5) hp = 5;
		statusGUI.setHp(hp);
		statusGUI.setScore(score);
		
	    Keyboard keyboard= getOwnerArea().getKeyboard();
	    if (keyboard.get(Keyboard.LEFT).isDown()) desiredOrientation = Orientation.LEFT;
	    if (keyboard.get(Keyboard.UP).isDown()) desiredOrientation = Orientation.UP;
	    if (keyboard.get(Keyboard.RIGHT).isDown()) desiredOrientation = Orientation.RIGHT;
		if (keyboard.get(Keyboard.DOWN).isDown()) desiredOrientation = Orientation.DOWN;
		
		List <DiscreteCoordinates> nextCells = Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()));			
       
		if (!isDisplacementOccurs()) {
            if(getOwnerArea().canEnterAreaCells(this, nextCells)) {
				  orientate(desiredOrientation);
			    } 
            move(ANIMATION_DURATION/2);
 
        }
	    
	    if(isDisplacementOccurs()) {
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
	    	currentAnimation.update(deltaTime);
	    } 
	    
	    else {
            resetMotion();
	    	currentAnimation.reset();
	    }
	    super.update(deltaTime);
	    }
	
	public boolean isWeak() {
		if (hp<=0) {
			return true;
		}
		else return false;
	}
	    
    /// implements Graphics
	
	@Override
	public void draw(Canvas canvas) {
		currentAnimation.draw(canvas);
		statusGUI.draw(canvas);
	}
	
	/// implements Interactable

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
		return true;
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}
	
	/// implements Interactor

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
        }
	
	
	/**
	 * Nested class of SuperPacmanPlayer
	 * Handles the interactions between the SuperPacmanPlayer and the other Interactables 
	 */	
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
		/**
	     * Simulates an interaction between SuperPacmanPlayer and a Door
	     * @param door (Door), not null
	     */
	    public void interactWith(Door door){
	    	setIsPassingADoor(door);
	    }
	    
	    
	    /**
	     * Simulates an interaction between SuperPacmanPlayer and an AutomaticallyCollectableAreaEntity
	     * @param  collectable (AutomaticallyCollectableAreaEntity) : the AutomaticallyCollectableAreaEntity to collect, not null
	     */
	    public void interactWith(AutomaticallyCollectableAreaEntity collectable) {
	    	 collectable.setIsWalkedOn(true);

	    	 if(!collectable.isCollected() && collectable.isWalkedOn()) {
	       	    collectable.collect();
	       	    score += collectable.getPoints();
	         }
	    }
	    
	    /**
	     * Simulates an interaction between SuperPacmanPlayet and a Bonus
	     * @param bonus (Bonus) : the Bonus to collect, not null 
	     */
	    public void interactWith(Bonus bonus) {
	    	interactWith((AutomaticallyCollectableAreaEntity)bonus);
	    	if(bonus.isCollected()) {
	    		isVulnerable=false;
       	    	Ghost.setTimer(Bonus.getBonusTimer());
	    	}
	    	
	    }
	    
	    /**
	     * Simulate an interaction between SuperPacmanPlayer and a Ghost
	     * @param ghost (Ghost), not null
	     */
	    public void interactWith(Ghost ghost){

	    	if(isVulnerable) {
	    		eatsPlayer();
	    		ghost.resetGhostPosition();
	    		
	    	} else {
	    		score+=ghost.getGHOST_SCORE();
	    		ghost.isEaten();
	    	}
	    }
	    
	    
	    /**
	     * Simulate an interaction between SuperPacmanPlayer and a Heart
	     * @param heart (Heart), not null
	     */
	    public void interactWith(Heart heart) {
	    	interactWith((AutomaticallyCollectableAreaEntity)heart);

	    	if(heart.isCollected()) {
       	    	hp+=1;
	    	} 
	    	
	    }
	    
	    /**
	     * Simulate an interaction between SuperPacmanPlayer and a Flake
	     * @param flake (Flake), not null
	     */
	    public void interactWith(Flake flake) {
	    	interactWith((AutomaticallyCollectableAreaEntity)flake);
	    	if(flake.isCollected()) {
	    		((SuperPacmanArea)getOwnerArea()).frozenGhosts();
       	    	Ghost.setFrozenTimer(Flake.getFlakeTimer());
	    	}
	    	
	    }
	    
	}
}