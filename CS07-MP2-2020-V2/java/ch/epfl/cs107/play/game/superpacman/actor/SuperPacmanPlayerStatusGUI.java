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
	
	private final float DEPTH = 1.f;
	private int score;
	private int life;
	
	public SuperPacmanPlayerStatusGUI(int score, int life) {
		this.score = score;
		this.life = life;
	}
	
	
	/**
	 * Sets the value of the player's score.
	 * @param score (int): value of the player's score.
	 */
	
	public void setScore(int score) {
		this.score = score;
	}


  /**
   * Sets the value of the player's life. 
   * @param life (int): value of the player's life
   */
	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public void draw(Canvas canvas) {
		
		
		float width = canvas.getScaledWidth();
		float height = canvas.getScaledHeight();
		Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
		ImageGraphics[] lifeGraphic = getLifeGraphic(anchor, height);
		
		for(int i = 0; i < lifeGraphic.length; ++i) {
			lifeGraphic[i].draw(canvas);
		}
		
		TextGraphics scoreTextValue = new TextGraphics(Integer.toString(score), 1.1f, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector(12f, height- 1.375f))); 
		scoreTextValue.draw(canvas);
		
		TextGraphics scoreText = new TextGraphics("SCORE:", 1.1f, Color.YELLOW, Color.BLACK, 0.05f, true, false, anchor.add(new Vector(8f, height- 1.375f))); 
		scoreText.draw(canvas);
		
	}
	
	private ImageGraphics[] getLifeGraphic(Vector anchor, float height) {
		
		ImageGraphics[] lifeGraphic = new ImageGraphics[5];
		
		for(int i = 0; i < lifeGraphic.length; ++i) {
			int j;
			if(i<life) j = 0;
			else j = 64;
			lifeGraphic[i] = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),1, 1, new RegionOfInterest(j, 0, 64, 64),anchor.add(new Vector(i+1, height - 1.375f)), 1, DEPTH);
			
		}
		
		return lifeGraphic;
	}

}
