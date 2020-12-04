package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class SuperPacmanPlayerStatusGUI implements Graphics{
	
	private final float DEPTH = 0.f;
	private SuperPacmanPlayer player;
	private TextGraphics score;

	public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player) {		
		player = player;
		int score = player.score;
	}
	
	
	
	@Override
	public void draw(Canvas canvas) {
		
		float width = canvas.getScaledWidth();
		float height = canvas.getScaledHeight();
		Vector anchor = canvas.getTransform().getOrigin().sub(new Vector(width/2, height/2));
		ImageGraphics life = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),anchor.add(new Vector(1, height - 1.375f)), 1, DEPTH);
		life.draw(canvas);
		
	}

}
