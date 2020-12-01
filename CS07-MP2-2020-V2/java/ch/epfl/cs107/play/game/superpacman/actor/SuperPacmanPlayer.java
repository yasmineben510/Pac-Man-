package ch.epfl.cs107.play.game.superpacman.actor;


import java.awt.Color;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player{
	
	
	private float hp;
	private TextGraphics message;
	private Sprite sprite;
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 6;
    private Orientation desiredOrientation;
    
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
		sprite = new Sprite(spriteName, 1.f, 1.f,this);
		desiredOrientation=orientation;

		resetMotion();
	}
	 
	 @Override
	    public void update(float deltaTime) {
		 if (hp > 0) {
				hp -=deltaTime;
				message.setText(Integer.toString((int)hp));
			}
		 if (hp < 0) hp = 0.f;
			
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
	    super.update(deltaTime);
	     
	    }

	 
	 
	 
	    /**
	     * Orientate or Move this player in the given orientation if the given button is down
	     * @param orientation (Orientation): given orientation, not null
	     * @param b (Button): button corresponding to the given orientation, not null
	     */
	/*   private void moveOrientate(Orientation orientation, Button b){
	    
	        if(b.isDown()) {
	            if(getOrientation() == orientation) move(ANIMATION_DURATION);
	            else orientate(orientation);
	        }
	    }
	    
	    */
	    
	    
	    
	    /**
	     * Leave an area by unregister this player
	     */
	    public void leaveArea(){
	        getOwnerArea().unregisterActor(this);
	    }

	    /**
	     *
	     * @param area (Area): initial area, not null
	     * @param position (DiscreteCoordinates): initial position, not null
	     */
	    public void enterArea(Area area, DiscreteCoordinates position){
	        area.registerActor(this);
	        area.setViewCandidate(this);
	        setOwnerArea(area);
	        setCurrentPosition(position.toVector());
	        resetMotion();
	    }
    
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);	
		message.draw(canvas);
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
		// TODO Auto-generated method stub
		
	}
	
	
	@SuppressWarnings("unused")
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
	   
		/**
	     * Simulate and interaction between RPG Interactor and a Door
	     * @param door (Door), not null
	     */
	    public void interactWith(Door door){
	        // by default the interaction is empty
	    }

	}

}
