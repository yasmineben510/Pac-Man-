package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends AutomaticallyCollectableAreaEntity implements Logic{
	
	Sprite sprite;

	public Key(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		sprite = new Sprite("superpacman/key", 1,1, this);
		sprite.setDepth(-100.f);
	}


	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}


	@Override
	public boolean isOn() {
		if (this.isCollected()) return true;
		else return false;
	}


	@Override
	public boolean isOff() {
		if (this.isCollected()) return false;
		else return true;
	}


	@Override
	public float getIntensity() {
		return 0;
	}

}
