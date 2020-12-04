package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;

public class Bonus {
	
	Sprite[] sprites;
	
	public Bonus() {
		sprites = RPGSprite.extractSprites("superpacman/coin", 4, 1, 1, parent , 16, 16);
	}

}
