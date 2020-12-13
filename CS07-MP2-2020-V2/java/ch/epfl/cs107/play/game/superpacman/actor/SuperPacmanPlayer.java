package ch.epfl.cs107.play.game.superpacman.actor;


import java.awt.Color;


import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
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
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	
	private SuperPacmanPlayerHandler handler;
    private SuperPacmanPlayerStatusGUI statusGUI;

	 
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 6;
    private Orientation desiredOrientation;
    private Animation[] animations;
    private Animation currentAnimation;
    private static int hp = 3;
    private int score;
    private boolean isVulnerable;
    private boolean isEaten;
   
	/**
	 * Demo actor
	 * 
	 */
	public SuperPacmanPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
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
	
	public boolean isVulnerable() {
		return isVulnerable;
	}
	
	public void resetVulnerable() {
		isVulnerable=true;
	}

	private void eatsPlayer() {
		hp-=1;
		getOwnerArea().leaveAreaCells(this , getEnteredCells());
		DiscreteCoordinates position = ((SuperPacmanArea)getOwnerArea()).getPlayerSpawnPosition();
		setCurrentPosition(position.toVector());
		getOwnerArea().enterAreaCells(this , getCurrentCells());
		resetMotion();
		setEaten(true);
	}
	
	
	/// extends Entity
	
	 /**
	 * @return the isEaten
	 */
	public boolean isEaten() {
		return isEaten;
	}

	/**
	 * @param isEaten the isEaten to set
	 */
	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}

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
	    
    
	@Override
	public void draw(Canvas canvas) {
		currentAnimation.draw(canvas);
		statusGUI.draw(canvas);
	}
	
	/// implements Interactable

	@Override
	public boolean takeCellSpace() {
		return true;
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
	
	
		
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
		/**
	     * Simulate an interaction between SuperPacmanPlayer and a Door
	     * @param door (Door), not null
	     */
	    public void interactWith(Door door){
	    	setIsPassingADoor(door);
	    }
	    
	    
	    /**
	     * Simulate an interaction between SuperPacmanPlayer and an AutomaticallyCollectableAreaEntity
	     * @param  collectable (AutomaticallyCollectableAreaEntity) : the AutomaticallyCollectableAreaEntity to collect, not null
	     */
	    public void interactWith(AutomaticallyCollectableAreaEntity collectable) {
	    	 collectable.setIsWalkedOn(true);

	    	 if(!collectable.isCollected() && collectable.isWalkedOn()) {
	       	    collectable.collect();
	       	    score += collectable.getPoints();
	         }
	    }
	    
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
	}
}