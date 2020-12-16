package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.AutomaticallyCollectableAreaEntity;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

	
    /**
     * Simulate an interaction between RPG Interactor and a SuperPacmanPlayer
     * @param player (SuperPacmanPlayer), not null
     */
    default void interactWith(SuperPacmanPlayer player){
        // by default the interaction is empty
    }
    
    /**
     * Simulate an interaction between RPG Interactor and an AutomaticallyCollectableAreaEntity
     * @param collectable (AutomaticallyCollectableAreaEntity), not null
     */
    default void interactWith(AutomaticallyCollectableAreaEntity collectable){
        // by default the interaction is empty
    }
    
    /**
     * Simulate an interaction between RPG Interactor and a Bonus
     * @param bonus (Bonus), not null
     */
    default void interactWith(Bonus bonus){
    	// by default the interaction is empty
    }    

    /**
     * Simulate an interaction between RPG Interactor and a Ghost
     * @param ghost (Ghost), not null
     */
    default void interactWith(Ghost ghost){
        // by default the interaction is empty
    }

}
