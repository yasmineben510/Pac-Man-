package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

	
    /**
     * Simulate and interaction between RPG Interactor and a SuperPacmanPlayer
     * @param player (SuperPacmanPlayer), not null
     */
    default void interactWith(SuperPacmanPlayer player){
        // by default the interaction is empty
    }
    
    default void interactWith(CollectableAreaEntity collectable){
        // by default the interaction is empty
    }
    
// later: add interaction with ghost

}
