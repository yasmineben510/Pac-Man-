package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class GameOverArea extends SuperPacmanArea {

	@Override
	public String getTitle() {
		return "superpacman/GameOverArea";
	}

	@Override
	public float getCameraScaleFactor() {
		return 300.f;
	}

	
	protected void createArea() {
		registerActor(new Background(this));
        }

	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return null;
	}



}
