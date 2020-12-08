package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.window.Canvas;

public class Diamond extends AutomaticallyCollectableAreaEntity{
	
	final int points = 10;
	
    Sprite sprite;
	
	public Diamond(Area area, DiscreteCoordinates position) {
		super(area,Orientation.DOWN,position);
		sprite = new Sprite("superpacman/diamond", 1,1, this);
		sprite.setDepth(-100f);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}
	
	@Override
	public int getPoints() {
		return points;
	}

}
