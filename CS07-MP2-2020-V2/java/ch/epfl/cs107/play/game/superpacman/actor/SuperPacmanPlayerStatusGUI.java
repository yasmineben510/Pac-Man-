package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class SuperPacmanPlayerStatusGUI implements Graphics{
	
	/// Depth of the image 
	private final float DEPTH = 1.f;
	private int score;
	private int hp;
	
	/**
	 * Constructor for the SuperPacmanPlayer's status
	 * @param score (int) : The SuperPacmanPlayer's score. 
	 * @param hp (int) : The SuperPacmanPlayer's points of life.
	 */
	public SuperPacmanPlayerStatusGUI(int score, int hp) {
		this.score = score;
		this.hp = hp;
	}
	
	
	/**
	 * Sets the value of the player's score.
	 * @param score (int): value of the player's score.
	 */
	public void setScore(int score) {
		this.score = score;
	}


  /**
   * Sets the player's health points. 
   * @param hp (int): value of the player's health points. 
   */
	public void setHp(int hp) {
		this.hp = hp;
	}

	
	/**
	 * Constructor for the ImageGraphic of the Health Points (hp) of the SuperPacmanPlayer
	 * @param anchor (Vector) :
	 * @param height (float) : height of the canvas
	 * @return the ImageGraphic of the hp of the player
	 */
	private ImageGraphics[] getHpGraphic(Vector anchor, float height) {
		
		ImageGraphics[] hpGraphic = new ImageGraphics[5];
		
		for(int i = 0; i < hpGraphic.length; ++i) {
			int j;
			if(i<hp) j = 0;
			else j = 64;
			hpGraphic[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),1, 1, new RegionOfInterest(j, 0, 64, 64),anchor.add(new Vector(i+1, height - 1.375f)), 1, DEPTH);
		}
		
		return hpGraphic;
	}
	
	/// implements Graphics
	
		@Override
		public void draw(Canvas canvas) {
			
			float width = canvas.getScaledWidth();
			float height = canvas.getScaledHeight();
			Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
			ImageGraphics[] hpGraphic = getHpGraphic(anchor, height);
			
			for(int i = 0; i < hpGraphic.length; ++i) {
				hpGraphic[i].draw(canvas);
			}
			
			TextGraphics scoreText = new TextGraphics("SCORE: "+Integer.toString(score), 1.1f, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector(8f, height- 1.375f))); 
			scoreText.draw(canvas);

		}
}