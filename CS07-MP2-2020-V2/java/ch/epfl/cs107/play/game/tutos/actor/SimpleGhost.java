package ch.epfl.cs107.play.game.tutos.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class SimpleGhost extends Entity {
	
	private Sprite spriteGhost;
	private float hp;
	private TextGraphics hpText;
	
	public SimpleGhost(Vector position, String spriteName) {
		super(position);
		//create new image graphic
		spriteGhost = new Sprite(spriteName, 1, 1.f, this);
		//initializes energy
		hp =10;
		// create a graphic for the graphic and associates it to the ghost
		hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		hpText.setParent(this);
		this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
		
	}
	
	public boolean isWeak() {
		if (hp<=0) {
			return true;
		} else { 
			return false;
		}
	}
	
	public void strengthen() {
		hp = 10;
	}
	

	@Override
	public void draw(Canvas canvas) {
		spriteGhost.draw(canvas);
		hpText.draw(canvas);
	}
	
	@Override
	public void update(float deltaTime) {
		if (!isWeak()) {
		hp-=deltaTime;
		hpText.setText(Integer.toString((int) hp));
	    }
		if (isWeak()) {
			hp=0;
			hpText.setText(Integer.toString((int) hp));
		}
	}
	
	//public void moveUp(float delta) {
	public void moveUp() {
		super.setCurrentPosition(getPosition().add(0.f,0.05f));
	}
	
	public void moveDown() {
		super.setCurrentPosition(getPosition().add(0.f, -0.05f));
	}
	
	public void moveLeft() {
		super.setCurrentPosition(getPosition().add(-0.05f, 0.0f));
	}
	
	public void moveRight() {
		super.setCurrentPosition(getPosition().add(0.05f, 0.0f));
	}
}


