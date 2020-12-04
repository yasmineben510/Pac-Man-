package ch.epfl.cs107.play.game.superpacman.actor;


import java.awt.Color;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	
	SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();
	 
	private float hp;
	private TextGraphics message;
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 6;
    private Orientation desiredOrientation;
    private Animation[] animations;
    private Animation currentAnimation;
    protected static int life = 3;
    private int score = 0;
   
    
	/**
	 * Demo actor
	 * 
	 */
    
	public SuperPacmanPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
		super(owner, orientation, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		desiredOrientation=orientation;
		
		
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
        
        currentAnimation = animations[Orientation.RIGHT.ordinal()];
		resetMotion();
	}
	 
	 @Override
	 public void update(float deltaTime) {
		if (hp > 0) {
				hp -=deltaTime;
				message.setText(Integer.toString((int)hp));
	    }
     	if (hp < 0) hp = 0.f;
     	
     	
		if (life > 5) life = 5;
			
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
            move(ANIMATION_DURATION);
        }
	    
	    if(isDisplacementOccurs()) {
	    	if(desiredOrientation.equals(Orientation.LEFT)) {
	    	     currentAnimation = animations[Orientation.LEFT.ordinal()];
	        }
	    	if(desiredOrientation.equals(Orientation.RIGHT)) {
	    	     currentAnimation = animations[Orientation.RIGHT.ordinal()];
	        }
	    	if(desiredOrientation.equals(Orientation.UP)) {
	    	     currentAnimation = animations[Orientation.UP.ordinal()];
	        }
	    	if(desiredOrientation.equals(Orientation.DOWN)) {
	    	     currentAnimation = animations[Orientation.DOWN.ordinal()];
	        }
	    	currentAnimation.update(deltaTime);
	    } else resetMotion();
	    
	    super.update(deltaTime);
	    }
	    
    
	@Override
	public void draw(Canvas canvas) {
		message.draw(canvas);
		currentAnimation.draw(canvas);
	}

	public boolean isWeak() {
		return (hp <= 0.f);
	}

	public void strengthen() {
		hp = 10;
	}

	///SuperPacman implements Interactable

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

	}

}

