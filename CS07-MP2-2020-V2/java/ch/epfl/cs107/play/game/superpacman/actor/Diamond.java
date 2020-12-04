package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.Positionable;

public class Diamond implements CollectableAreaEntity{
	
	final int points = 10;
	
    Sprite[] sprites;
	
	public Diamond(Positionable parent) {
		sprites = RPGSprite.extractSprites("superpacman/diamond", 4, 1, 1, parent , 16, 16);
	}

}
