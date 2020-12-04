package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.Positionable;

public class Bonus {
	
	Sprite[] sprites;
	
	public Bonus(Positionable parent) {
		sprites = RPGSprite.extractSprites("superpacman/coin", 4, 1, 1, parent , 16, 16);
	}

}
