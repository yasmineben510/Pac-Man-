package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


public class SuperPacman extends RPG {
	
	public final static float CAMERA_SCALE_FACTOR = 13.f;
	public final static float STEP = 0.05f;


	private SuperPacmanPlayer player;
	private final String[] areas = {"superpacman/Level0", "superpacman/Level1","superpacman/Level2"};
	private final DiscreteCoordinates[] startingPositions = {new DiscreteCoordinates(2,10), 
															 new DiscreteCoordinates(5,15)};

	private int areaIndex;
	/**
	 * Add all the areas
	 */
	private void createAreas(){
		addArea(new Level0());
		addArea(new Level1());
		addArea(new Level2());
	}
	
	// idea decaler uniquement effrayer ghosts a la behavior au moyen de l'aire comme pour le resetpositions
	private void frightenGhosts() {
		((SuperPacmanArea)getCurrentArea()).frightenGhosts();	
	}
	

	/// SuperPacman implements playable
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			createAreas();
			areaIndex = 1;
			Area area = setCurrentArea(areas[areaIndex], true);
			player = new SuperPacmanPlayer(area, Orientation.DOWN, ((SuperPacmanArea)area).getPlayerSpawnPosition(),"superpacman/bonus");
			initPlayer(player);
			return true;
		}
		return false;
	}

	@Override
	public void end() {
	}
	
	// Returns the title of the game
	
	@Override
	public String getTitle() {
		return "Super Pac-Man";
	}
	
	@Override
	public void update(float deltaTime) {
		
		
		if(!player.isVulnerable()) {
			
			frightenGhosts();
			
			if (Ghost.getTimer()<=0) {
				player.resetVulnerable();
			}
		} 
		
		if(player.isEaten()) {
			((SuperPacmanArea)getCurrentArea()).resetAllGhostsPosition();
			player.setEaten(false);
		}
		
		
		
		super.update(deltaTime);
	}
}
